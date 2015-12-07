package fhdw.hotel.BLL.Async.Room;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import fhdw.hotel.BLL.Async.IListener.IAsyncRoomListener;
import fhdw.hotel.BLL.RestService;
import fhdw.hotel.DomainModel.Guest;
import fhdw.hotel.DomainModel.Room;

public class GetCollection extends AsyncTask<String, Void, ArrayList<Room>> {
    private IAsyncRoomListener callback;

    private String readFile(String pathname) throws IOException {

        File file = new File(pathname);
        StringBuilder fileContents = new StringBuilder((int)file.length());
        Scanner scanner = new Scanner(file);
        String lineSeparator = System.getProperty("line.separator");

        try {
            while(scanner.hasNextLine()) {
                fileContents.append(scanner.nextLine() + lineSeparator);
            }
            return fileContents.toString();
        } finally {
            scanner.close();
        }
    }



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
