package fhdw.hotel.Activity;


import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;

import java.util.ArrayList;

import fhdw.hotel.DomainModel.Room;
import fhdw.hotel.R;

public class RoomSelection extends Activity {

    LinearLayout rooms;
    ArrayList<Room> allRooms = new ArrayList<>();


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_room_selection);
        rooms = (LinearLayout) findViewById(R.id.linearLayoutRooms);
        Intent intent = getIntent();
        allRooms = (ArrayList<Room>) intent.getSerializableExtra("allRooms");
        fillRoomList();
    }

    void fillRoomList() {

        Button b1;
        LinearLayout ll;
        RadioGroup rg;

        RadioButton standard;
        RadioButton comfort;
        RadioButton luxus;

        for (int current = 0; current < allRooms.size(); current++) {

            ll = new LinearLayout(this);
            rg = new RadioGroup(this);
            standard = new RadioButton(this);
            comfort = new RadioButton(this);
            luxus = new RadioButton(this);
            b1 = new Button(this);

            b1.setId(current);
            ll.setId(current);

            b1.setText(allRooms.get(current).toString());

            rg.setOrientation(RadioGroup.HORIZONTAL);

            standard.setText(R.string.standardZimmer);
            rg.addView(standard);

            comfort.setText(R.string.comfortZimmer);
            rg.addView(comfort);

            luxus.setText(R.string.luxusZimmer);
            rg.addView(luxus);

            ll.addView(rg);

            b1.setTypeface(null, 1);

            b1.setTextSize(15);

            b1.setGravity(Gravity.START);
            ll.setGravity(Gravity.START);

            ll.setPadding(20, 10, 10, 10);

            /**
             * By default, color of button is black
             */
            b1.setTextColor(getResources().getColor(R.color.black));

//            b1.setCompoundDrawablesWithIntrinsicBounds(
//                    0,     //left
//                    0,      //top
//                    R.drawable.sq_br_down,  //right
//                    0);     //bottom
            ll.setVisibility(View.GONE);

            rooms.addView(b1, new TableLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            rooms.addView(ll, new TableLayout.LayoutParams(
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
