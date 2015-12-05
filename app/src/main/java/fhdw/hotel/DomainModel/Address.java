package fhdw.hotel.DomainModel;

public class Address {
    public int Id;
    public String Street;
    public String PostalCode;
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
