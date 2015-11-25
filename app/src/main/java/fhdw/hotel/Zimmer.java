package fhdw.hotel;

import java.io.Serializable;

/**
 * Created by Artur Briem on 25.11.2015.
 */
public class Zimmer implements Serializable{
    private int zid;
    private String art;
    private String typ;

    public Zimmer(int zid, String art, String typ)
    {
        super();
        this.zid = zid;
        this.art = art;
        this.typ = typ;

    }

    public Zimmer(String art)
    {
        super();

        this.art = art;
    }

    public int getZid()
    {
        return zid;
    }

    public String getArt()
    {
        return art;
    }
    public String getTyp()
    {
        return typ;
    }

    @Override
    public String toString()
    {
        return art;
    }

}
