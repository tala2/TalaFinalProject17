package tala.mubarki.talafinalproject17.Data;

import java.util.Date;

/**
 * class for each shop for the user(owner)
 */
public class Shop {
    /**
     * key: id
     * name: name of the shop
     * address: address of the shop
     * category: kind of the goods
     * discount: for each shop
     * lastdate: lastdate of the sale
     * owner: the owner of the shop
     */
    private String key; //id
    private String name;
    private String address;
    private String category;
    private double discountpercent ;//sale
    private Date Lastdate;//sale
    private String owner;
    private final boolean isCompleted= false;

    public boolean isCompleted() {
        return isCompleted;
    }

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

    /**
     * the function changes the param from string to dabble
     * @param discountString: String disscount
     */
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
}
