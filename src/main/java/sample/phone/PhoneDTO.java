/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.phone;

/**
 *
 * @author Lenovo
 */
public class PhoneDTO {
    private String phoneID;
    private String phoneName;
    private String imageURL;
    private String seriesID;
    private int price;
    private int quantity;
    private String phoneCPU;
    private String screenSize;

    public PhoneDTO() {
        this.phoneID = "";
        this.phoneName = "";
        this.seriesID = "";
        this.price = 0;
        this.quantity = 0;
        this.phoneCPU = "";
        this.screenSize = "";
        this.imageURL = "";
    }

    public PhoneDTO(String phoneID, String phoneName, String imageURL, String seriesID, int price, int quantity, String phoneCPU, String screenSize) {
        this.phoneID = phoneID;
        this.phoneName = phoneName;
        this.imageURL = imageURL;
        this.seriesID = seriesID;
        this.price = price;
        this.quantity = quantity;
        this.phoneCPU = phoneCPU;
        this.screenSize = screenSize;
    }

    public String getPhoneID() {
        return phoneID;
    }

    public void setPhoneID(String phoneID) {
        this.phoneID = phoneID;
    }

    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    public String getSeriesID() {
        return seriesID;
    }

    public void setSeriesID(String seriesID) {
        this.seriesID = seriesID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPhoneCPU() {
        return phoneCPU;
    }

    public void setPhoneCPU(String phoneCPU) {
        this.phoneCPU = phoneCPU;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    
}
