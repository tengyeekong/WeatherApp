package com.tengyeekong.weatherapp.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tengyeekong.weatherapp.data.repository.WeatherRepositoryImpl;
import com.tengyeekong.weatherapp.domain.repository.WeatherRepository;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    @Singleton
    Gson provideMoshi() {
        return new GsonBuilder().create();
    }

    @Module
    @InstallIn(SingletonComponent.class)
    abstract class AppBindModule {

        @Binds
        @Singleton
        abstract WeatherRepository bindWeatherRepository(WeatherRepositoryImpl impl);
    }
}
