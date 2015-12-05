package fhdw.hotel.DomainModel;

import java.util.Date;

public class Guest {
    public int Id;
    public String Firstname;
    public String Lastname;
    public String Emailaddress;
    public String Password;
    public Date Birthday;
    public String Birthplace;
    public Address ContactAddress;
    public Address BillingAddress;
    public String Telephone;

    // region Getter & Setter
    public int getId() {
        return Id;
    }
    public void setId(int id) {
        Id = id;
    }

    public String getFirstname() {
        return Firstname;
    }
    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getLastname() {
        return Lastname;
    }
    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public String getEmailaddress() {
        return Emailaddress;
    }
    public void setEmailaddress(String emailaddress) {
        Emailaddress = emailaddress;
    }

    public String getPassword() {
        return Password;
    }
    public void setPassword(String password) {
        Password = password;
    }

    public Date getBirthday() {
        return Birthday;
    }
    public void setBirthday(Date birthday) {
        Birthday = birthday;
    }

    public String getBirthplace() {
        return Birthplace;
    }
    public void setBirthplace(String birthplace) {
        Birthplace = birthplace;
    }

    public Address getContactAddress() {
        return ContactAddress;
    }
    public void setContactAddress(Address contactAddress) {
        ContactAddress = contactAddress;
    }

    public Address getBillingAddress() {
        return BillingAddress;
    }
    public void setBillingAddress(Address billingAddress) {
        BillingAddress = billingAddress;
    }

    public String getTelephone() {
        return Telephone;
    }
    public void setTelephone(String telephone) {
        Telephone = telephone;
    }
    // endregion
}
