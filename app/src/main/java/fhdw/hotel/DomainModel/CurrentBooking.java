package fhdw.hotel.DomainModel;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Artur Briem on 05.12.2015.
 */
public class CurrentBooking {
    public static int singleRoomCnt;
    public static int doubleRoomCnt;
    public static int familyRoomCnt;
    public static String personCnt;
    public static Date Arrival;
    public static Date Departure;
    public static Guest Guest;
    public static Hotel Hotel;
    public static ArrayList<Room> Rooms;

    // region Getter & Setter
    public static int getSingleRoomCnt() {
        return singleRoomCnt;
    }

    public static void setgetSingleRoomCnt(int rooms) {
        singleRoomCnt = rooms;
    }

    public static int getDoubleRoomCnt() {
        return doubleRoomCnt;
    }

    public static void setDoubleRoomCnt(int rooms) {
        doubleRoomCnt = rooms;
    }

    public static int getFamilyRoomCnt() {
        return familyRoomCnt;
    }

    public static void setFamilyRoomCnt(int rooms) {
        familyRoomCnt = rooms;
    }

    public static String getPersonCnt() {
        return personCnt;
    }

    public static void setPersonCnt(String persons) {
        personCnt = persons;
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

    public static fhdw.hotel.DomainModel.Guest getGuest() {
        return Guest;
    }

    public static void setGuest(fhdw.hotel.DomainModel.Guest guest) {
        Guest = guest;
    }

    public static fhdw.hotel.DomainModel.Hotel getHotel() {
        return Hotel;
    }

    public static void setHotel(fhdw.hotel.DomainModel.Hotel hotel) {
        Hotel = hotel;
    }

    public static ArrayList<Room> getRooms() {
        return Rooms;
    }

    public static void setRooms(ArrayList<Room> rooms) {
        Rooms = rooms;
    }


    public static String toOutString() {

        String billing = "";
//        if(!Guest.BillingAddress.City.isEmpty() || !Guest.BillingAddress.PostalCode.isEmpty() || !Guest.BillingAddress.Street.isEmpty() ) {
//            billing = "Rechnungsadresse: "+Guest.BillingAddress.PostalCode + " " + Guest.BillingAddress.City + "\n" +
//                      "                : "+Guest.BillingAddress.Street;
//        }
//        else {
//            billing = " - ";
//        }

        return "Name   : " + Guest.Firstname + " " + Guest.Lastname + "\n" +
               "Email  : " + Guest.Emailaddress + "\n" +
               "Adresse: " + Guest.ContactAddress.PostalCode + " " + Guest.ContactAddress.City + "\n" +
               "         " + Guest.ContactAddress.Street + "\n" +
               "Hotel  : " + Hotel.Name + " " + Hotel.Address.City + "\n" +
               "Anreise: " + Arrival + "\n" +
               "Abreise: " + Departure + "\n" +
               "Anz. Personen: " + personCnt + "\n" +

               "Gebuchte Zimmer: " + "\n" +
               "  Einzel:  " + singleRoomCnt + "\n" +
               "  Doppel:  " + doubleRoomCnt + "\n" +
               "  Familien:" + familyRoomCnt + "\n\n" +
               "Separate Rechnungsadresse: \n" +
                billing
                ;
    }
    // endregion
}
