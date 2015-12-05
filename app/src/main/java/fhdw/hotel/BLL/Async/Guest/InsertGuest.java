package fhdw.hotel.BLL.Async.Guest;

import android.os.AsyncTask;
import android.util.Pair;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;

import fhdw.hotel.BLL.Async.IListener.IAsyncGuestListener;
import fhdw.hotel.BLL.RestService;
import fhdw.hotel.DomainModel.Guest;

public class InsertGuest extends AsyncTask<String, Void, Guest> {
    private IAsyncGuestListener callback;

    public InsertGuest(IAsyncGuestListener p_callback) {
        callback = p_callback;
    }

    @Override
    protected Guest doInBackground(String... params) {
        try {
            if (params.length == 0) throw new Exception("Params expected");

            ArrayList<Pair<String, String>> parameter = new ArrayList<>();
            parameter.add(new Pair<>("Guest", params[0]));

            String json = RestService.Put(callback.Controller, parameter);
            return new Gson().fromJson(json, new TypeToken<Guest>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Guest p_guest){
        callback.InsertGuestTest(p_guest);
    }
}
