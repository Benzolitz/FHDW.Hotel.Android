package fhdw.hotel.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import fhdw.hotel.BLL.Async.IListener.IAsyncHotelListener;
import fhdw.hotel.BLL.Async.IListener.IAsyncRoomListener;
import fhdw.hotel.DomainModel.CurrentBooking;
import fhdw.hotel.DomainModel.Hotel;
import fhdw.hotel.DomainModel.Room;
import fhdw.hotel.R;

public class SearchFormular extends AppCompatActivity implements IAsyncHotelListener, IAsyncRoomListener {
    // region Member
    private String IntentExtraName = "CurrentBooking";

    private SimpleDateFormat dateFormatter;

    private CurrentBooking currentBooking;

    private Spinner spnCities;
    private Spinner spnPersonCount;

    private EditText txtArrivalDate;
    private EditText txtDepartureDate;
    private EditText txtSingleRoomCount;
    private EditText txtDoubleRoomCount;
    private EditText txtFamilyRoomCount;

    private Hotel selectedHotel;
    private ArrayList<Hotel> hotelList;

    private ArrayList<Room> vacantRooms;

    private Gson gson;
    // endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_formular);

        initializeMember();
        removeKeypadFromDatePicker();
        getHotelCollection();
    }

    private void initializeMember() {
        gson = new Gson();

        dateFormatter = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMANY);

        currentBooking = new CurrentBooking();

        spnCities = (Spinner) findViewById(R.id.spnCity);
        spnCities.setOnItemSelectedListener(spnCitiesOnItemSelectedListener());

        spnPersonCount = (Spinner) findViewById(R.id.spnPersonCount);

        txtArrivalDate = (EditText) findViewById(R.id.txtArrivalDate);
        txtDepartureDate = (EditText) findViewById(R.id.txtDepartureDate);
        txtSingleRoomCount = (EditText) findViewById(R.id.txtSingleRoomCount);
        txtDoubleRoomCount = (EditText) findViewById(R.id.txtDoubleRoomCount);
        txtFamilyRoomCount = (EditText) findViewById(R.id.txtFamilyRoomCount);

        selectedHotel = new Hotel();
        hotelList = new ArrayList<>();

        vacantRooms = new ArrayList<>();

        String currentBookingString = (String) getIntent().getSerializableExtra(IntentExtraName);
        if (currentBookingString != null && !currentBookingString.isEmpty()) {
            currentBooking = gson.fromJson(currentBookingString, new TypeToken<CurrentBooking>() {
            }.getType());
            selectedHotel = currentBooking.getHotel();

            int hotelIndex = hotelList.indexOf(selectedHotel);
            if (hotelIndex != -1) spnCities.setSelection(hotelIndex);

            spnPersonCount.setSelection(currentBooking.getPersonCount() - 1);

            currentBooking = gson.fromJson(currentBookingString, new TypeToken<CurrentBooking>() {
            }.getType());
            txtArrivalDate.setText(currentBooking.getArrival().toString());
            txtDepartureDate.setText(currentBooking.getDeparture().toString());
            txtSingleRoomCount.setText(currentBooking.getSingleRoomCnt());
            txtDoubleRoomCount.setText(currentBooking.getDoubleRoomCnt());
            txtFamilyRoomCount.setText(currentBooking.getFamilyRoomCnt());
        }
    }

    private void testBooking() {
        // new fhdw.hotel.BLL.Async.Booking.SendBooking(this).execute(json);
    }

    private void removeKeypadFromDatePicker() {
        txtArrivalDate.setInputType(InputType.TYPE_NULL);
        txtDepartureDate.setInputType(InputType.TYPE_NULL);
    }

    public void ShowHotelInfo(View view) {
        String message = "";
        message += "Hotelinformation:\n";
        message += "Hier könnten Informationen zu dem Hotel stehen!\n\n";
        message += "Adresse:\n";
        message += "Hotel " + selectedHotel.Name + "\n";
        message += selectedHotel.getAddress().getStreet() + "\n";
        message += selectedHotel.getAddress().getPostalCode() + " " + selectedHotel.getAddress().getCity();

        AlertDialog alertDialog;
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Hotel " + selectedHotel.Name + " (" + selectedHotel.getAddress().getCity() + ")");
        alertDialog.setMessage(message);
        alertDialog.show();
    }

    public void SearchRoomOnClick(View view) throws ParseException {
        boolean cancel = false;

        int singleRoomCount = Integer.parseInt(txtSingleRoomCount.getText().toString());
        int doubleRoomCount = Integer.parseInt(txtDoubleRoomCount.getText().toString());
        int familyRoomCount = Integer.parseInt(txtFamilyRoomCount.getText().toString());

        int sumRoomCount = singleRoomCount + doubleRoomCount + familyRoomCount;
        if (Integer.parseInt(spnPersonCount.getSelectedItem().toString()) < sumRoomCount) {
            Toast.makeText(this, "Achtung! Sie haben mehr Zimmer als Gäste ausgewählt.", Toast.LENGTH_LONG);
        } else if (sumRoomCount == 0) {
            cancel = true;
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Zimmeranzahl");
            alertDialog.setMessage("Sie müssen eine Anzahl von Zimmern eintragen!");
            alertDialog.show();
        }

        if (txtArrivalDate.getText().toString().isEmpty() || txtDepartureDate.getText().toString().isEmpty()) {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Datum");
            alertDialog.setMessage("Bitte Ankunfts- und Abreisedatum angeben!");
            alertDialog.show();
            cancel = true;
        } else if (dateFormatter.parse(txtDepartureDate.getText().toString()).before(dateFormatter.parse(txtArrivalDate.getText().toString()))) {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Datum");
            alertDialog.setMessage("Abreisedatum muss nach dem Ankunftsdatum liegen!");
            alertDialog.show();
            cancel = true;

        }

        if (!cancel) {
            currentBooking.setArrival(dateFormatter.parse(txtArrivalDate.getText().toString()));
            currentBooking.setDeparture(dateFormatter.parse(txtDepartureDate.getText().toString()));
            currentBooking.setHotel(selectedHotel);
            currentBooking.setPersonCount(spnCities.getSelectedItemPosition() + 1);
            currentBooking.setSingleRoomCnt(singleRoomCount);
            currentBooking.setDoubleRoomCnt(doubleRoomCount);
            currentBooking.setFamilyRoomCnt(familyRoomCount);

            try {
                Intent intent = new Intent(SearchFormular.this, RegisterDescision.class);
                intent.putExtra(IntentExtraName, gson.toJson(currentBooking));
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void getArrivalDate(View view) {
        showDatePicker((EditText) findViewById(R.id.txtArrivalDate));
    }

    public void getDepartureDate(View view) throws ParseException {
        showDatePicker((EditText) findViewById(R.id.txtDepartureDate));

    }

    private void showDatePicker(final EditText txtDate) {
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                txtDate.setText(dateFormatter.format(newDate.getTime()));


            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private AdapterView.OnItemSelectedListener spnCitiesOnItemSelectedListener() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0; i < hotelList.size(); i++) {
                    if (!spnCities.getSelectedItem().toString().equals(hotelList.get(i).getAddress().getCity()))
                        continue;
                    selectedHotel = hotelList.get(i);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.login_action:
                Intent intent = new Intent(this, Login.class);
                intent.putExtra(IntentExtraName, gson.toJson(currentBooking));
                this.startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void getHotelCollection() {
        new fhdw.hotel.BLL.Async.Hotel.GetCollection(this).execute();
    }

    @Override
    public void GetHotelCollectionComplete(ArrayList<Hotel> p_result) {
        if (p_result == null || p_result.size() == 0) return;
        hotelList = p_result;
        selectedHotel = hotelList.get(0);

        ArrayList<String> adrLst = new ArrayList<>();

        for (int i = 0; i < hotelList.size(); i++) {
            adrLst.add(hotelList.get(i).toString());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, adrLst);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCities.setAdapter(adapter);
    }

    @Override
    public void GetComplete(Hotel p_result) {

    }

    @Override
    public void GetRoomCollectionComplete(ArrayList<Room> p_result) {
        if (p_result == null) return;
        vacantRooms = p_result;
    }

    @Override
    public void GetRoom(Room p_result) {

    }
}
