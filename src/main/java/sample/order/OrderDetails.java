/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.order;

import java.util.List;
import sample.phone.PhoneDTO;

/**
 *
 * @author Lenovo
 */
public class OrderDetails {

    private String orderID;
    private String orderDate;
    private String expectedDeliveryDate;
    private String paymentMethod;
    private String shippingUnit;
    List<PhoneDTO> phones;

    public OrderDetails() {
    }

    public OrderDetails(String orderID, String orderDate, String expectedDeliveryDate, String paymentMethod, String shippingUnit, List<PhoneDTO> phones) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.expectedDeliveryDate = expectedDeliveryDate;
        this.paymentMethod = paymentMethod;
        this.shippingUnit = shippingUnit;
        this.phones = phones;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(String expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getShippingUnit() {
        return shippingUnit;
    }

    public void setShippingUnit(String shippingUnit) {
        this.shippingUnit = shippingUnit;
    }

    public List<PhoneDTO> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneDTO> phones) {
        this.phones = phones;
    }

    
}
