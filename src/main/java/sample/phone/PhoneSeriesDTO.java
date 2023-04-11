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
public class PhoneSeriesDTO {
    private String seriesID;
    private String seriesName;
    private String imageURL;
    private int minPrice;

    public PhoneSeriesDTO() {
    }

    public PhoneSeriesDTO(String seriesID, String seriesName, String imageURL, int minPrice) {
        this.seriesID = seriesID;
        this.seriesName = seriesName;
        this.imageURL = imageURL;
        this.minPrice = minPrice;
    }

    public String getSeriesID() {
        return seriesID;
    }

    public void setSeriesID(String seriesID) {
        this.seriesID = seriesID;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }
}
