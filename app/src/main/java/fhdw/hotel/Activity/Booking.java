package fhdw.hotel.Activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import fhdw.hotel.DomainModel.Address;
import fhdw.hotel.DomainModel.CurrentBooking;
import fhdw.hotel.DomainModel.Guest;
import fhdw.hotel.R;

/**
 * Created by Artur Briem on 01.12.2015.
 */
public class Booking extends Activity {
    private SimpleDateFormat sdf;
    EditText firstname;
    EditText lastname;
    EditText bday;
    EditText street;
    EditText plz;
    EditText city;
    CheckBox showBillingAdr;
    LinearLayout billingAdr;
    EditText billingStreet;
    EditText billingPostalCode;
    EditText billingCity;
    Guest guest;
    Address adr;
    Address billingAddress;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);


        firstname = (EditText) findViewById(R.id.txtFirstname);
        lastname = (EditText) findViewById(R.id.txtLastname);
        bday = (EditText) findViewById(R.id.dtpBirthday);
        street = (EditText) findViewById(R.id.txtStreet);
        plz = (EditText) findViewById(R.id.txtPostalcode);
        city = (EditText) findViewById(R.id.txtCity);
        showBillingAdr = (CheckBox) findViewById(R.id.cb_show_billing_adr);
        showBillingAdr.setOnCheckedChangeListener(ShowBillingAdress());
        billingAdr = (LinearLayout) findViewById(R.id.ll_billing_adr);
        billingStreet = (EditText) findViewById(R.id.txtBillingStreet);
        billingPostalCode = (EditText) findViewById(R.id.txtBillingPostalcode);
        billingCity = (EditText) findViewById(R.id.txtBillingCity);
        guest = new Guest();
        adr = new Address();
        billingAddress = new Address();
        removeKeypadFromDatePicker();
        sdf = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMANY);

    }

    private void removeKeypadFromDatePicker() {
        bday.setInputType(InputType.TYPE_NULL);
    }

    /**
     * Show the Datepicker and set the selected date into the birthday textbox.
     *
     * @param view
     */
    public void getArrivalDate(View view) {
        showDatePicker((EditText) findViewById(R.id.dtpBirthday));
    }

    /**
     * @param txtDate
     */
    private void showDatePicker(final EditText txtDate) {
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                txtDate.setText(sdf.format(newDate.getTime()));


            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    @NonNull
    private CheckBox.OnCheckedChangeListener ShowBillingAdress() {
        return new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    billingAdr.setVisibility(View.VISIBLE);
                }
                if (!isChecked) {
                    billingAdr.setVisibility(View.GONE);
                }
            }
        };
    }

    public void OnClickBooking(View view) throws ParseException {
        Intent intent = new Intent(Booking.this, CheckBooking.class);
        if (firstname.getText().toString().isEmpty()) {
            firstname.requestFocus();
            firstname.setError("Darf nicht leer sein!");
        } else {
            guest.setFirstname(firstname.getText().toString());
        }

        if (lastname.getText().toString().isEmpty()) {
            lastname.requestFocus();
            lastname.setError("Darf nicht leer sein!");
        } else {
            guest.setLastname(lastname.getText().toString());
        }

        if (!bday.getText().toString().isEmpty()) {
            guest.setBirthday(sdf.parse(bday.getText().toString()));;
        }

        if (!street.getText().toString().isEmpty()) {
            adr.setStreet(street.getText().toString());
        }

        if (!plz.getText().toString().isEmpty()) {
            adr.setPostalCode(plz.getText().toString());
        }

        if (!city.getText().toString().isEmpty()) {
            adr.setPostalCode(city.getText().toString());
        }

        if (!billingStreet.getText().toString().isEmpty()) {
            billingAddress.setPostalCode(billingStreet.getText().toString());
        }
        if (!billingCity.getText().toString().isEmpty()) {
            billingAddress.setPostalCode(billingCity.getText().toString());
        }
        if (!billingPostalCode.getText().toString().isEmpty()) {
            billingAddress.setPostalCode(billingPostalCode.getText().toString());
        }

        guest.setBillingAddress(billingAddress);
        guest.setContactAddress(adr);
        CurrentBooking.setGuest(guest);

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
        getMenuInflater().inflate(R.menu.menu_main, menu);

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
