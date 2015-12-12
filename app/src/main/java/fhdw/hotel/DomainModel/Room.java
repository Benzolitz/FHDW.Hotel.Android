package fhdw.hotel.DomainModel;

import java.io.Serializable;

/**
 * Roommodel
 * @author Lucas Engel
 */
public class Room implements Serializable{
    /**
     * ID of the Room
     */
    public int Id;

    /**
     * RoomNumber of the Room
     */
    public String RoomNumber;

    /**
     * Category of the Room
     */
    public Enums.RoomCategory Category;

    /**
     * Type of the Room
     */
    public Enums.RoomType Type;

    /**
     * PersonCount of the Room
     */
    public int PersonCount;

    /**
     * Price of the Room
     */
    public float Price;

    /**
     * Hotel of the Room
     */
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

