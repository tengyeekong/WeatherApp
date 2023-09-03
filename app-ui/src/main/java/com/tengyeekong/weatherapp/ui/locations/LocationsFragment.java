package com.tengyeekong.weatherapp.ui.locations;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.model.LatLng;
import com.tengyeekong.weatherapp.ui.R;
import com.tengyeekong.weatherapp.ui.databinding.FragmentLocationsBinding;
import com.tengyeekong.weatherapp.ui.weather.WeatherFragment;
import com.tengyeekong.weatherapp.ui.webview.WebViewFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LocationsFragment extends Fragment implements LocationAdapter.Interaction, MenuProvider {

    private       LocationsViewModel       viewModel;
    private       FragmentLocationsBinding binding;
    private       MenuHost                 menuHost;
    private final LocationAdapter          adapter   = new LocationAdapter(this);
    private       List<String>             locations = new ArrayList<>();

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

        menuHost = requireActivity();
        menuHost.addMenuProvider(this, getViewLifecycleOwner(), Lifecycle.State.CREATED);

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
        if (data.length < 2) return null;

        double lat = Double.parseDouble(data[0]);
        double lng = Double.parseDouble(data[1]);
        String name = null;
        if (data.length > 2) {
            name = data[2];
        }
        LatLng latLng = new LatLng(lat, lng);
        return new Pair<>(latLng, name);
    }

    @Override
    public void onCreateMenu(@NonNull final Menu menu, @NonNull final MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.menu_locations, menu);
        MenuItem item = menu.getItem(0);
        Drawable drawable = item.getIcon();
        if (drawable != null) {
            drawable.mutate();
            drawable.setColorFilter(getResources().getColor(R.color.icon), PorterDuff.Mode.SRC_ATOP);
        }
        final SearchView searchView = (SearchView) item.getActionView();
        SearchManager searchManager = (SearchManager) requireActivity().getSystemService(Context.SEARCH_SERVICE);
        if (searchView != null && searchManager != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().getComponentName()));
            searchView.setQueryHint(getString(R.string.action_search));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(final String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(final String newText) {
                    if (TextUtils.isEmpty(newText)) {
                        adapter.differ.submitList(viewModel.getLocations());
                        return false;
                    }
                    ArrayList<String> newList = new ArrayList<>();
                    for (String location : locations) {
                        if (location.toLowerCase().contains(newText.toLowerCase())) {
                            newList.add(location);
                        }
                    }
                    adapter.differ.submitList(newList);
                    return false;
                }
            });
        }
    }

    @Override
    public boolean onMenuItemSelected(@NonNull final MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.action_clear_locations) {
            viewModel.removeAllLocations();
            adapter.differ.submitList(new ArrayList<>());
            return true;
        }
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        locations = viewModel.getLocations();
        adapter.differ.submitList(locations);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        menuHost.removeMenuProvider(this);
        binding = null;
    }
}