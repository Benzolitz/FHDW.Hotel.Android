package fhdw.hotel.DomainModel;

import java.util.ArrayList;
import java.util.Date;

public class Booking {
    private int Id;
    private Date Arrival;
    private Date Departure;
    private ArrayList<Room> Rooms;

    public int getId() {return Id;}
    public void setId(int p_id) {Id = p_id;}

    public Date getArrival() {return Arrival;}
    public void setArrival(Date p_arrival) {
        Arrival = p_arrival;
    }

    public Date getDeparture() {
        return Departure;
    }
    public void setDeparture(Date p_departure) {
        Departure = p_departure;
    }

    public ArrayList<Room> getRooms() {
        return Rooms;
    }
    public void setRooms(ArrayList<Room> p_rooms) {
        Rooms = p_rooms;
    }
}
