package com.tengyeekong.weatherapp.ui.weather;

import com.tengyeekong.weatherapp.domain.repository.WeatherRepository;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class WeatherViewModel extends ViewModel {

    private final SavedStateHandle  savedStateHandle;
    private final WeatherRepository repository;

    @Inject
    WeatherViewModel(SavedStateHandle  savedStateHandle, WeatherRepository repository) {
        this.savedStateHandle = savedStateHandle;
        this.repository = repository;
    }

    List<String> getLocations() {
        return repository.getLocations();
    }
}
