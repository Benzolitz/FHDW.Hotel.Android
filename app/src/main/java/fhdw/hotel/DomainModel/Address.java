package fhdw.hotel.DomainModel;

public class Address {
    private int Id;
    private String Street;
    private String PostalCode;
    private String City;

    public int getId() {
        return Id;
    }
    public void setId(int p_id) { Id = p_id; }

    public String getStreet() {
        return Street;
    }
    public void setStreet(String p_street) {
        Street = p_street;
    }

    public String getPostalCode() {
        return PostalCode;
    }
    public void setPostalCode(String p_postalCode) {
        PostalCode = p_postalCode;
    }

    public String getCity() {
        return City;
    }
    public void setCity(String p_city) {
        City = p_city;
    }
}
