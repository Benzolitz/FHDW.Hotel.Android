package fhdw.hotel;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;



import android.app.AlertDialog;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;

import android.widget.Toast;



public class PrototypeSuchform extends AppCompatActivity {

    private static final int DIALOG_ALERT = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prototype_suchform);

        Button searchBtn = (Button) findViewById(R.id.search);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrototypeSuchform.this, AccordeonZimmer.class);
                startActivity(intent);
            }
        });
        Button alertDialog = (Button)findViewById(R.id.alertDialogBtn);
        alertDialog.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DIALOG_ALERT);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DIALOG_ALERT:
                Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Bestätigen sie ihre Auswahl \n\n 4 Personen\n\n 1 Doppelzimmer Suite \n\n 2 Einzelzimmer Standard");
                builder.setCancelable(true);
                builder.setPositiveButton("Das passt", new OkOnClickListener());
                builder.setNegativeButton("Passt nicht", new CancelOnClickListener());
                AlertDialog dialog = builder.create();
                dialog.show();
        }
        return super.onCreateDialog(id);
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
    private final class CancelOnClickListener implements
            DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int which) {
            Toast.makeText(getApplicationContext(), "Activity will continue", Toast.LENGTH_LONG).show();
        }
    }

    private final class OkOnClickListener implements
            DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int which) {
            Toast.makeText(getApplicationContext(), "Just kidding", Toast.LENGTH_LONG).show();
        }
    }
}
