/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.orderUtils;

/**
 *
 * @author Lenovo
 */
public class UserReceiveItemInfo {
    private String receiverName;
    private String receiverPhone;
    private String receiveAddress;
    private String receiveWardName;
    private String receiveDistrictName;
    private String receiveProvinceName;

    public UserReceiveItemInfo() {
        this.receiverName = "";
        this.receiverPhone = "";
        this.receiveAddress = "";
        this.receiveWardName = "";
        this.receiveDistrictName = "";
        this.receiveProvinceName = "";
    }

    public UserReceiveItemInfo(String receiverName, String receiverPhone, String receiveAddress, String receiveWardName, String receiveDistrictName, String receiveProvinceName) {
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.receiveAddress = receiveAddress;
        this.receiveWardName = receiveWardName;
        this.receiveDistrictName = receiveDistrictName;
        this.receiveProvinceName = receiveProvinceName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public String getReceiveWardName() {
        return receiveWardName;
    }

    public void setReceiveWardName(String receiveWardName) {
        this.receiveWardName = receiveWardName;
    }

    public String getReceiveDistrictName() {
        return receiveDistrictName;
    }

    public void setReceiveDistrictName(String receiveDistrictName) {
        this.receiveDistrictName = receiveDistrictName;
    }

    public String getReceiveProvinceName() {
        return receiveProvinceName;
    }

    public void setReceiveProvinceName(String receiveProvinceName) {
        this.receiveProvinceName = receiveProvinceName;
    }
    
    
}
