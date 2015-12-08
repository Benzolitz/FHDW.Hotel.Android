package fhdw.hotel.DomainModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Booking implements Serializable{
    public int Id;
    public Date Arrival;
    public Date Departure;
    public ArrayList<Room> Rooms;

    // region Getter & Setter
    public int getId() {
        return Id;
    }
    public void setId(int id) {
        Id = id;
    }

    public Date getArrival() {
        return Arrival;
    }
    public void setArrival(Date arrival) {
        Arrival = arrival;
    }

    public Date getDeparture() {
        return Departure;
    }
    public void setDeparture(Date departure) {
        Departure = departure;
    }

    public ArrayList<Room> getRooms() {
        return Rooms;
    }
    public void setRooms(ArrayList<Room> rooms) {
        Rooms = rooms;
    }
    // endregion
}
