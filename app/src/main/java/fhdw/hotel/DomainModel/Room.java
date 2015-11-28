package fhdw.hotel.DomainModel;

import java.io.Serializable;

/**
 * Created by Artur Briem on 25.11.2015.
 */
public class Room implements Serializable{
    private int id;
    private Enums.RoomType type;
    private Enums.RoomCategory category;

    public Room() {}

    public int getId()
    {
        return id;
    }
    public void setId(int p_id) { this.id = p_id; }

    public Enums.RoomType getType()
    {
        return type;
    }
    public void setType(Enums.RoomType p_type){ type = p_type; }

    public Enums.RoomCategory getCategory()
    {
        return category;
    }
    public void setCategory(Enums.RoomCategory p_category){ category = p_category; }
}

