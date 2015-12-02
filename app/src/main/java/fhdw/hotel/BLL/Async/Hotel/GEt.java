package fhdw.hotel.BLL.Async.Hotel;

import android.os.AsyncTask;
import android.util.Pair;

import java.io.IOException;
import java.util.ArrayList;

import fhdw.hotel.BLL.Async.IListener.IAsyncHotelListener;
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

        //            ArrayList<Pair<String, String>> list = new ArrayList<>();
//            list.add(new Pair<>("ID", params[0]));

//            String stream =  RestService.Get(callback.Controller, list);
        // return new JsonService<Hotel>().DeserializeJson(stream);
        return null;
    }

    @Override
    protected void onPostExecute(Hotel result) {
        callback.GetComplete(result);
    }
}
