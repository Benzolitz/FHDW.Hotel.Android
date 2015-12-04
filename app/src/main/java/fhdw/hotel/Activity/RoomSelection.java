package fhdw.hotel.Activity;


import android.widget.RadioGroup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import android.widget.TableLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;

import fhdw.hotel.BLL.Async.IListener.IAsyncHotelListener;
import fhdw.hotel.BLL.Async.IListener.IAsyncRoomListener;
import fhdw.hotel.DomainModel.Enums;
import fhdw.hotel.DomainModel.Hotel;
import fhdw.hotel.DomainModel.Room;
import fhdw.hotel.R;

public class RoomSelection extends Activity implements IAsyncRoomListener {

    LinearLayout rooms;
    Button gotoBooking;
    RadioButton selectedRbn;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_room_selection);
        rooms = (LinearLayout) findViewById(R.id.linearLayoutRooms);
        gotoBooking = (Button) findViewById(R.id.btn_goto_booking);

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
            rooms.addView(getImageView(i));
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

    private View getImageView(int p_id) {
        ImageView hotelImgView;
        hotelImgView = new ImageView(RoomSelection.this);
        hotelImgView.setVisibility(View.VISIBLE);
        hotelImgView.setMaxHeight(60);
        hotelImgView.setMaxWidth(60);
        hotelImgView.setId(p_id);
        return hotelImgView;
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
            }
        };

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

        radGroupRoomType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup rg, int checkedId) {
                for (int i = 0; i < rg.getChildCount(); i++) {
                    RadioButton btn = (RadioButton) rg.getChildAt(i);
                    if (btn.getId() == checkedId) {
                        String text = btn.getText().toString();
                        ImageView img = (ImageView) getImageView(checkedId);

                        if (!text.isEmpty()) {
                            switch (text) {
                                case "Standard":
//                                    img.setBackgroundResource(R.mipmap.ein);
//                                    img.setImageDrawable(getResources().getDrawable(R.mipmap.ein));
                                    img.setImageResource(R.mipmap.ein);

                                    break;

                                case "Luxus":
//                                    img.setBackgroundResource(R.mipmap.dop);
//                                    img.setImageDrawable(getResources().getDrawable(R.mipmap.dop));
                                    img.setImageResource(R.mipmap.dop);
                                    break;

                                case "Überragend":
//                                    img.setBackgroundResource(R.mipmap.fam);
//                                    img.setImageDrawable(getResources().getDrawable(R.mipmap.fam));
                                    img.setImageResource(R.mipmap.fam);
                                    break;

                                default:
                                    Toast.makeText(RoomSelection.this, "iwas is schief gelaufen", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        } else {
                            Toast.makeText(RoomSelection.this, "ich bin im else zweig!?!?", Toast.LENGTH_LONG).show();
                        }


//                        Toast.makeText(RoomSelection.this, text, Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }
        });

        return radGroupRoomType;
    }

    @Override
    public void GetCollectionComplete(ArrayList<Room> p_result) {

    }

    @Override
    public void GetRoom(Room p_result) {

    }

    public void GoToRegisterDescisionOnClick(View view) {
        Intent intent = new Intent(RoomSelection.this, RegisterDescision.class);


        startActivity(intent);
    }
}
