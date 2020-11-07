package com.movie.ombdwebservice.service.home;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class WEbServiceOmbd {

    public static final String SEARCH_URL = "http://omdbapi.com/?s=TITLE&apikey=APIKEY";

    @Transactional
    @SneakyThrows
    public String sendGetRequest(String requestURL) {
        StringBuffer response = new StringBuffer();
        URL url = new URL(requestURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "*/*");
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        InputStream stream = connection.getInputStream();
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            response.append(line);
        }
        bufferedReader.close();
        connection.disconnect();
        return response.toString();
    }

    public String searchMovieByTitle(String title, String key) {
        String requestURL = SEARCH_URL.replaceAll("TITLE", title).replaceAll("APIKEY", key);
        return sendGetRequest(requestURL);
    }
}
