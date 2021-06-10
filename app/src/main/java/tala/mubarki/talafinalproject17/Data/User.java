package tala.mubarki.talafinalproject17.Data;

/**
 * class for the owner or the customer
 */
public class User {
    /**
     * name: the name of the user
     * owner: the owner of the profile
     * key: id
     * phone: phone number
     * ladtName: family name
     * Email address: the email of the user
     */
    private String name;
    private String owner;
    private String key;
    private String phone;
    private String EmailAddress;

    /**
     * constructor
     */
    public User() {
    }

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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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
        return "User{" +
                "name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", key='" + key + '\'' +
                ", phone='" + phone + '\'' +
                ", EmailAdress='" + EmailAddress + '\'' +
                '}';
    }
}
