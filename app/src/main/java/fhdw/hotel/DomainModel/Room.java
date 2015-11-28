package fhdw.hotel.DomainModel;

import java.io.Serializable;

public class Room implements Serializable{
    private int Id;
    private String RoomNumber;
    private Enums.RoomCategory Category;
    private Enums.RoomType Type;
    private int PersonCount;
    private float Price;
    private Hotel Hotel;

    public int getId() {
        return Id;
    }
    public void setId(int p_id) {
        Id = p_id;
    }

    public String getRoomNumber() {
        return RoomNumber;
    }
    public void setRoomNumber(String p_roomNumber) {
        RoomNumber = p_roomNumber;
    }

    public Enums.RoomCategory getCategory() {
        return Category;
    }
    public void setCategory(Enums.RoomCategory p_category) {
        Category = p_category;
    }

    public Enums.RoomType getType() {
        return Type;
    }
    public void setType(Enums.RoomType p_type) {
        Type = p_type;
    }

    public int getPersonCount() {
        return PersonCount;
    }
    public void setPersonCount(int p_personCount) {
        PersonCount = p_personCount;
    }

    public float getPrice() {
        return Price;
    }
    public void setPrice(float p_price) {
        Price = p_price;
    }

    public fhdw.hotel.DomainModel.Hotel getHotel() {
        return Hotel;
    }
    public void setHotel(Hotel p_hotel) {
        Hotel = p_hotel;
    }
}

