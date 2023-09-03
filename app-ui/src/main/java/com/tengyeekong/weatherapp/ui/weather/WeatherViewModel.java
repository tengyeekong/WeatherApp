package com.tengyeekong.weatherapp.ui.weather;

import com.google.android.gms.maps.model.LatLng;
import com.tengyeekong.weatherapp.domain.entity.WeatherEntity;
import com.tengyeekong.weatherapp.domain.repository.WeatherRepository;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class WeatherViewModel extends ViewModel {

    private final SavedStateHandle  savedStateHandle;
    private final WeatherRepository repository;
    ExecutorService weatherExecutorService = Executors.newSingleThreadExecutor();

    private final MutableLiveData<WeatherEntity> _weather = new MutableLiveData<>();
    public final  LiveData<WeatherEntity>        weather  = _weather;

    @Inject
    WeatherViewModel(SavedStateHandle savedStateHandle, WeatherRepository repository) {
        this.savedStateHandle = savedStateHandle;
        this.repository = repository;
    }

    void getWeather() {
        LatLng latLng = savedStateHandle.get(WeatherFragment.EXTRA_LAT_LNG);
        boolean isCreating = Boolean.TRUE.equals(savedStateHandle.get(WeatherFragment.EXTRA_IS_CREATING));
        if (latLng == null) return;

        weatherExecutorService.execute(() -> {
            WeatherEntity weatherEntity = repository.getWeather(latLng.latitude, latLng.longitude);
            if (isCreating) {
                repository.addLocation(
                        weatherEntity.getCoord().getLat(),
                        weatherEntity.getCoord().getLon(),
                        weatherEntity.getName()
                );
            }
            _weather.postValue(weatherEntity);
        });
    }

    @Override
    protected void onCleared() {
        weatherExecutorService.shutdown();
        super.onCleared();
    }
}
