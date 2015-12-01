package fhdw.hotel.DomainModel;

import java.util.ArrayList;

public class Hotel {
    private int Id;
    private String Name;

    private Address Address;
    private ArrayList<Room> Rooms;

    public int getId() {
        return Id;
    }
    public void setId(int p_id) {
        Id = p_id;
    }

    public String getName() {return Name;}
    public void setName(String name) {
        Name = name;
    }

    public Address getAddress() {
        return Address;
    }
    public void setAddress(Address p_address) {
        Address = p_address;
    }

    public ArrayList<Room> getRooms() {
        return Rooms;
    }
    public void setRooms(ArrayList<Room> p_rooms) {
        Rooms = p_rooms;
    }
}
