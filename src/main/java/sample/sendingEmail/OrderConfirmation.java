/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.sendingEmail;

import java.util.List;
import sample.orderUtils.UserReceiveItemInfo;
import sample.phone.PhoneDTO;

/**
 *
 * @author Lenovo
 */
public class OrderConfirmation {
    
    public String toCurrencyFormat(int price)
    {
        String standard = "";
        String priceString = "" + price;
        int count = 0;
        for (int i = priceString.length() - 1; i >= 0; i--) {
            count++;
            standard += priceString.charAt(i);
            if(count == 3)
            {
                count = 0;
                standard += ".";
            }
        }
        
        String rs = "";
        for (int i = standard.length() - 1; i >= 0; i--) {
            rs += standard.charAt(i);
        }
        return rs;
    }

    public String getTemplete(String shopName, UserReceiveItemInfo receiveInfo, String orderID, String paymentMethod, List<PhoneDTO> phones) {

        String phoneString = "";
        int count = 0;
        int totalPrice = 0;
        for (PhoneDTO phone : phones) {
            count++;
            totalPrice += phone.getPrice() * phone.getQuantity();
            phoneString += "          <tr>\n"
                    + "            <td>" + count + "</td>\n"
                    + "            <td>" + phone.getPhoneName() + "</td>\n"
                    + "            <td>" + toCurrencyFormat(phone.getPrice()) + "</td>\n"
                    + "            <td>" + phone.getQuantity() + "</td>\n"
                    + "            <td>" + toCurrencyFormat(phone.getPrice() * phone.getQuantity()) + "</td>\n"
                    + "          </tr>\n";
        }

        String templete = "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "  <head>\n"
                + "    <meta charset=\"UTF-8\" />\n"
                + "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n"
                + "    <title>Confirm Code Templete</title>\n"
                + "\n"
                + "    <style>\n"
                + "      body {\n"
                + "        width: 100%;\n"
                + "        height: 100vh;\n"
                + "        font-family: monospace;\n"
                + "        background-color: #f3f4f8;\n"
                + "      }\n"
                + "\n"
                + "      table,\n"
                + "      td,\n"
                + "      th {\n"
                + "        border: 1px solid;\n"
                + "        padding: 5px;\n"
                + "      }\n"
                + "\n"
                + "      table {\n"
                + "        width: 100%;\n"
                + "        border-collapse: collapse;\n"
                + "      }\n"
                + "\n"
                + "      #root {\n"
                + "        width: 40%;\n"
                + "        height: fit-content;\n"
                + "        padding: 10px 20px;\n"
                + "        border: 1px solid black;\n"
                + "        background-color: white;\n"
                + "        border-radius: 10px;\n"
                + "        text-align: center;\n"
                + "      }\n"
                + "\n"
                + "      .shopName {\n"
                + "        font-size: 60px;\n"
                + "      }\n"
                + "\n"
                + "      .title {\n"
                + "        font-size: 30px;\n"
                + "      }\n"
                + "\n"
                + "      .des {\n"
                + "        font-size: 19px;\n"
                + "        text-align: justify;\n"
                + "      }\n"
                + "\n"
                + "      .receive-info {\n"
                + "        background-color: #f3f4f8;\n"
                + "        width: 100%;\n"
                + "        height: auto;\n"
                + "        color: black;\n"
                + "        font-size: 14px;\n"
                + "        border-radius: 5px;\n"
                + "        padding: 10px;\n"
                + "        text-align: left;\n"
                + "      }\n"
                + "\n"
                + "      .receive-info span {\n"
                + "        font-weight: bold;\n"
                + "      }\n"
                + "\n"
                + "      .order-info\n"
                + "      {\n"
                + "        margin: 15px 0;\n"
                + "      }\n"
                + "    </style>\n"
                + "  </head>\n"
                + "  <body>\n"
                + "    <div id=\"root\">\n"
                + "      <h1 class=\"shopName\">" + shopName + "</h1>\n"
                + "      <h4 class=\"title\">Order Confirmation</h4>\n"
                + "      <p class=\"des\">\n"
                + "        You just placed an order. Below is our shop's automatic email to confirm\n"
                + "        the order.\n"
                + "      </p>\n"
                + "      <p class=\"des\">\n"
                + "        Receive Information:\n"
                + "      </p>\n"
                + "      <div class=\"receive-info\">\n"
                + "        <div class=\"receiver-name\">\n"
                + "          <span>Receiver Name:</span> " + receiveInfo.getReceiverName() + "\n"
                + "        </div>\n"
                + "        <div class=\"receiver-phone\">\n"
                + "          <span>Receiver Phone:</span> 0963723616\n"
                + "        </div>\n"
                + "        <div class=\"receiver-address\"><span>Receiver Address:</span> " + receiveInfo.getReceiveAddress() + "</div>\n"
                + "        <div class=\"receiver-ward\"><span>Receiver Ward:</span> " + receiveInfo.getReceiveWardName() + "</div>\n"
                + "        <div class=\"receiver-district\"><span>Receive District:</span> " + receiveInfo.getReceiveDistrictName() + "</div>\n"
                + "        <div class=\"receiver-province\"><span>Receive City:</span> " + receiveInfo.getReceiveProvinceName() + "</div>\n"
                + "        <div class=\"payment-method\"><span>Payment Method: </span> " + paymentMethod + "</div>\n"
                + "        <div class=\"orderID\"><span>Order ID: </span> " + orderID + "</div>\n"
                + "      </div>\n"
                + "      <p class=\"des\">\n"
                + "        Order Information:\n"
                + "      </p>\n"
                + "      <div class=\"order-info\">\n"
                + "        <table>\n"
                + "          <tr>\n"
                + "            <th>No</th>\n"
                + "            <th>Name</th>\n"
                + "            <th>Price each </th>\n"
                + "            <th>Quantity</th>\n"
                + "            <th>Price</th>\n"
                + "          </tr>\n" 
                +
                                phoneString
                + "          <tr>\n"
                + "            <th colspan=\"2\">TOTAL PRICE: </th>\n"
                + "            <th colspan=\"3\">"+ toCurrencyFormat(totalPrice) +"</th>\n"
                + "          </tr>\n"
                + "        </table>\n"
                + "      </div>\n"
                + "    </div>\n"
                + "  </body>\n"
                + "</html>";

        return templete;
    }
}
