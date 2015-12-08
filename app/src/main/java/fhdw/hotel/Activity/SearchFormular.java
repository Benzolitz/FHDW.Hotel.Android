package fhdw.hotel.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import fhdw.hotel.BLL.Async.Guest.InsertGuest;
import fhdw.hotel.BLL.Async.IListener.IAsyncGuestListener;
import fhdw.hotel.BLL.Async.IListener.IAsyncHotelListener;
import fhdw.hotel.BLL.Async.IListener.IAsyncRoomListener;
import fhdw.hotel.DomainModel.Address;
import fhdw.hotel.DomainModel.CurrentBooking;
import fhdw.hotel.DomainModel.Guest;
import fhdw.hotel.DomainModel.Hotel;
import fhdw.hotel.DomainModel.Room;
import fhdw.hotel.DomainModel.Booking;
import fhdw.hotel.R;

public class SearchFormular extends AppCompatActivity implements IAsyncHotelListener, IAsyncGuestListener, IAsyncRoomListener {
    private SimpleDateFormat dateFormatter;
    EditText txtDepartureDate;
    EditText txtArrivalDate;
    Spinner spn_cities;
    Spinner spn_personCount;
    ArrayList<Hotel> selectedHotel;
    int hotelId;
    ArrayList<Room> vacantRooms;
    EditText singleRooms;
    EditText doubleRooms;
    EditText familyRooms;

    Hotel myBookingHotel;

    // region Initialization

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search_formular);

        dateFormatter = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMANY);
        removeKeypadFromDatePicker();

        getHotelCollection();
        selectedHotel = new ArrayList<>();

        myBookingHotel = new Hotel();

//        insertGuestTest();
        singleRooms = (EditText) findViewById(R.id.txtSingleRoomCount);
        doubleRooms = (EditText) findViewById(R.id.txtDoubleRoomCount);
        familyRooms = (EditText) findViewById(R.id.txtFamilyRoomCount);
        spn_personCount = (Spinner) findViewById(R.id.spnPersonCount);
        vacantRooms = new ArrayList<>();
        if (spn_cities != null) {
            for (int i = 0; i < selectedHotel.size(); i++) {
                if (spn_cities.getSelectedItem().toString().equals(selectedHotel.get(i).getAddress().getCity())) {
                    hotelId = selectedHotel.get(i).getId();
                }
            }
        }
    }

    /**
     * Set Type of Date Text Boxes to NULL, otherwise the keyboard will open every time focus is set to the input fields.
     */
    private void removeKeypadFromDatePicker() {
        txtArrivalDate = (EditText) findViewById(R.id.txtArrivalDate);
        txtDepartureDate = (EditText) findViewById(R.id.txtDepartureDate);

        txtArrivalDate.setInputType(InputType.TYPE_NULL);
        txtDepartureDate.setInputType(InputType.TYPE_NULL);
    }
    // endregion

    // region onClickMethods

    public void ShowHotelInfo(View view) {
        String strasse = "";
        String plz = "";
        String stadt = "";
        String name = "";


        for (int i = 0; i < selectedHotel.size(); i++) {
            if (selectedHotel.get(i).getId() == hotelId) {
                name = selectedHotel.get(i).getName();
                strasse = selectedHotel.get(i).getAddress().getStreet();
                plz = selectedHotel.get(i).getAddress().getPostalCode();
                stadt = selectedHotel.get(i).getAddress().getCity();

            }
        }

        AlertDialog alertDialog;
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Hotelinfo");
        alertDialog.setMessage(name + "\n" + strasse + "\n" + stadt + " " + plz);
        alertDialog.show();
    }

    /**
     * @param view
     */
    public void SearchRoomOnClick(View view) throws ParseException {
        Intent intent = new Intent(SearchFormular.this, RegisterDescision.class);
//        getFreeRoomsCollection();


        /**
         * Get values from room amount selection
         */
        EditText txtSingleRoomCount = (EditText) findViewById(R.id.txtSingleRoomCount);
        int singleRoomCount = Integer.parseInt(txtSingleRoomCount.getText().toString());

        EditText txtDoubleRoomCount = (EditText) findViewById(R.id.txtDoubleRoomCount);
        int doubleRoomCount = Integer.parseInt(txtDoubleRoomCount.getText().toString());

        EditText txtFamilyRoomCount = (EditText) findViewById(R.id.txtFamilyRoomCount);
        int familyRoomCount = Integer.parseInt(txtFamilyRoomCount.getText().toString());

        int selectedRoomCount = singleRoomCount + doubleRoomCount + familyRoomCount;
        if (Integer.parseInt(spn_personCount.getSelectedItem().toString()) < selectedRoomCount) {
            Toast.makeText(this, "Mehr Zimmer als Gäste!", Toast.LENGTH_SHORT).show();
        } else {
            if (selectedRoomCount == 0) {
                Toast.makeText(this, "Bitte Zimmeranzahl eintragen", Toast.LENGTH_SHORT).show();
            }
        }

        /**
         * Check if arrival and departure dates are set
         */
        if (txtArrivalDate.getText().toString().isEmpty() || txtDepartureDate.getText().toString().isEmpty()) {


            AlertDialog alertDialog;
            alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Hotelinfo");
            alertDialog.setMessage("Bitte Ankunfts- und Abreisedatum angeben!");
            alertDialog.show();


        }
        else if (dateFormatter.parse(txtDepartureDate.getText().toString()).before(dateFormatter.parse(txtArrivalDate.getText().toString())))
        {
            AlertDialog alertDialog;
            alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Falsche Datumsangabe");
            alertDialog.setMessage("Abreisedatum muss nach dem Ankunftsdatum liegen!");
            alertDialog.show();
        }
        else {
            CurrentBooking.setArrival(dateFormatter.parse(txtArrivalDate.getText().toString()));
            CurrentBooking.setDeparture(dateFormatter.parse(txtDepartureDate.getText().toString()));
            myBookingHotel.setId(hotelId);
            CurrentBooking.setHotel(myBookingHotel);
            CurrentBooking.setgetSingleRoomCnt(singleRoomCount);
            CurrentBooking.setDoubleRoomCnt(doubleRoomCount);
            CurrentBooking.setFamilyRoomCnt(familyRoomCount);

            try {
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        /**
         * Check if data coming from server isn't empty and
         * there are enough rooms available for the selected room amount and
         * the selcted rooms have enough beds for the selected amount of guests
         */
        /*
        if (!vacantRooms.isEmpty()) {
            int selectedRoomsBeds = singleRoomCount + (doubleRoomCount * 2) + (familyRoomCount * 5);
            int vacantSingle = 0;
            int vacantDouble = 0;
            int vacantFamily = 0;

            for (int i = 0; i < vacantRooms.size(); i++) {
                String roomType = vacantRooms.get(i).getType().toString();

                switch (roomType) {
                    case "Single":
                        vacantSingle++;
                        break;
                    case "Double":
                        vacantDouble++;
                        break;
                    case "Family":
                        vacantFamily++;
                        break;
                    default:
                        break;
                }

            }


            if (vacantSingle != singleRoomCount || vacantDouble != doubleRoomCount || vacantFamily != familyRoomCount) {
                //prompt that there are not enough rooms

                AlertDialog alertDialog;
                alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("Nicht genug der gewünschten Zimmertypen vorhanden!");
                alertDialog.setMessage("Noch verfügbar:\n " + vacantSingle + " Einzelzimmer,\n" + vacantDouble + " Doppelzimmer,\n" + vacantFamily + "Familienzimmer");
                alertDialog.show();

            }
        } else {
            AlertDialog alertDialog;
            alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Keine Zimmer Frei!");
            alertDialog.setMessage("lol");
            alertDialog.show();
        }
        */


        // TODO add all search relevant items to currentbooking


    }

    /**
     * Show the Datepicker and set the selected date into the arrival textbox.
     *
     * @param view
     */
    public void getArrivalDate(View view) {
        showDatePicker((EditText) findViewById(R.id.txtArrivalDate));
    }

    /**
     * Show the Datepicker, check the date and set the selected date into the departure textbox.
     *
     * @param view
     */
    public void getDepartureDate(View view) throws ParseException {
        showDatePicker((EditText) findViewById(R.id.txtDepartureDate));


        // TODO: Check if DepartureDate is before ArrivalDate!#


    }
    // endregion

    // region HelperMethods

    /**
     * @param txtDate
     */
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
    // endregion

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

    //endregion

    // region Async-Methods
    public void getHotelCollection() {
        new fhdw.hotel.BLL.Async.Hotel.GetCollection(this).execute();
    }

    public void getFreeRoomsCollection() {
        new fhdw.hotel.BLL.Async.Room.GetCollection(this).execute(Integer.toString(hotelId), txtArrivalDate.getText().toString(), txtDepartureDate.getText().toString());
    }

    @Override
    public void GetHotelCollectionComplete(ArrayList<Hotel> p_result) {
        if (p_result == null) return;

        ArrayList<String> adrLst = new ArrayList<>();

        for (int i = 0; i < p_result.size(); i++) {
            selectedHotel.add(p_result.get(i));
            adrLst.add(p_result.get(i).toString());
        }
        spn_cities = (Spinner) findViewById(R.id.spnCity);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, adrLst);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_cities.setAdapter(adapter);
    }

    @Override
    public void GetComplete(Hotel p_result) {

    }

//    private void insertGuestTest() {
//        Guest guest = new Guest();
//        guest.Firstname = "Lucas";
//        guest.Lastname = "Engel";
//        guest.Emailaddress = "engellucas@gmx.de";
//        guest.Password = "123456";
//
//        // doInBackground only likes String-Parameter.
//        // Convert objects to JSON-String
//        String json = new Gson().toJson(guest);
//        new fhdw.hotel.BLL.Async.Guest.InsertGuest(this).execute(json);
//    }

    @Override
    public void InsertGuestTest(Guest p_guest) {
        Guest guest = p_guest;
    }

    @Override
    public void GetRoomCollectionComplete(ArrayList<Room> p_result) {
        if (p_result == null) return;

        vacantRooms = p_result;


    }

    @Override
    public void GetRoom(Room p_result) {

    }


    // endregion


}
