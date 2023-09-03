package com.tengyeekong.weatherapp.di;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tengyeekong.weatherapp.data.repository.WeatherRepositoryImpl;
import com.tengyeekong.weatherapp.domain.repository.WeatherRepository;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(@ApplicationContext Context context) {
        return context.getSharedPreferences("WeatherAppPref", Context.MODE_PRIVATE);
    }

    @Module
    @InstallIn(SingletonComponent.class)
    abstract class AppBindModule {

        @Binds
        @Singleton
        abstract WeatherRepository bindWeatherRepository(WeatherRepositoryImpl impl);
    }
}
