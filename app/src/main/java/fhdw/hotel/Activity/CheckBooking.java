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

import fhdw.hotel.DomainModel.CurrentBooking;
import fhdw.hotel.R;

public class CheckBooking extends AppCompatActivity {

    LinearLayout llBookingOverview;
    TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_booking);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        llBookingOverview = (LinearLayout) findViewById(R.id.ll_my_booking);
        test = (TextView) findViewById(R.id.test);
        test.setText(CurrentBooking.toOutString());




    }

}
