package tala.mubarki.talafinalproject17.Data;

import java.util.Date;
import java.util.List;

public class Owner {
    private String name;
    private String key;
    private String phone;
    private String EmailAddress;
    private String owner;
    private String type;
    private String LastName;
   /// private List<Shop> shops;

    public Owner() {
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * constructor
     */



    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmailAdress() {
        return EmailAddress;
    }
    public void setEmailAdress(String emailAdress) {
        EmailAddress = emailAdress;
    }
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    @Override
    public String toString() {
        return "Owner{" +
                "name='" + name + '\'' +
                ", key='" + key + '\'' +
                ", phone='" + phone + '\'' +
                ", EmailAddress='" + EmailAddress + '\'' +
                ", owner='" + owner + '\'' +
                ", type='" + type + '\'' +
                ", LastName='" + LastName + '\'' +
                '}';
    }
}
