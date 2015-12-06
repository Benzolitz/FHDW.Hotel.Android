package fhdw.hotel.DomainModel;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Artur Briem on 05.12.2015.
 */
public class CurrentBooking {

    public static int Id;
    public static Date Arrival;
    public static Date Departure;
    public static ArrayList<Room> Rooms;

    // region Getter & Setter
    public static int getId() {
        return Id;
    }
    public static void setId(int id) {
        Id = id;
    }

    public static Date getArrival() {
        return Arrival;
    }
    public static void setArrival(Date arrival) {
        Arrival = arrival;
    }

    public static Date getDeparture() {
        return Departure;
    }
    public static void setDeparture(Date departure) {
        Departure = departure;
    }

    public static ArrayList<Room> getRooms() {
        return Rooms;
    }
    public static void setRooms(ArrayList<Room> rooms) {
        Rooms = rooms;
    }
}
