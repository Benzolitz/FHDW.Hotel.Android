package fhdw.hotel.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import fhdw.hotel.DomainModel.CurrentBooking;
import fhdw.hotel.R;

public class CheckBooking extends AppCompatActivity {
    private String IntentExtraName = "CurrentBooking";
    private CurrentBooking currentBooking;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_booking);

        gson = new Gson();
        String currentBookingString = (String) getIntent().getSerializableExtra(IntentExtraName);
        currentBooking = gson.fromJson(currentBookingString, new TypeToken<CurrentBooking>(){}.getType());

    }
}
