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
import java.util.Collection;

import fhdw.hotel.DomainModel.Enums;
import fhdw.hotel.DomainModel.Room;
import fhdw.hotel.R;

public class RoomSelection extends Activity {

    LinearLayout rooms;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_room_selection);
        rooms = (LinearLayout) findViewById(R.id.linearLayoutRooms);

        Intent intent = getIntent();

        int singleRoomCount = (int) intent.getSerializableExtra("singleRoomCount");
        int doubleRoomCount = (int) intent.getSerializableExtra("doubleRoomCount");
        int familyRoomCount = (int) intent.getSerializableExtra("familyRoomCount");

        fillRoomList(singleRoomCount, doubleRoomCount, familyRoomCount);
    }

    private void fillRoomList(int p_singleRoomCount, int p_doubleRoomCount, int p_familyRoomCount) {
        ArrayList<Room> allRooms = getRoomList(p_singleRoomCount, p_doubleRoomCount, p_familyRoomCount);

        for (int i = 0; i < allRooms.size(); i++) {
            rooms.addView(getAccordionButton(i, allRooms.get(i)), new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            rooms.addView(getRoomInformation(i), new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        }
    }

    private View getRoomInformation(int p_id) {
        LinearLayout llRoomInformation;
        llRoomInformation = new LinearLayout(this);
        llRoomInformation.setId(p_id);
        llRoomInformation.addView(getRoomTypeSelection());
        llRoomInformation.setGravity(Gravity.START);
        llRoomInformation.setPadding(20, 10, 10, 10);
        llRoomInformation.setVisibility(View.GONE);
        return llRoomInformation;
    }

    private View getAccordionButton(int p_id, Room p_currentRoom) {
        Button btnAccordion;
        btnAccordion = new Button(this);
        btnAccordion.setId(p_id);
        btnAccordion.setText(Enums.RoomTypeToString(p_currentRoom.getType()));
        btnAccordion.setTypeface(null, 1);
        btnAccordion.setTextSize(15);
        btnAccordion.setGravity(Gravity.START);
        btnAccordion.setTextColor(getResources().getColor(R.color.black));
        btnAccordion.setOnClickListener(AccordionOnClick());
        return btnAccordion;
    }

    private View.OnClickListener AccordionOnClick() {
        return new View.OnClickListener() {

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
                            // button.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.sq_br_up,0);
                        }
                    }
                } else {
                    for (int j = 0; j < parent.getChildCount(); j++) {
                        if (v.getId() == parent.getChildAt(j).getId() && parent.getChildAt(j).getVisibility() == View.VISIBLE) {

                            /*Color changing like above*/
                            button.setTextColor(getResources().getColor(R.color.black));
                            parent.getChildAt(j + 1).setVisibility(View.GONE);

                            // button.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.sq_br_down,0);
                        }
                    }
                }
            }};

    }

    private ArrayList<Room> getRoomList(int p_singleRoomCount, int p_doubleRoomCount, int p_familyRoomCount) {
        ArrayList<Room> allRooms = new ArrayList<>();
        allRooms.addAll(getRooms(p_singleRoomCount, Enums.RoomType.Single));
        allRooms.addAll(getRooms(p_doubleRoomCount, Enums.RoomType.Double));
        allRooms.addAll(getRooms(p_familyRoomCount, Enums.RoomType.Family));
        return allRooms;
    }

    private Collection<? extends Room> getRooms(int p_roomCount, Enums.RoomType p_roomType) {
        ArrayList<Room> roomList = new ArrayList<>();

        for (int i = 0; i < p_roomCount; i++) {
            Room room = new Room();
            room.setType(p_roomType);

            roomList.add(room);
        }

        return roomList;
    }

    private View getRoomTypeSelection() {
        RadioGroup radGroupRoomType = new RadioGroup(this);
        radGroupRoomType.setOrientation(RadioGroup.HORIZONTAL);

        RadioButton radStandard = new RadioButton(this);
        radStandard.setText(Enums.RoomCategoryToString(Enums.RoomCategory.Standard));
        radGroupRoomType.addView(radStandard);

        RadioButton radLuxus = new RadioButton(this);
        radLuxus.setText(Enums.RoomCategoryToString(Enums.RoomCategory.Luxus));
        radGroupRoomType.addView(radLuxus);

        RadioButton radSuperior = new RadioButton(this);
        radSuperior.setText(Enums.RoomCategoryToString(Enums.RoomCategory.Superior));
        radGroupRoomType.addView(radSuperior);

        return radGroupRoomType;
    }
}
