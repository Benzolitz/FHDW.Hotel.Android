package fhdw.hotel.BLL.Async.Hotel;

import android.os.AsyncTask;
import android.util.Pair;

import java.io.IOException;
import java.util.ArrayList;

import fhdw.hotel.BLL.Async.IListener.IAsyncHotelListener;
import fhdw.hotel.BLL.RestService;
import fhdw.hotel.DomainModel.Hotel;

public class GetCollection extends AsyncTask<String, Void, ArrayList<Hotel>> {
    private IAsyncHotelListener callback;

    public GetCollection(IAsyncHotelListener p_callback) {
        callback = p_callback;
    }

    @Override
    protected ArrayList<Hotel> doInBackground(String... params) {
        try {
            return RestService.Get(callback.Controller, new ArrayList<Pair<String, String>>());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Hotel> result) {
        callback.GetCollectionComplete(result);
    }
}
