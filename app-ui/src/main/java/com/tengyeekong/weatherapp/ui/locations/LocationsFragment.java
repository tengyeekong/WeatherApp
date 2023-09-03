package com.tengyeekong.weatherapp.ui.locations;

import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.model.LatLng;
import com.tengyeekong.weatherapp.ui.R;
import com.tengyeekong.weatherapp.ui.databinding.FragmentLocationsBinding;
import com.tengyeekong.weatherapp.ui.weather.WeatherFragment;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LocationsFragment extends Fragment implements LocationAdapter.Interaction {

    private       LocationsViewModel       viewModel;
    private       FragmentLocationsBinding binding;
    private final LocationAdapter          adapter = new LocationAdapter(this);

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(LocationsViewModel.class);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentLocationsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.rvLocations.setAdapter(adapter);

        binding.fab.setOnClickListener(view1 -> NavHostFragment.findNavController(this)
            .navigate(R.id.action_LocationsFragment_to_AddLocationFragment));
    }

    @Override
    public void onItemClicked(final String location) {
        Pair<LatLng, String> pair = getLocationData(location);
        if (pair == null) return;

        Bundle bundle = new Bundle();
        bundle.putParcelable(WeatherFragment.EXTRA_LAT_LNG, pair.first);
        bundle.putString(WeatherFragment.EXTRA_NAME, pair.second);
        NavHostFragment.findNavController(this)
                .navigate(R.id.action_LocationsFragment_to_WeatherFragment, bundle);
    }

    @Override
    public void onItemRemoved(final String location) {
        Pair<LatLng, String> pair = getLocationData(location);
        if (pair == null) return;

        viewModel.removeLocation(pair.first);
        ArrayList<String> newList = new ArrayList<>(adapter.differ.getCurrentList());
        newList.remove(location);
        adapter.differ.submitList(newList);
    }

    private Pair<LatLng, String> getLocationData(String location) {
        String[] data = location.split(",");
        if (data.length != 3) return null;

        double lat = Double.parseDouble(data[0]);
        double lng = Double.parseDouble(data[1]);
        String name = data[2];
        LatLng latLng = new LatLng(lat, lng);
        return new Pair<>(latLng, name);
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.differ.submitList(viewModel.getLocations());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}