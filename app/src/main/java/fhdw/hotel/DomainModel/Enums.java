package fhdw.hotel.DomainModel;

/**
 * All Enums needed for the models
 * @author Lucas Engel
 */
public class Enums
{
    /**
     * Roomtype
     */
    public enum RoomType {
        Single, Double, Family
    }

    public static String RoomTypeToString(RoomType p_type){
        switch (p_type)
        {
            case Single:
                return "Einzelzimmer";
            case Double:
                return "Doppelzimmer";
            case Family:
                return "Familienzimmer";
            default:
                return "Unknown Type!";
        }
    }

    /**
     * Roomcategory
     */
    public enum RoomCategory {
        Standard, Luxus, Superior
    }

    public static String RoomCategoryToString(RoomCategory p_category){
        switch (p_category)
        {
            case Standard:
                return "Standard";
            case Luxus:
                return "Luxus";
            case Superior:
                return "Überragend";
            default:
                return "Unknown Category!";
        }
    }
}
