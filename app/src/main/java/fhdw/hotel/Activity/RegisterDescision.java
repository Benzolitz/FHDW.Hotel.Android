package fhdw.hotel.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import fhdw.hotel.R;

/**
 * Created by Artur Briem on 01.12.2015.
 */
public class RegisterDescision extends Activity{

    LinearLayout llDescisionBtns;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_descision);
        llDescisionBtns.setOrientation(LinearLayout.HORIZONTAL);

    }

    public void GoToRegistrationSiteOnClick(View view) {
        Intent intent = new Intent(RegisterDescision.this, Login.class);



        startActivity(intent);
    }

    public void GoToBookingOnClick(View view) {
        Intent intent = new Intent(RegisterDescision.this, Booking.class);



        startActivity(intent);
    }


    //region GUI-Methods

    /**
     * Inflate the menu; this adds items to the action bar if it is present.
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_prototype_suchform, menu);

        return true;
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
    //endregion

}