package fhdw.hotel.BLL.Async.Room;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;

import fhdw.hotel.BLL.Async.IListener.IAsyncRoomListener;
import fhdw.hotel.DomainModel.Room;

public class GetCollection extends AsyncTask<String, Void, ArrayList<Room>> {
    private Context context;
    private IAsyncRoomListener callback;

    public GetCollection(Context p_context, IAsyncRoomListener p_callback) {
        context = p_context;
        callback = p_callback;
    }

    @Override
    protected ArrayList<Room> doInBackground(String... params) {
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Room> result) {
        callback.GetCollectionComplete(result);
    }
}
