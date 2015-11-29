package fhdw.hotel.BLL.Async.Hotel;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;

import java.io.IOException;
import java.util.ArrayList;

import fhdw.hotel.BLL.Async.IListener.IAsyncHotelListener;
import fhdw.hotel.BLL.RestService;
import fhdw.hotel.DomainModel.Hotel;

public class Get extends AsyncTask<String, Void, Hotel> {
    private Context context;
    private IAsyncHotelListener callback;

    public Get(Context p_context, IAsyncHotelListener p_callback) {
        context = p_context;
        callback = p_callback;
    }

    @Override
    protected Hotel doInBackground(String... params) {
        try {
            return RestService.Get(callback.Controller, new ArrayList<Pair<String, String>>());
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
