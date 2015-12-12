package fhdw.hotel.DomainModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Bookingmodel
 * @author Lucas Engel
 */
public class Booking implements Serializable{
    /**
     * ID of the Booking
     */
    public int Id;

    /**
     * Arrivaldate of the Booking
     */
    public Date Arrival;

    /**
     * Departuredate of the Booking
     */
    public Date Departure;

    /**
     * Guest of the Booking
     */
    public Guest Guest;

    /**
     * Hotel of the Booking
     */
    public Hotel Hotel;

    /**
     * Rooms of the Booking
     */
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

    public fhdw.hotel.DomainModel.Guest getGuest() {
        return Guest;
    }

    public void setGuest(fhdw.hotel.DomainModel.Guest guest) {
        Guest = guest;
    }

    public fhdw.hotel.DomainModel.Hotel getHotel() {
        return Hotel;
    }

    public void setHotel(fhdw.hotel.DomainModel.Hotel hotel) {
        Hotel = hotel;
    }

    public ArrayList<Room> getRooms() {
        return Rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        Rooms = rooms;
    }
    // endregion
}
