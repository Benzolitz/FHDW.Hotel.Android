package fhdw.hotel.DomainModel;

/**
 * Addressmodel
 * @author Lucas Engel
 */
public class Address {
    /**
     * ID of the Address
     */
    public int Id;

    /**
     * Street of the Address
     */
    public String Street;

    /**
     * PostalCode of the Address
     */
    public String PostalCode;

    /**
     * City of the Address
     */
    public String City;

    // region Getter & Setter
    public int getId() {
        return Id;
    }
    public void setId(int id) {
        Id = id;
    }

    public String getStreet() {
        return Street;
    }
    public void setStreet(String street) {
        Street = street;
    }

    public String getPostalCode() {
        return PostalCode;
    }
    public void setPostalCode(String postalCode) {
        PostalCode = postalCode;
    }

    public String getCity() {
        return City;
    }
    public void setCity(String city) {
        City = city;
    }
    // endregion
}
