package com.tengyeekong.weatherapp.data.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.net.ssl.HttpsURLConnection;

public class HttpRequestUtil {

    @Inject
    public HttpRequestUtil() {
    }

    public String getRequest(String url, ArrayList<String> requestData) {
        try {
            URL uri = new URL(url + "?" + String.join("&", requestData));
            HttpURLConnection httpURLConnection = (HttpURLConnection) uri.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Content-length", "0");
            httpURLConnection.connect();
            try {
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    return readStream(httpURLConnection.getInputStream());
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // this is done so that there are no open connections left when this task is going to complete
                httpURLConnection.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuilder response = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }
}
