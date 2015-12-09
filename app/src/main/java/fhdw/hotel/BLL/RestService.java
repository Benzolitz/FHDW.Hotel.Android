package fhdw.hotel.BLL;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * @author Lucas Engel
 * Serviceclass for all calls to the REST-Server.
 */

public class RestService {
    // private static String serverUrl = "http://192.168.2.104:35588/api/";
    // private static String serverUrl = "http://192.168.178.30:35588/api/";
    private static String serverUrl = "http://192.168.43.125:35588/api/";

    // region Web-Methods
    public static String Get(String p_controller, ArrayList<Pair<String, String>> p_urlParameters) throws IOException {
        URL url = createURL(p_controller, p_urlParameters);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestProperty("Accept", "application/json");

        try {
            InputStream in = urlConnection.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder out = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                out.append(line);
            }

            reader.close();

            return out.toString();
        } catch (Exception ex) {
            throw ex;
        } finally {
            urlConnection.disconnect();
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static String Post(String p_controller, ArrayList<Pair<String, String>> p_urlParameters) throws Exception {
        if (p_urlParameters == null || p_urlParameters.size() == 0) throw new Exception("One Parameter is required!");

        URL url = new URL(createURLControllerPath(serverUrl, p_controller));

        String urlParameter = p_urlParameters.get(0).second;
        byte[] putData = urlParameter.getBytes("UTF-8");
        int postDataLength = putData.length;

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setDoOutput(true);
        urlConnection.setInstanceFollowRedirects(false);
        urlConnection.setRequestMethod("POST");
        urlConnection.setRequestProperty("Content-Type", "application/json");
        urlConnection.setRequestProperty("charset", "utf-8");
        urlConnection.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        urlConnection.setUseCaches(false);

        urlConnection.getOutputStream().write(putData);

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            StringBuilder out = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) {
                out.append(line);
            }

            in.close();

            return out.toString();
        } catch (IOException e) {
            throw e;
        } finally {
            urlConnection.disconnect();
        }
    }

    public static String Put(String p_controller, ArrayList<Pair<String, String>> p_urlParameters) throws Exception {
        if (p_urlParameters == null || p_urlParameters.size() == 0) throw new Exception("One Parameter is required!");

        URL url = new URL(createURLControllerPath(serverUrl, p_controller));

        String urlParameter = p_urlParameters.get(0).second;
        byte[] putData = urlParameter.getBytes("UTF-8");
        int postDataLength = putData.length;

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setDoOutput(true);
        urlConnection.setInstanceFollowRedirects(false);
        urlConnection.setRequestMethod("PUT");
        urlConnection.setRequestProperty("Content-Type", "application/json");
        urlConnection.setRequestProperty("charset", "utf-8");
        urlConnection.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        urlConnection.setUseCaches(false);

        urlConnection.getOutputStream().write(putData);

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            StringBuilder out = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) {
                out.append(line);
            }

            in.close();

            return out.toString();
        } catch (IOException e) {
            throw e;
        } finally {
            urlConnection.disconnect();
        }
    }
    // endregion

    // region Helper
    private static URL createURL(String p_controller, ArrayList<Pair<String, String>> p_parameters) throws MalformedURLException {
        return new URL(createURLControllerPath(serverUrl, p_controller) + "?" + createURLParameter(p_parameters));
    }

    private static String createURLControllerPath(String p_serverUrl, String p_controller) {
        if (!p_serverUrl.substring(p_serverUrl.length() - 1).equals("/"))
            p_serverUrl += "/";

        return p_serverUrl + p_controller;
    }

    private static String createURLParameter(ArrayList<Pair<String, String>> p_urlParameter) {
        if (p_urlParameter == null || p_urlParameter.size() == 0) return "";

        String urlParameter = "";

        for (int i = 0; i < p_urlParameter.size(); i++) {
            urlParameter += String.format("%1$s%2$s=%3$s", (i == 0 ? "" : "&"), p_urlParameter.get(i).first, p_urlParameter.get(i).second);
        }

        return urlParameter;
    }
    // endregion
}
