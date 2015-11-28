package fhdw.hotel.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import fhdw.hotel.BLL.Async.IListener.IAsyncHotelListener;
import fhdw.hotel.DomainModel.Hotel;
import fhdw.hotel.R;



public class SearchFormular extends AppCompatActivity implements IAsyncHotelListener {
    private SimpleDateFormat dateFormatter;

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
    }

    /**
     * Set Type of Date Text Boxes to NULL, otherwise the keyboard will open every time focus is set to the input fields.
     */
    private void removeKeypadFromDatePicker() {
        EditText txtArrivalDate = (EditText) findViewById(R.id.txtArrivalDate);
        EditText txtDepartureDate = (EditText) findViewById(R.id.txtDepartureDate);

        txtArrivalDate.setInputType(InputType.TYPE_NULL);
        txtDepartureDate.setInputType(InputType.TYPE_NULL);
    }
    // endregion

    // region onClickMethods
    /**
     * @param view
     */
    public void SearchRoomOnClick(View view) {
        Intent intent = new Intent(SearchFormular.this, RoomSelection.class);

        EditText txtSingleRoomCount = (EditText) findViewById(R.id.txtSingleRoomCount);
        int singleRoomCount = Integer.parseInt(txtSingleRoomCount.getText().toString());
        intent.putExtra("singleRoomCount", singleRoomCount);

        EditText txtDoubleRoomCount = (EditText) findViewById(R.id.txtDoubleRoomCount);
        int doubleRoomCount = Integer.parseInt(txtDoubleRoomCount.getText().toString());
        intent.putExtra("doubleRoomCount", doubleRoomCount);

        EditText txtFamilyRoomCount = (EditText) findViewById(R.id.txtFamilyRoomCount);
        int familyRoomCount = Integer.parseInt(txtFamilyRoomCount.getText().toString());
        intent.putExtra("familyRoomCount", familyRoomCount);

        startActivity(intent);
    }

    /**
     * Show the Datepicker and set the selected date into the arrival textbox.
     * @param view
     */
    public void getArrivalDate(View view) {
        showDatePicker((EditText) findViewById(R.id.txtArrivalDate));
    }

    /**
     * Show the Datepicker, check the date and set the selected date into the departure textbox.
     * @param view
     */
    public void getDepartureDate(View view) {
        showDatePicker((EditText) findViewById(R.id.txtDepartureDate));
        // TODO: Check if DepartureDate is before ArrivalDate!
    }
    // endregion

    // region HelperMethods
    /**
     * @param txtArrivalDate
     */
    private void showDatePicker(final EditText txtArrivalDate) {
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                txtArrivalDate.setText(dateFormatter.format(newDate.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
    // endregion

    //region GUI-Methods
    /**
     * Inflate the menu; this adds items to the action bar if it is present.
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

    // region Async-Methods
    public void getHotelCollection() {
        new fhdw.hotel.BLL.Async.Hotel.GetCollection(null, this).execute();
    }

    @Override
    public void GetCollectionComplete(ArrayList<Hotel> p_result) {

    }

    @Override
    public void GetComplete(Hotel p_result) {

    }
    // endregion
}
