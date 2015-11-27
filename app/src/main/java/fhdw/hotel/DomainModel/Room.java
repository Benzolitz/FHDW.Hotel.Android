package fhdw.hotel.DomainModel;

import java.io.Serializable;

/**
 * Created by Artur Briem on 25.11.2015.
 */
public class Room implements Serializable{
    private int ID;
    private String Type;
    private String Category;

    public Room(int zid, String art, String typ)
    {
        super();
        this.ID = zid;
        this.Type = art;
        this.Category = typ;
    }

    public Room(String art)
    {
        super();
        this.Type = art;
    }

    public int getID()
    {
        return ID;
    }

    public String getType()
    {
        return Type;
    }
    public String getCategory()
    {
        return Category;
    }

    @Override
    public String toString()
    {
        return Type;
    }

}
