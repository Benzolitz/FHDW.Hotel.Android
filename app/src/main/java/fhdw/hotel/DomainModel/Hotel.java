package fhdw.hotel.DomainModel;

import java.util.ArrayList;

/**
 * Hotelmodel
 * @author Lucas Engel
 */
public class Hotel {
    /**
     * ID of the Hotel
     */
    public int ID;

    /**
     * Name of the Hotel
     */
    public String Name;

    /**
     * Address of the Hotel
     */
    public Address Address;

    /**
     * Rooms of the Hotel
     */
    public ArrayList<Room> Rooms;

    // region Getter & Setter
    public int getId() {
        return ID;
    }
    public void setId(int id) {
        ID = id;
    }

    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }

    public fhdw.hotel.DomainModel.Address getAddress() {
        return Address;
    }
    public void setAddress(fhdw.hotel.DomainModel.Address address) {
        Address = address;
    }

    public ArrayList<Room> getRooms() {
        return Rooms;
    }
    public void setRooms(ArrayList<Room> rooms) {
        Rooms = rooms;
    }
    @Override
    public String toString() {
        return Address.City;
    }
    // endregion
}
