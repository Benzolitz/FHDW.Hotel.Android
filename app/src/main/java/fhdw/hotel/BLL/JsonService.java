package fhdw.hotel.BLL;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JsonService<T> {
    public T DeserializeJson(InputStream p_json) throws IOException {
        if(p_json.available() == 0) return null;

        BufferedReader reader = new BufferedReader(new InputStreamReader(p_json));
        StringBuilder out = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
        }

        reader.close();

        Gson gson = new Gson();
        return gson.fromJson(out.toString(), new TypeToken<T>() {
        }.getType());
    }
}
