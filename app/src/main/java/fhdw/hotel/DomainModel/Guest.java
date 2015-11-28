package fhdw.hotel.DomainModel;

import java.util.Date;

public class Guest {
    private int Id;
    private String Firstname;
    private String Lastname;
    private String Emailaddress;
    private Date Birthday;
    private String Birthplace;
    private Address ContactAddress;
    private Address BillingAddress;
    private String Telephone;

    public int getId() {
        return Id;
    }
    public void setId(int id) {
        Id = id;
    }

    public String getFirstname() {
        return Firstname;
    }
    public void setFirstname(String p_firstName) {
        Firstname = p_firstName;
    }

    public String getLastname() {
        return Lastname;
    }
    public void setLastname(String p_lastname) {
        Lastname = p_lastname;
    }

    public String getEmailaddress() {
        return Emailaddress;
    }
    public void setEmailaddress(String p_emailaddress) {
        Emailaddress = p_emailaddress;
    }

    public Date getBirthday() {
        return Birthday;
    }
    public void setBirthday(Date p_birthday) {
        Birthday = p_birthday;
    }

    public String getBirthplace() {
        return Birthplace;
    }
    public void setBirthplace(String p_birthplace) {
        Birthplace = p_birthplace;
    }

    public Address getContactAddress() {
        return ContactAddress;
    }
    public void setContactAddress(Address p_contactAddress) {
        ContactAddress = p_contactAddress;
    }

    public Address getBillingAddress() {
        return BillingAddress;
    }
    public void setBillingAddress(Address p_billingAddress) {
        BillingAddress = p_billingAddress;
    }

    public String getTelephone() {
        return Telephone;
    }
    public void setTelephone(String p_telephone) {
        Telephone = p_telephone;
    }
}
