package fhdw.hotel;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import android.app.AlertDialog;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;

import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


public class PrototypeSuchform extends AppCompatActivity implements View.OnClickListener {

    private static final int DIALOG_ALERT = 10;

    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;
    private EditText fromDateEtxt;
    private EditText toDateEtxt;


    private SimpleDateFormat dateFormatter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_prototype_suchform);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.GERMANY);

        findViewsById();

        setDateTimeField();

        Button searchBtn = (Button) findViewById(R.id.search);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<Zimmer> zimmerArten = new ArrayList<>();
                Zimmer einzelZimmer;
                Zimmer doppelZimmer;
                Zimmer famZimmer;

                EditText txtEinzel = (EditText) findViewById(R.id.einzelZimmer);
                EditText txtDoppel = (EditText) findViewById(R.id.doppelZimmer);
                EditText txtFam = (EditText) findViewById(R.id.famZimmer);

                int anzEinzel = Integer.parseInt(txtEinzel.getText().toString());
                int anzDoppel = Integer.parseInt(txtDoppel.getText().toString());
                int anzFam = Integer.parseInt(txtFam.getText().toString());

                for (int i = 0; i < anzEinzel; i++) {
                    einzelZimmer = new Zimmer("Einzelzimmer");
                    zimmerArten.add(i, einzelZimmer);
                }

                for (int i = 0; i < anzDoppel; i++) {
                    doppelZimmer = new Zimmer("Doppelzimmer");
                    zimmerArten.add(i, doppelZimmer);
                }

                for (int i = 0; i < anzFam; i++) {
                    famZimmer = new Zimmer("Familienzimmer");
                    zimmerArten.add(i, famZimmer);
                }

                Intent intent = new Intent(PrototypeSuchform.this, AccordeonZimmer.class);
                intent.putExtra("allRooms", zimmerArten);
                startActivity(intent);
            }
        });
    }

    private void findViewsById() {
        fromDateEtxt = (EditText) findViewById(R.id.inputStartDate);
        fromDateEtxt.setInputType(InputType.TYPE_NULL);
        fromDateEtxt.requestFocus();

        toDateEtxt = (EditText) findViewById(R.id.inputEndDate);
        toDateEtxt.setInputType(InputType.TYPE_NULL);
    }

    private void setDateTimeField() {
        fromDateEtxt.setOnClickListener(this);
        toDateEtxt.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                fromDateEtxt.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        toDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                toDateEtxt.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_prototype_suchform, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void onClick(View view) {
        if (view == fromDateEtxt) {
            fromDatePickerDialog.show();
        } else if (view == toDateEtxt) {
            toDatePickerDialog.show();
        }
    }
}
