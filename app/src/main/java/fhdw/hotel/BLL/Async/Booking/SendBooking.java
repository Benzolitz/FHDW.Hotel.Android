package fhdw.hotel.BLL.Async.Booking;

import android.os.AsyncTask;
import android.util.Pair;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;

import fhdw.hotel.BLL.Async.IListener.IAsyncBookingListener;
import fhdw.hotel.BLL.RestService;
import fhdw.hotel.DomainModel.Booking;

public class SendBooking extends AsyncTask<String, Void, Booking> {
    private IAsyncBookingListener callback;

    public SendBooking(IAsyncBookingListener p_callback){
        callback = p_callback;
    }

    @Override
    protected Booking doInBackground(String... params) {
        try {
            if (params.length == 0) throw new Exception("CurrentBooking!");

            ArrayList<Pair<String, String>> parameter = new ArrayList<>();
            parameter.add(new Pair<>("CurrentBooking", params[0]));

            String json = RestService.Put(callback.Controller, parameter);
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
            return gson.fromJson(json, new TypeToken<Booking>() { }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    protected void onPostExecute(Booking p_booking){
        callback.BookingComplete(p_booking);
    }
}
