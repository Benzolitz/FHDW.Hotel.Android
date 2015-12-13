package fhdw.hotel.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import fhdw.hotel.DomainModel.CurrentBooking;
import fhdw.hotel.R;

public class RegisterDescision extends Activity{
    private String IntentExtraName = "CurrentBooking";
    private CurrentBooking currentBooking;
    private Gson gson;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_descision);

        gson = new Gson();
        String currentBookingString = (String) getIntent().getSerializableExtra(IntentExtraName);
        currentBooking = gson.fromJson(currentBookingString, new TypeToken<CurrentBooking>(){}.getType());

    }

    /**
     * Listener for the Login button
     * @param view
     */
    public void GoToLoginView(View view) {
        Intent intent = new Intent(RegisterDescision.this, Login.class);
        intent.putExtra(IntentExtraName, gson.toJson(currentBooking));
        startActivity(intent);
    }

    /**
     * Listener for Registration button
     * @param view
     */
    public void GoToRegistrationSiteOnClick(View view) {
        Intent intent = new Intent(RegisterDescision.this, Register.class);
        intent.putExtra(IntentExtraName, gson.toJson(currentBooking));
        startActivity(intent);
    }

    /**
     * Listener for the Booking-without-registration Button
     * @param view
     */
    public void GoToBookingOnClick(View view) {
        Intent intent = new Intent(RegisterDescision.this, Booking.class);
        intent.putExtra(IntentExtraName, gson.toJson(currentBooking));
        startActivity(intent);
    }


    /**
     * Handle action bar item clicks here. The action bar will automatically handle clicks on the Home/Up button, so long as you specify a parent activity in AndroidManifest.xml.
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
