package fhdw.hotel.DomainModel;

import java.util.Date;

public class CurrentBooking {
    public Date Arrival;
    public Date Departure;
    public Guest Guest;
    public Hotel Hotel;

    public int PersonCount;
    public int singleRoomCnt;
    public int doubleRoomCnt;
    public int familyRoomCnt;

    // region Getter & Setter
    public int getSingleRoomCnt() {
        return singleRoomCnt;
    }
    public void setSingleRoomCnt(int rooms) {
        singleRoomCnt = rooms;
    }

    public int getDoubleRoomCnt() {
        return doubleRoomCnt;
    }
    public void setDoubleRoomCnt(int rooms) {
        doubleRoomCnt = rooms;
    }

    public int getFamilyRoomCnt() {
        return familyRoomCnt;
    }
    public void setFamilyRoomCnt(int rooms) {
        familyRoomCnt = rooms;
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

    public int getPersonCount() {
        return PersonCount;
    }

    public void setPersonCount(int personCount) {
        PersonCount = personCount;
    }
    // endregion
}
