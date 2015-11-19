package fhdw.hotel;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;



public class AccordeonZimmer extends AppCompatActivity {
    Button b;
    ScrollView scrollview;
    Toolbar toolbar;
    LinearLayout magicLL;
    ListView magicLV;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_accordeon_zimmer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accordeon_zimmer);
//      toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar = new Toolbar(this);
        setSupportActionBar(toolbar);


//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.content_accordeon_zimmer);


        scrollview = new ScrollView(this);
        LinearLayout linearlayout = new LinearLayout(this);
        linearlayout.setOrientation(LinearLayout.VERTICAL);
        scrollview.addView(linearlayout);

        for (int i = 0; i < 4; i++) {
            LinearLayout linear1 = new LinearLayout(this);
            linear1.setOrientation(LinearLayout.HORIZONTAL);
            linearlayout.addView(linear1);
            b = new Button(this);
            b.setText("Zimmer" + i);
            b.setId(i);

            b.setPadding(8, 3, 8, 3);
            b.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));

            linear1.addView(b);

            magicLL = new LinearLayout(this);
            magicLL.setOrientation(LinearLayout.VERTICAL);
            magicLL.setId(100 + i);


            magicLV = new ListView(this);
            magicLV.setId(200 + i);
//
//
//            magicLV.setAdapter(arrayAdapter);
//            arrayAdapter.add("listview" + i);

            magicLL.addView(magicLV);


            linear1.addView(magicLL);
            
            
//            CheckBox checkbox = new CheckBox(this);
//            checkbox.setId(i);
//            checkbox.setText("Wow..");
//            linear1.addView(checkbox);
//
//            checkbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//
//                @Override
//                public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
//                    // TODO Auto-generated method stub
//                    Toast.makeText(getApplicationContext(), "Checked.."+ arg0.getId(), Toast.LENGTH_SHORT).show();
//                }
//            });
//
            b.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
//                    Toast.makeText(getApplicationContext(), "Yipee.."+ v.getId(), Toast.LENGTH_SHORT).show();
                    magicLL = (LinearLayout) findViewById(v.getId());
                    if (magicLL.getVisibility() == View.VISIBLE) {
                        magicLL.setVisibility(View.GONE);
                    } else {
                        magicLL.setVisibility(View.VISIBLE);
                    }
                }
            });
        }

        
        this.setContentView(scrollview);
    }
}

//package fhdw.hotel;
//
//import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.view.View;
//import android.widget.Button;
//import android.widget.LinearLayout;
//
//public class AccordeonZimmer extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_accordeon_zimmer);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//
//        Button findMagicBtn = (Button) findViewById(R.id.magic_btn);
//        findMagicBtn.setOnClickListener(new View.OnClickListener() {
//
//
//            @Override
//            public void onClick(View v) {
//                LinearLayout findMagicLl = (LinearLayout) findViewById(R.id.magic_layout);
//                if (findMagicLl.getVisibility() == View.VISIBLE) {
//                    findMagicLl.setVisibility(View.GONE);
//                } else {
//                    findMagicLl.setVisibility(View.VISIBLE);
//                }
//            }
//        });
//    }
//
//
//
//}
