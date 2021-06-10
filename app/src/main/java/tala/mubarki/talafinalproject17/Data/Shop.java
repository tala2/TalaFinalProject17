package tala.mubarki.talafinalproject17.Data;

import java.util.Date;

public class Shop {
    private String key; //id
    private String name;
    private String address;
    private String category;
    private double rate;
    private String phone;
    private double discountpercent ;//sale
    private Date Lastdate;//sale
    private String owner;

    public String getKey() {
        return key;
    }

    public double getDiscountpercent() {
        return discountpercent;
    }

    public void setDiscountpercent(double discountpercent) {
        this.discountpercent = discountpercent;
    }
    public String getDiscountString(){
        return discountpercent +"";
    }

    public void setDiscountString(String discountString){
        this.discountpercent= Double.parseDouble(discountString);
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getLastdate() {
        return Lastdate;
    }

    public void setLastdate(Date lastdate) {
        Lastdate = lastdate;
    }
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getKey(String key) {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Shop() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
