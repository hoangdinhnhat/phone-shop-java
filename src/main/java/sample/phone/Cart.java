/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.phone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Lenovo
 */
public class Cart {

    private Map<String, PhoneDTO> cart;
    private List<String> phoneIDs = new ArrayList<>();

    public Cart() {
    }

    public Cart(Map<String, PhoneDTO> cart, List<String> phoneIDs) {
        this.cart = cart;
        this.phoneIDs = phoneIDs;
    }

    public Map<String, PhoneDTO> getCart() {
        return cart;
    }

    public void setCart(Map<String, PhoneDTO> cart) {
        this.cart = cart;
    }

    public List<String> getPhoneIDs() {
        return phoneIDs;
    }

    public void setPhoneIDs(List<String> phoneIDs) {
        this.phoneIDs = phoneIDs;
    }

    public boolean add(PhoneDTO phone) {
        boolean check = false;
        if (this.cart == null) {
            this.cart = new HashMap<String, PhoneDTO>();
        }
        if (this.cart.containsKey(phone.getPhoneID())) {
            int currentQuantity = this.cart.get(phone.getPhoneID()).getQuantity();
            int newQuantity = currentQuantity + phone.getQuantity();
            phone.setQuantity(newQuantity);
            return check = this.cart.replace(phone.getPhoneID(), this.cart.get(phone.getPhoneID()), phone);
        }
        PhoneDTO addedPhone = this.cart.put(phone.getPhoneID(), phone);
        phoneIDs.add(phone.getPhoneID());
        if (addedPhone != null) {
            check = true;
        }
        return check;
    }

    public boolean update(String id, PhoneDTO phone) {
        boolean checkUpdate = false;
        if (this.getCart() != null) {
            if (this.getCart().containsKey(id)) {
                PhoneDTO old = this.getCart().get(id);
                checkUpdate = this.getCart().replace(id, old, phone);
            }
        }
        return checkUpdate;
    }

    public boolean remove(String id) {
        boolean checkRemove = false;
        if (this.getCart() != null) {
            if (this.getCart().containsKey(id)) {
                PhoneDTO removedPhone = this.getCart().remove(id);
                phoneIDs.remove(id);
                if (removedPhone != null) {
                    checkRemove = true;
                }
            }
        }
        return checkRemove;
    }
}
