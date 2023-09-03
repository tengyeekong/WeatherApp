package com.tengyeekong.weatherapp.ui.weather;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.tengyeekong.weatherapp.ui.R;
import com.tengyeekong.weatherapp.ui.databinding.FragmentWeatherBinding;
import com.tengyeekong.weatherapp.ui.webview.WebViewFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class WeatherFragment extends Fragment implements MenuProvider {

    public static final String EXTRA_LAT_LNG     = "EXTRA_LAT_LNG";
    public static final String EXTRA_NAME        = "EXTRA_NAME";
    public static final String EXTRA_IS_CREATING = "EXTRA_IS_CREATING";

    private WeatherViewModel       viewModel;
    private FragmentWeatherBinding binding;
    private MenuHost               menuHost;
    private String                 name;

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
        viewModel.getWeather();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentWeatherBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setViewModel(viewModel);

        getInputData();
        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (actionBar != null && !TextUtils.isEmpty(name)) {
            actionBar.setTitle(name);
        }

        viewModel.weather.observe(getViewLifecycleOwner(), weatherEntity -> {
            if (actionBar != null && (TextUtils.isEmpty(actionBar.getTitle()) || !actionBar.getTitle().equals(weatherEntity.getName()))) {
                actionBar.setTitle(weatherEntity.getName());
            }
        });

        return binding.getRoot();
    }

    private void getInputData() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            name = arguments.getString(EXTRA_NAME);
        }
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        menuHost = requireActivity();
        menuHost.addMenuProvider(this, getViewLifecycleOwner(), Lifecycle.State.CREATED);
    }

    @Override
    public void onCreateMenu(@NonNull final Menu menu, @NonNull final MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.menu_help, menu);
        Drawable drawable = menu.getItem(0).getIcon();
        if (drawable != null) {
            drawable.mutate();
            drawable.setColorFilter(getResources().getColor(R.color.icon), PorterDuff.Mode.SRC_ATOP);
        }
    }

    @Override
    public boolean onMenuItemSelected(@NonNull final MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.action_help) {
            Bundle bundle = new Bundle();
            bundle.putString(WebViewFragment.EXTRA_URL, "https://support.google.com/maps/");
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_WeatherFragment_to_WebViewFragment, bundle);
            return true;
        }
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        menuHost.removeMenuProvider(this);
        binding = null;
    }
}