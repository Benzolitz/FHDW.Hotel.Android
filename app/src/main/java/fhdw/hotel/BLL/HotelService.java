package fhdw.hotel.BLL;

import android.content.Context;
import android.os.AsyncTask;

import fhdw.hotel.Activity.IAsyncTaskCompleteListener;
import fhdw.hotel.DomainModel.Hotel;



public class HotelService extends AsyncTask<String, Void, Hotel> {
    private Context context;
    private IAsyncTaskCompleteListener<Hotel> callback;

    public HotelService(Context p_context, IAsyncTaskCompleteListener p_callback) {
        context = p_context;
        callback = p_callback;
    }

    @Override
    protected Hotel doInBackground(String... params) {
        return null;
    }

    @Override
    protected void onPostExecute(Hotel result) {
        callback.onTaskComplete(result);
    }
}
