package tala.mubarki.talafinalproject17.Data;

import android.location.Location;

import java.util.Date;

public class MySale {
    private String key; //id
    private String type;
    private double discountpercent ;
    private Date Lastdate;

    public MySale() {
    }
    public String getKey(String key) {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
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
    public  String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setDiscountString(String discountString){
        this.discountpercent= Double.parseDouble(discountString);
    }

    public Date getLastdate() {
        return Lastdate;
    }

    public void setLastdate(Date lastdate) {
        Lastdate = lastdate;
    }
}
