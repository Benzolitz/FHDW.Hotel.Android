package fhdw.hotel.BLL.Async.Guest;

import android.os.AsyncTask;
import android.util.Pair;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;

import fhdw.hotel.BLL.Async.IListener.IAsyncGuestListener;
import fhdw.hotel.BLL.RestService;
import fhdw.hotel.DomainModel.Guest;

public class CheckLogin extends AsyncTask<String, Void, Guest> {
    private IAsyncGuestListener callback;

    public CheckLogin(IAsyncGuestListener p_callback) {
        callback = p_callback;
    }

    @Override
    protected Guest doInBackground(String... params) {
        try {
            if (params.length < 2) throw new Exception("Values for EMail and Password expectet!");

            ArrayList<Pair<String, String>> parameter = new ArrayList<>();
            parameter.add(new Pair<>("EMail", params[0]));
            parameter.add(new Pair<>("Password", params[1]));

            String json = RestService.Get(callback.Controller, parameter);
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
            return gson.fromJson(json, new TypeToken<Guest>() { }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Guest p_guest){
        callback.CheckLoginComplete(p_guest);
    }
}
