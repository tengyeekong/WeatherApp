package com.tengyeekong.weatherapp.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import java.io.IOException;
import java.net.URL;

import androidx.databinding.BindingAdapter;

public class BindingAdapters {

    @BindingAdapter(value = {"iconName"})
    public static void setIcon(ImageView view, String iconName) {
        new Thread(() -> {
            try {
                URL url = new URL("https://openweathermap.org/img/wn/" + iconName + ".png");
                Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                new Handler(Looper.getMainLooper()).post(() -> {
                    view.setImageBitmap(bmp);
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
