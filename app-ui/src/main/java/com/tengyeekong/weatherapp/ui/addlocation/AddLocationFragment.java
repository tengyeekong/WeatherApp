package com.tengyeekong.weatherapp.ui.addlocation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.tengyeekong.weatherapp.ui.R;
import com.tengyeekong.weatherapp.ui.databinding.FragmentAddLocationBinding;
import com.tengyeekong.weatherapp.ui.weather.WeatherFragment;

import androidx.annotation.NonNull;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.navigation.fragment.NavHostFragment;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddLocationFragment extends Fragment implements OnMapReadyCallback, MenuProvider {

    private             FragmentAddLocationBinding binding;
    private             MenuHost                   menuHost;
    private             LatLng                     latLng;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentAddLocationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        menuHost = requireActivity();
        menuHost.addMenuProvider(this, getViewLifecycleOwner(), Lifecycle.State.CREATED);

        binding.pin.post(() -> binding.pin.setY(binding.pin.getY() - (binding.pin.getHeight() / 2f) + 10));
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(@NonNull final GoogleMap googleMap) {
        LatLng latLng = new LatLng(-33.852, 151.211);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.setOnCameraIdleListener(() -> {
            this.latLng = googleMap.getCameraPosition().target;
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        menuHost.removeMenuProvider(this);
        binding = null;
    }

    @Override
    public void onCreateMenu(@NonNull final Menu menu, @NonNull final MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.menu_add_location, menu);
    }

    @Override
    public boolean onMenuItemSelected(@NonNull final MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.action_add) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(WeatherFragment.EXTRA_LAT_LNG, latLng);
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_AddLocationFragment_to_WeatherFragment, bundle);
            return true;
        }

        return false;
    }
}