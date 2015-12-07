package fhdw.hotel.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.gson.Gson;

import java.util.Date;

import fhdw.hotel.BLL.Async.IListener.IAsyncGuestListener;
import fhdw.hotel.DomainModel.Address;
import fhdw.hotel.DomainModel.Guest;
import fhdw.hotel.R;

/**
 * @Author Artur Briem
 * Activity for registration of a user and booking at same time
 */
public class Register extends AppCompatActivity implements IAsyncGuestListener {

    EditText email;
    EditText lastname;
    EditText firstname;
    EditText pw;
    EditText rpt_pw;
    EditText bday;
    EditText phone;
    EditText city;
    EditText plz;

    Guest guest;


    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = (EditText) findViewById(R.id.email);
        lastname = (EditText) findViewById(R.id.lastname);
        firstname = (EditText) findViewById(R.id.firstname);
        pw = (EditText) findViewById(R.id.enter_password);
        rpt_pw = (EditText) findViewById(R.id.repeat_password);
        bday = (EditText) findViewById(R.id.birthday);
        phone = (EditText) findViewById(R.id.phone);
        city = (EditText) findViewById(R.id.city);
        plz = (EditText) findViewById(R.id.plz);

    }

    /**
     *
     * @param v
     */
    public void RegisterNewUserAndBook(View v) {

        guest = new Guest();
        guest.ContactAddress = new Address();

        /**
         * Validation of all req. input fields
         */
        if(email.getText().toString().isEmpty()) {
            email.requestFocus();
            email.setError("Bitte Email eingeben");
        }
        else {
            guest.Emailaddress = email.getText().toString();
        }

        if(lastname.getText().toString().isEmpty()) {
            lastname.requestFocus();
            lastname.setError("Bitte Nachnamen eingeben");
        }
        else {
            guest.Lastname = lastname.getText().toString();
        }

        if(firstname.getText().toString().isEmpty()) {
            firstname.requestFocus();
            firstname.setError("Bitte Vornamen eingeben");
        }
        else {
            guest.Firstname = firstname.getText().toString();
        }

        if(city.getText().toString().isEmpty()) {
            city.requestFocus();
            city.setError("Bitte Ort eingeben");
        }
        else {
            guest.ContactAddress.City = city.getText().toString();
        }

        if(plz.getText().toString().isEmpty()) {
            plz.requestFocus();
            plz.setError("Bitte PLZ eingeben");
        }
        else {
            guest.ContactAddress.PostalCode = plz.getText().toString();
        }

        if(pw.getText().toString().isEmpty()){
            pw.requestFocus();
            pw.setError("Bitte ein Passwort festlegen!");
        }

        if(!checkPassWordAndConfirmPassword(pw.getText().toString(),rpt_pw.getText().toString())) {
            rpt_pw.requestFocus();
            rpt_pw.setError("Die Passwörter stimmen nicht überein");
        }
        else {
            guest.Password = pw.getText().toString();
        }

        InsertGuestTest(guest);

    }

    /**
     *
     * @param password
     * @param confirmPassword
     * @return pstatus
     *
     * Checks password with the confirmation password and returns a boolean
     */
    public boolean checkPassWordAndConfirmPassword(String password,String confirmPassword)
    {
        boolean pstatus = false;
        if (confirmPassword != null && password != null)
        {
            if (password.equals(confirmPassword))
            {
                pstatus = true;
            }
        }
        return pstatus;
    }

    @Override
    public void InsertGuestTest(Guest p_guest) {
        String json = new Gson().toJson(p_guest);
        new fhdw.hotel.BLL.Async.Guest.InsertGuest(this).execute(json);
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
        switch(item.getItemId()) {
            case R.id.login_action:
                Intent intent = new Intent(this, Login.class);
                this.startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    //endregion
}
