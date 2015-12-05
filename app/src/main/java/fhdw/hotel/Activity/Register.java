package fhdw.hotel.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.OutputStreamWriter;

import fhdw.hotel.DomainModel.Guest;
import fhdw.hotel.R;

public class Register extends AppCompatActivity {
    private final static String STORETEXT = "storetext.txt";

    EditText email;
    EditText lastname;
    EditText firstname;
    EditText pw;
    EditText rpt_pw;
    Guest besucher;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = (EditText) findViewById(R.id.email);
        lastname = (EditText) findViewById(R.id.lastname);
        firstname = (EditText) findViewById(R.id.firstname);
        pw = (EditText) findViewById(R.id.enter_password);
        rpt_pw = (EditText) findViewById(R.id.repeat_password);

    }

    public void RegisterNewUser(View v) {

        if(email.getText().toString().isEmpty()) {
            email.requestFocus();
            email.setError("Bitte Email eingeben");
        }

        if(lastname.getText().toString().isEmpty()) {
            lastname.requestFocus();
            lastname.setError("Bitte Nachnamen eingeben");
        }

        if(firstname.getText().toString().isEmpty()) {
            firstname.requestFocus();
            firstname.setError("Bitte Vornamen eingeben");
        }

        if(!checkPassWordAndConfirmPassword(pw.getText().toString(),rpt_pw.getText().toString())) {
            rpt_pw.requestFocus();
            rpt_pw.setError("Die Passwörter stimmen nicht überein");
        }


        besucher = new Guest();
        besucher.setEmailaddress(email.toString());
        besucher.setLastname(lastname.toString());
        besucher.setFirstname(firstname.toString());


    }

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
}
