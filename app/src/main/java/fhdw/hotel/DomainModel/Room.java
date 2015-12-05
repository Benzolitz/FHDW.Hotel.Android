package fhdw.hotel.DomainModel;

import java.io.Serializable;

public class Room implements Serializable{
    public int Id;
    public String RoomNumber;
    public Enums.RoomCategory Category;
    public Enums.RoomType Type;
    public int PersonCount;
    public float Price;
    public Hotel Hotel;

    // region Getter & Setter
    public int getId() {
        return Id;
    }
    public void setId(int id) {
        Id = id;
    }

    public String getRoomNumber() {
        return RoomNumber;
    }
    public void setRoomNumber(String roomNumber) {
        RoomNumber = roomNumber;
    }

    public Enums.RoomCategory getCategory() {
        return Category;
    }
    public void setCategory(Enums.RoomCategory category) {
        Category = category;
    }

    public Enums.RoomType getType() {
        return Type;
    }
    public void setType(Enums.RoomType type) {
        Type = type;
    }

    public int getPersonCount() {
        return PersonCount;
    }
    public void setPersonCount(int personCount) {
        PersonCount = personCount;
    }

    public float getPrice() {
        return Price;
    }
    public void setPrice(float price) {
        Price = price;
    }

    public fhdw.hotel.DomainModel.Hotel getHotel() {
        return Hotel;
    }
    public void setHotel(fhdw.hotel.DomainModel.Hotel hotel) {
        Hotel = hotel;
    }
    // endregion
}

