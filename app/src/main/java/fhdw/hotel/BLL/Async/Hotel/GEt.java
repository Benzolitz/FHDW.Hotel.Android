package fhdw.hotel.BLL.Async.Hotel;

import android.os.AsyncTask;
import android.util.Pair;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import fhdw.hotel.BLL.Async.IListener.IAsyncHotelListener;
import fhdw.hotel.BLL.JsonService;
import fhdw.hotel.BLL.RestService;
import fhdw.hotel.DomainModel.Hotel;

public class Get extends AsyncTask<String, Void, Hotel> {
    private IAsyncHotelListener callback;

    public Get(IAsyncHotelListener p_callback) {
        callback = p_callback;
    }

    @Override
    protected Hotel doInBackground(String... params) {
        if(params.length == 0) return null;

        try {
            ArrayList<Pair<String, String>> list = new ArrayList<>();
            list.add(new Pair<>("ID", params[0]));

            InputStream stream =  RestService.Get(callback.Controller, list);

            JsonService json = new JsonService<Hotel>();

            return (Hotel) json.DeserializeJson(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Hotel result) {
        callback.GetComplete(result);
    }
}
