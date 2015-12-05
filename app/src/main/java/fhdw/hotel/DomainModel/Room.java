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
}

