/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.orderUtils;

/**
 *
 * @author Lenovo
 */
public class Tester {

    public static void main(String[] args) {
        GHN ghn = new GHN();
        UserReceiveItemInfo info = new UserReceiveItemInfo("Hoang Dinh Bao", "0914514345", "thon Tan Hiep", "tanlien", "huonghoa", "quangtri");
        ghn.shippingOrders(info, null, "KHONGCHOXEMHANG");
    }
}
