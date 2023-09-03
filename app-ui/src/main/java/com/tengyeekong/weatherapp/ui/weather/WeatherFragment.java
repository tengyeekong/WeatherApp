package com.tengyeekong.weatherapp.ui.weather;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.model.LatLng;
import com.tengyeekong.weatherapp.ui.databinding.FragmentWeatherBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class WeatherFragment extends Fragment {

    public static final String EXTRA_LAT_LNG = "EXTRA_LAT_LNG";
    public static final String EXTRA_NAME    = "EXTRA_NAME";

    private WeatherViewModel       viewModel;
    private FragmentWeatherBinding binding;
    private LatLng                 latLng;
    private String                 name;

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
        Bundle arguments = getArguments();
        if (arguments != null) {
            latLng = arguments.getParcelable(EXTRA_LAT_LNG);
            name = arguments.getString(EXTRA_NAME);

            requireActivity().setTitle(name);
        }
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentWeatherBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}