package fhdw.hotel.BLL;

import android.util.Pair;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class RestService {
    private static String serverUrl = "http://192.168.2.104:35588/api/";

    // region Web-Methods
    public static <T> T Get(String p_controller, ArrayList<Pair<String, String>> p_parameters) throws IOException {
        URL url = createGetUrl(p_controller, p_parameters);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestProperty("Accept", "application/json");

        try {
            InputStream in = urlConnection.getInputStream();
            return DeserializeJson(in);
        } catch (Exception ex) {
            throw ex;
        } finally {
            urlConnection.disconnect();
        }
    }

    public static void Post(String p_controller) throws IOException {
        URL url = createPostUrl(p_controller);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            urlConnection.setDoOutput(true);

            urlConnection.setChunkedStreamingMode(0);

            OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
    }

    public static void Put(String p_controller) throws IOException {
        URL url = createPostUrl(p_controller);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            urlConnection.setDoOutput(true);

            urlConnection.setChunkedStreamingMode(0);

            OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
    }
    // endregion

    // region Helper
    private static <T> T DeserializeJson(InputStream p_json) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(p_json));
        StringBuilder out = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
        }

        reader.close();

        Gson gson = new Gson();
        return gson.fromJson(out.toString(), new TypeToken<T>() {
        }.getType());
    }

    private static URL createGetUrl(String p_controller, ArrayList<Pair<String, String>> p_parameters) throws MalformedURLException {
        String completeUrl = serverUrl + p_controller;

        if (p_parameters == null || p_parameters.size() == 0) return new URL(completeUrl);

        boolean firstParameter = true;

        for (Pair<String, String> parameter : p_parameters) {
            if (firstParameter) {
                completeUrl += "?";
                firstParameter = false;
            } else {
                completeUrl += "&";
            }
            completeUrl += parameter.first + "=" + parameter.second;
        }

        return new URL(completeUrl);
    }

    private static URL createPostUrl(String p_controller) {
        return null;
    }
    // endregion
}
