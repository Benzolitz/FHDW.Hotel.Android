package fhdw.hotel.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import fhdw.hotel.BLL.Async.IListener.IAsyncBookingListener;
import fhdw.hotel.DomainModel.*;
import fhdw.hotel.DomainModel.Booking;
import fhdw.hotel.R;

public class CheckBooking extends AppCompatActivity implements IAsyncBookingListener {
    private String IntentExtraName = "CurrentBooking";
    private CurrentBooking currentBooking;
    private Gson gson;

    private TextView txvBookingInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_booking);

        txvBookingInformation = (TextView) findViewById(R.id.txvBookingInformation);

        gson = new Gson();
        String currentBookingString = (String) getIntent().getSerializableExtra(IntentExtraName);
        currentBooking = gson.fromJson(currentBookingString, new TypeToken<CurrentBooking>() {
        }.getType());

        addTextToBookingInformation();
    }

    /**
     * Adds dynamically booking information to the overview
     *
     */
    private void addTextToBookingInformation() {
        double sumSingle = currentBooking.getSingleRoomCnt() * 11.11;
        double sumDouble = currentBooking.getDoubleRoomCnt() * 22.22;
        double sumFamily = currentBooking.getFamilyRoomCnt() * 30;

        String bookingInformation = "";
        bookingInformation += "Ihre Daten:\n";
        bookingInformation += currentBooking.getGuest().getFirstname() + " " + currentBooking.getGuest().getLastname() + "\n";
        bookingInformation += currentBooking.getGuest().getContactAddress().getStreet() + "\n";
        bookingInformation += currentBooking.getGuest().getContactAddress().getPostalCode() + " " + currentBooking.getGuest().getContactAddress().getCity() + "\n\n";
        bookingInformation += "Ihre Auswahl:\n";
        bookingInformation += "Hotel " + currentBooking.getHotel().getName() + " (" + currentBooking.getHotel().getAddress().getCity() + ")\n";
        bookingInformation += "Personenanzahl: " + currentBooking.getPersonCount() + "\n";
        bookingInformation += "Ankunft: " + currentBooking.getArrival().toString() + "\n";
        bookingInformation += "Abfahrt: " + currentBooking.getDeparture().toString() + "\n\n";
        bookingInformation += "Zimmerauswahl:\n";
        bookingInformation += currentBooking.getSingleRoomCnt() > 0 ? "Einzelzimmer: \t\t" + currentBooking.getSingleRoomCnt() + " (" + sumSingle + " €)" + "\n" : "";
        bookingInformation += currentBooking.getDoubleRoomCnt() > 0 ? "Doppelzimmer: \t" + currentBooking.getDoubleRoomCnt() + " (" + sumDouble + " €)" + "\n" : "";
        bookingInformation += currentBooking.getFamilyRoomCnt() > 0 ? "Familienzimmer: \t" + currentBooking.getFamilyRoomCnt() + " (" + sumFamily + " €)" + "\n" : "";
        bookingInformation += "---------------\n\n";
        bookingInformation += "Gesamtpreis \t\t\t\t" + (sumSingle + sumDouble + sumFamily) + " €";

        txvBookingInformation.setText(bookingInformation);
    }

    /**
     *
     * @param view
     */
    public void RegisterNewBooking(View view) {
        new fhdw.hotel.BLL.Async.Booking.SendBooking(this).execute(gson.toJson(currentBooking));
    }

    @Override
    public void BookingComplete(Booking p_booking) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Buchung erfolgreich");
        alertDialog.setMessage("Die Buchung wurde erfolgreich durchgeführt!");
        alertDialog.show();
    }
}
