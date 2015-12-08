package fhdw.hotel.BLL.Async.Room;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.support.annotation.Nullable;
import android.util.Pair;
import android.view.Display;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import fhdw.hotel.BLL.Async.IListener.IAsyncRoomListener;
import fhdw.hotel.BLL.RestService;
import fhdw.hotel.DomainModel.Guest;
import fhdw.hotel.DomainModel.Room;

public class GetCollection extends AsyncTask<String, Void, ArrayList<Room>> {
    private IAsyncRoomListener callback;


    public GetCollection(IAsyncRoomListener p_callback) {
        callback = p_callback;
    }

    @Override
    protected ArrayList<Room> doInBackground(String... params) {
        try {
            if (params.length == 0) throw new Exception("Params expected");

            ArrayList<Pair<String, String>> parameter = new ArrayList<>();
            parameter.add(new Pair<>("HotelId", params[0]));
            parameter.add(new Pair<>("Arrival", params[1]));
            parameter.add(new Pair<>("Departure", params[2]));




            String json = RestService.Get(callback.Controller, parameter);
//            String json = RestService.Get(callback.Controller, parameter);
            return new Gson().fromJson(json, new TypeToken<ArrayList<Room>>() {
            }.getType());
//            return new Gson().fromJson(readFile("C:/Users/Hatebreeder/Desktop/roomarray.json"), new TypeToken<ArrayList<Room>>() {
//            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Room> result) {

        callback.GetRoomCollectionComplete(result);
    }
}
