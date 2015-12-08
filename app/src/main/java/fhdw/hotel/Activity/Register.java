package fhdw.hotel.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import fhdw.hotel.BLL.Async.IListener.IAsyncGuestListener;
import fhdw.hotel.DomainModel.Address;
import fhdw.hotel.DomainModel.CurrentBooking;
import fhdw.hotel.DomainModel.Guest;
import fhdw.hotel.R;

public class Register extends AppCompatActivity implements IAsyncGuestListener {
    private String IntentExtraName = "CurrentBooking";
    private CurrentBooking currentBooking;
    private Gson gson;

    private SimpleDateFormat dateFormatter;

    private EditText txtEmail;
    private EditText txtLastname;
    private EditText txtFirstname;
    private EditText txtPassword;
    private EditText txtPasswordRepeat;
    private EditText txtBirthday;
    private EditText txtBirthplace;
    private EditText txtTelephone;
    private EditText txtContactStreet;
    private EditText txtContactCity;
    private EditText txtContactPostalcode;


    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dateFormatter = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMANY);

        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtLastname = (EditText) findViewById(R.id.txtLastname);
        txtFirstname = (EditText) findViewById(R.id.txtFirstname);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtPasswordRepeat = (EditText) findViewById(R.id.txtPasswordRepeat);
        txtBirthday = (EditText) findViewById(R.id.txtBirthday);
        txtBirthplace = (EditText) findViewById(R.id.txtBirthplace);
        txtTelephone = (EditText) findViewById(R.id.txtTelephone);
        txtContactStreet = (EditText) findViewById(R.id.txtContactStreet);
        txtContactCity = (EditText) findViewById(R.id.txtContactCity);
        txtContactPostalcode = (EditText) findViewById(R.id.txtPostlcode);

        gson = new Gson();
        String currentBookingString = (String) getIntent().getSerializableExtra(IntentExtraName);
        currentBooking = gson.fromJson(currentBookingString, new TypeToken<CurrentBooking>() {
        }.getType());
    }

    public void RegisterNewUser(View v) throws ParseException {
        boolean error = false;

        Guest guest = new Guest();
        Address contactAddress = new Address();

        if (txtEmail.getText().toString().isEmpty()) {
            txtEmail.requestFocus();
            txtEmail.setError("Bitte Email eingeben");
            error = true;
        } else {
            guest.setEmailaddress(txtEmail.getText().toString());
        }

        if (txtLastname.getText().toString().isEmpty()) {
            txtLastname.requestFocus();
            txtLastname.setError("Bitte Nachnamen eingeben");
            error = true;
        } else {
            guest.setLastname(txtLastname.getText().toString());
        }

        if (txtFirstname.getText().toString().isEmpty()) {
            txtFirstname.requestFocus();
            txtFirstname.setError("Bitte Vornamen eingeben");
            error = true;
        } else {
            guest.setFirstname(txtFirstname.getText().toString());
        }

        if (!txtTelephone.getText().toString().isEmpty()) {
            guest.setTelephone(txtTelephone.getText().toString());
        }

        if (txtBirthday.getText().toString().isEmpty()) {
            txtBirthday.requestFocus();
            txtBirthday.setError("Bitte Geburtsdatum eingeben");
            error = true;
        } else {
            guest.setBirthday(dateFormatter.parse(txtBirthday.getText().toString()));
        }

        if (txtBirthplace.getText().toString().isEmpty()) {
            txtBirthplace.requestFocus();
            txtBirthplace.setError("Bitte Geburtsort eingeben");
            error = true;
        } else {
            guest.setBirthplace(txtBirthplace.getText().toString());
        }

        if (txtContactCity.getText().toString().isEmpty()) {
            txtContactCity.requestFocus();
            txtContactCity.setError("Bitte Ort eingeben");
            error = true;
        } else {
            contactAddress.setCity(txtContactCity.getText().toString());
        }

        if (txtContactPostalcode.getText().toString().isEmpty()) {
            txtContactPostalcode.requestFocus();
            txtContactPostalcode.setError("Bitte PLZ eingeben");
            error = true;
        } else {
            contactAddress.setPostalCode(txtContactPostalcode.getText().toString());
        }

        if (txtContactStreet.getText().toString().isEmpty()) {
            txtContactStreet.requestFocus();
            txtContactStreet.setError("Bitte eine Straße angeben");
            error = true;
        } else {
            contactAddress.setStreet(txtContactStreet.getText().toString());
        }

        if (txtPassword.getText().toString().isEmpty()) {
            txtPassword.requestFocus();
            txtPassword.setError("Bitte single_standard_room Passwort festlegen!");
            error = true;
        }

        if (!checkPassWordAndConfirmPassword(txtPassword.getText().toString(), txtPasswordRepeat.getText().toString())) {
            txtPasswordRepeat.requestFocus();
            txtPasswordRepeat.setError("Die Passwörter stimmen nicht überein");
            error = true;
        } else {
            guest.setPassword(txtPassword.getText().toString());
        }
        guest.setContactAddress(contactAddress);

        if (!error) {
            new fhdw.hotel.BLL.Async.Guest.RegisterNewUser(this).execute(gson.toJson(guest));
        }
    }

    /**
     * @param password
     * @param confirmPassword
     * @return pstatus
     * <p/>
     * Checks password with the confirmation password and returns a boolean
     */
    public boolean checkPassWordAndConfirmPassword(String password, String confirmPassword) {
        boolean pstatus = false;
        if (confirmPassword != null && password != null) {
            if (password.equals(confirmPassword)) {
                pstatus = true;
            }
        }
        return pstatus;
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
        switch (item.getItemId()) {
            case R.id.login_action:
                Intent intent = new Intent(this, Login.class);
                this.startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    @Override
    public void CheckLoginComplete(Guest p_guest) {

    }

    @Override
    public void RegisterNewUserComplete(Guest p_guest) {
        if (p_guest != null) {
            Intent intent = new Intent(Register.this, CheckBooking.class);
            currentBooking.setGuest(p_guest);
            intent.putExtra(IntentExtraName, gson.toJson(currentBooking));
            startActivity(intent);
        } else {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Registierung fehlgeschlagen!");
            alertDialog.setMessage("Die Registrierung ist fehlgeschlagen. Bitte überprüfen Sie Ihre Daten und versuchen Sie es erneut!");
            alertDialog.show();
        }
    }
}