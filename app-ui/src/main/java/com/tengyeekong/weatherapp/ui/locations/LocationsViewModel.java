package com.tengyeekong.weatherapp.ui.locations;

import com.google.android.gms.maps.model.LatLng;
import com.tengyeekong.weatherapp.domain.repository.WeatherRepository;

import java.util.Collections;
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
        List<String> locations = repository.getLocations();
        Collections.sort(locations, (a, b) -> {
            String[] listA = a.split(",");
            String nameA = "";
            if (listA.length > 2) {
                nameA = listA[2];
            }
            String[] listB = b.split(",");
            String nameB = "";
            if (listB.length > 2) {
                nameB = listB[2];
            }
            return nameA.compareToIgnoreCase(nameB);
        });
        return locations;
    }

    void removeLocation(LatLng latLng) {
        repository.removeLocation(latLng.latitude, latLng.longitude);
    }

    void removeAllLocations() {
        repository.removeAllLocations();
    }
}
