package com.tengyeekong.weatherapp.ui.locations;

import com.google.android.gms.maps.model.LatLng;
import com.tengyeekong.weatherapp.domain.repository.WeatherRepository;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.ViewModel;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LocationsViewModel extends ViewModel {

    private final WeatherRepository repository;

    @Inject
    LocationsViewModel(WeatherRepository repository) {
        this.repository = repository;
    }

    List<String> getLocations() {
        return repository.getLocations();
    }

    void removeLocation(LatLng latLng) {
        repository.removeLocation(latLng.latitude, latLng.longitude);
    }
}
