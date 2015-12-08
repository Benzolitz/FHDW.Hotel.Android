package fhdw.hotel.Activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import fhdw.hotel.DomainModel.Address;
import fhdw.hotel.DomainModel.CurrentBooking;
import fhdw.hotel.DomainModel.Guest;
import fhdw.hotel.R;

public class Booking extends Activity {
    private String IntentExtraName = "CurrentBooking";
    private CurrentBooking currentBooking;
    private Gson gson;

    private SimpleDateFormat dateFormat;
    private EditText txtFirstname;
    private EditText txtLastname;

    private EditText txtBirthday;
    private EditText txtBirthplace;

    private EditText txtContactStreet;
    private EditText txtContactPostalcode;
    private EditText txtContactCity;

    private CheckBox cbAddBillingAddress;
    private LinearLayout llBillingAddress;

    private EditText txtBillingStreet;
    private EditText txtBillingPostalcode;
    private EditText txtBillingCity;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMANY);


        txtFirstname = (EditText) findViewById(R.id.txtFirstname);
        txtLastname = (EditText) findViewById(R.id.txtLastname);
        txtBirthday = (EditText) findViewById(R.id.dtpBirthday);
        txtBirthplace = (EditText) findViewById(R.id.txtBirthplace);

        txtContactStreet = (EditText) findViewById(R.id.txtContactStreet);
        txtContactPostalcode = (EditText) findViewById(R.id.txtContactPostalcode);
        txtContactCity = (EditText) findViewById(R.id.txtContactCity);

        cbAddBillingAddress = (CheckBox) findViewById(R.id.cbAddBillingAddress);
        cbAddBillingAddress.setOnCheckedChangeListener(ShowBillingAdress());

        llBillingAddress = (LinearLayout) findViewById(R.id.llBillingAddress);
        txtBillingStreet = (EditText) findViewById(R.id.txtBillingStreet);
        txtBillingPostalcode = (EditText) findViewById(R.id.txtBillingPostalcode);
        txtBillingCity = (EditText) findViewById(R.id.txtBillingCity);
        removeKeypadFromDatePicker();

        gson = new Gson();
        String currentBookingString = (String) getIntent().getSerializableExtra(IntentExtraName);
        currentBooking = gson.fromJson(currentBookingString, new TypeToken<CurrentBooking>() {
        }.getType());
    }

    private void removeKeypadFromDatePicker() {
        txtBirthday.setInputType(InputType.TYPE_NULL);
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
                txtDate.setText(dateFormat.format(newDate.getTime()));


            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    @NonNull
    private CheckBox.OnCheckedChangeListener ShowBillingAdress() {
        return new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    llBillingAddress.setVisibility(View.VISIBLE);
                }
                if (!isChecked) {
                    llBillingAddress.setVisibility(View.GONE);
                }
            }
        };
    }

    public void OnClickBooking(View view) throws ParseException {
        Address contactAddress = new Address();
        Address billingAddress = new Address();
        Guest guest = new Guest();

        if (txtFirstname.getText().toString().isEmpty()) {
            txtFirstname.requestFocus();
            txtFirstname.setError("Darf nicht leer sein!");
        } else {
            guest.setFirstname(txtFirstname.getText().toString());
        }

        if (txtLastname.getText().toString().isEmpty()) {
            txtLastname.requestFocus();
            txtLastname.setError("Darf nicht leer sein!");
        } else {
            guest.setLastname(txtLastname.getText().toString());
        }

        if (txtBirthday.getText().toString().isEmpty()) {
            txtBirthday.requestFocus();
            txtBirthday.setError("Darf nicht leer sein!");
        } else {
            guest.setBirthday(dateFormat.parse(txtBirthday.getText().toString()));
        }

        if (txtBirthplace.getText().toString().isEmpty()) {
            txtBirthplace.requestFocus();
            txtBirthplace.setError("Darf nicht leer sein!");
        } else {
            guest.setBirthplace(txtBirthplace.getText().toString());
        }

        if (!txtBirthday.getText().toString().isEmpty()) {
            guest.setBirthday(dateFormat.parse(txtBirthday.getText().toString()));
        }

        if (!txtContactStreet.getText().toString().isEmpty()) {
            contactAddress.setStreet(txtContactStreet.getText().toString());
        }

        if (!txtContactPostalcode.getText().toString().isEmpty()) {
            contactAddress.setPostalCode(txtContactPostalcode.getText().toString());
        }

        if (!txtContactCity.getText().toString().isEmpty()) {
            contactAddress.setCity(txtContactCity.getText().toString());
        }
        if (cbAddBillingAddress.isChecked()) {

            if (!txtBillingStreet.getText().toString().isEmpty()) {
                billingAddress.setPostalCode(txtBillingStreet.getText().toString());
            }
            if (!txtBillingCity.getText().toString().isEmpty()) {
                billingAddress.setPostalCode(txtBillingCity.getText().toString());
            }
            if (!txtBillingPostalcode.getText().toString().isEmpty()) {
                billingAddress.setPostalCode(txtBillingPostalcode.getText().toString());
            }
        }else
        {
            billingAddress = null;
        }

        guest.setBillingAddress(billingAddress);
        guest.setContactAddress(contactAddress);
        currentBooking.setGuest(guest);

        Intent intent = new Intent(Booking.this, CheckBooking.class);
        intent.putExtra(IntentExtraName, gson.toJson(currentBooking));
        startActivity(intent);
    }

    //region GUI-Methods
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
