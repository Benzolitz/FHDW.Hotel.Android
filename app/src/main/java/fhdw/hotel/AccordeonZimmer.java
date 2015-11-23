package fhdw.hotel;


import android.app.Activity;

import android.graphics.drawable.Drawable;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class AccordeonZimmer extends Activity {
    LinearLayout rooms;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_accordeon_zimmer);
        rooms = (LinearLayout) findViewById(R.id.linearLayoutBuses);
        fillCountryTable();
    }

    void fillCountryTable() {

        Button b1;
        TextView t1;
//Converting to dip unit
        int dip = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                (float) 1, getResources().getDisplayMetrics());

        for (int current = 0; current < BusList.busNumber.length; current++) {

            b1 = new Button(this);
            t1 = new TextView(this);

            b1.setId(current);
            t1.setId(current);

            b1.setText(BusList.busNumber[current]);
            t1.setText(BusList.busName[current]);

            b1.setTypeface(null, 1);
            t1.setTypeface(null, 1);

            t1.setTextSize(15);
            b1.setTextSize(15);

            b1.setGravity(Gravity.LEFT);
            t1.setGravity(Gravity.LEFT);

            t1.setPadding(20, 10, 10, 10);

            /**
             * By default, color of button is black
             */
            b1.setTextColor(getResources().getColor(R.color.black));

//            b1.setCompoundDrawablesWithIntrinsicBounds(
//                    0,     //left
//                    0,      //top
//                    R.drawable.sq_br_down,  //right
//                    0);     //bottom
            t1.setVisibility(t1.GONE);

            rooms.addView(b1, new TableLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            rooms.addView(t1, new TableLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

            b1.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    Button button = (Button) v;
                    LinearLayout parent = (LinearLayout) v.getParent();

                    /**
                     * If text color is black
                     *         open the accordion of selected tab
                     *         close the accordion of remaining tab
                     * else
                     *         if text color is white
                     *         close the accordion of selected tab
                     */
                    if (button.getCurrentTextColor() == getResources().getColor(R.color.black)) {
                        for (int j = 0; j < parent.getChildCount(); j++) {
                            if (v.getId() == parent.getChildAt(j).getId()) {
                                /*Change the color of text to check later wether a tab is opened or closed*/
                                button.setTextColor(getResources().getColor(R.color.white));

                                parent.getChildAt(j).setVisibility(View.VISIBLE);

                                /*Icon for arrow right / down*/
//                                button.setCompoundDrawablesWithIntrinsicBounds(
//                                        0,     //left
//                                        0,      //top
//                                        R.drawable.sq_br_up,  //right
//                                        0);     //bottom
                            }
                        }
                    } else {
                        for (int j = 0; j < parent.getChildCount(); j++) {
                            if (v.getId() == parent.getChildAt(j).getId() &&
                                    parent.getChildAt(j).getVisibility() == View.VISIBLE) {

                                /*Color changing like above*/
                                button.setTextColor(getResources().getColor(R.color.black));
                                parent.getChildAt(j + 1).setVisibility(View.GONE);

                                    /*Chenge the icon like above*/
//                                button.setCompoundDrawablesWithIntrinsicBounds(
//                                        0,     //left
//                                        0,      //top
//                                        R.drawable.sq_br_down,  //right
//                                        0);     //bottom
                            }
                        }
                    }
                }
            });
        }
    }
}

//
//import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.LinearLayout;
//import android.app.Activity;
//import android.graphics.Typeface;
//import android.os.Bundle;
//import android.view.View;
//import android.view.ViewGroup.LayoutParams;
//
//import android.widget.CheckBox;
//import android.widget.CompoundButton;
//import android.widget.CompoundButton.OnCheckedChangeListener;
//import android.widget.ListView;
//import android.widget.ScrollView;
//import android.widget.Toast;
//
//
//
//public class AccordeonZimmer extends AppCompatActivity {
//    Button b;
//    ScrollView scrollview;
//    Toolbar toolbar;
//    LinearLayout magicLL;
//    ListView magicLV;
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_accordeon_zimmer, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    /**
//     * Called when the activity is first created.
//     */
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_accordeon_zimmer);
////      toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar = new Toolbar(this);
//        setSupportActionBar(toolbar);
//
//
////        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.content_accordeon_zimmer);
//
//
//        scrollview = new ScrollView(this);
//        LinearLayout linearlayout = new LinearLayout(this);
//        linearlayout.setOrientation(LinearLayout.VERTICAL);
//        scrollview.addView(linearlayout);
//
//        for (int i = 0; i < 4; i++) {
//            LinearLayout linear1 = new LinearLayout(this);
//            linear1.setOrientation(LinearLayout.HORIZONTAL);
//            linearlayout.addView(linear1);
//            b = new Button(this);
//            b.setText("Zimmer" + i);
//            b.setId(i);
//
//            b.setPadding(8, 3, 8, 3);
//            b.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
//
//            linear1.addView(b);
//
////            magicLL = new LinearLayout(this);
////            magicLL.setOrientation(LinearLayout.VERTICAL);
////            magicLL.setId(100 + i);
//
//
//            magicLV = new ListView(this);
//            magicLV.setTag("ListView"+i);
////
////
////            magicLV.setAdapter(arrayAdapter);
////            arrayAdapter.add("listview" + i);
//
////            magicLL.addView(magicLV);
////            magicLL.addView(magicLV);
//
//
//            linear1.addView(magicLV);
//
//
////            CheckBox checkbox = new CheckBox(this);
////            checkbox.setId(i);
////            checkbox.setText("Wow..");
////            linear1.addView(checkbox);
////
////            checkbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
////
////                @Override
////                public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
////                    // TODO Auto-generated method stub
////                    Toast.makeText(getApplicationContext(), "Checked.."+ arg0.getId(), Toast.LENGTH_SHORT).show();
////                }
////            });
////
//            b.setOnClickListener(new View.OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//                    // TODO Auto-generated method stub
////                    Toast.makeText(getApplicationContext(), "Yipee.."+ v.getId(), Toast.LENGTH_SHORT).show();
////                    magicLV = (ListView) findViewById();
//                    if (magicLV.getVisibility() == View.VISIBLE) {
//                        magicLV.setVisibility(View.GONE);
//                    } else {
//                        magicLV.setVisibility(View.VISIBLE);
//                    }
//                }
//            });
//        }
//
//
//        this.setContentView(scrollview);
//    }
//}
//
////package fhdw.hotel;
////
////import android.os.Bundle;
////import android.support.design.widget.FloatingActionButton;
////import android.support.design.widget.Snackbar;
////import android.support.v7.app.AppCompatActivity;
////import android.support.v7.widget.Toolbar;
////import android.view.View;
////import android.widget.Button;
////import android.widget.LinearLayout;
////
////public class AccordeonZimmer extends AppCompatActivity {
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_accordeon_zimmer);
////        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
////        setSupportActionBar(toolbar);
////
////
////        Button findMagicBtn = (Button) findViewById(R.id.magic_btn);
////        findMagicBtn.setOnClickListener(new View.OnClickListener() {
////
////
////            @Override
////            public void onClick(View v) {
////                LinearLayout findMagicLl = (LinearLayout) findViewById(R.id.magic_layout);
////                if (findMagicLl.getVisibility() == View.VISIBLE) {
////                    findMagicLl.setVisibility(View.GONE);
////                } else {
////                    findMagicLl.setVisibility(View.VISIBLE);
////                }
////            }
////        });
////    }
////
////
////
////}
