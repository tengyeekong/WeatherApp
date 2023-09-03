package com.tengyeekong.weatherapp.ui.webview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tengyeekong.weatherapp.ui.databinding.FragmentWebviewBinding;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class WebViewFragment extends Fragment {

    public static final String EXTRA_URL   = "EXTRA_URL";

    private String url;

    private FragmentWebviewBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentWebviewBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getInputData();
        binding.webview.loadUrl(url);
    }

    private void getInputData() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            url = arguments.getString(EXTRA_URL);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}