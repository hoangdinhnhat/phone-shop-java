/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.orderUtils;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject; // https://mvnrepository.com/artifact/org.json/json

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import sample.phone.PhoneDTO;

public class GHN {

    private Map<String, String> config = new HashMap<String, String>() {
        {
            put("shop_id", "122248");
            put("token", "fc0ea700-c65d-11ed-ab31-3eeb4194879e");
            put("endpoint", "https://dev-online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/create");
        }
    };

    public String shippingOrders(UserReceiveItemInfo info, List<PhoneDTO> phones, String requiredNote) {
        String expectedDeliveryDate = "";
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost post = new HttpPost(config.get("endpoint"));
            post.setHeader("Content-type", "application/json");
            
            String phoneString = "                             {\n"
                    + "                                 \"name\":\"" + phones.get(0).getPhoneName() + "\",\n"
                    + "                                 \"code\":\"" + phones.get(0).getPhoneID() + "\",\n"
                    + "                                 \"quantity\": " + phones.get(0).getQuantity() + ",\n"
                    + "                                 \"price\": " + phones.get(0).getPrice() + ",\n"
                    + "                                 \"length\": 12,\n"
                    + "                                 \"width\": 12,\n"
                    + "                                 \"height\": 12,\n"
                    + "                                 \"weight\": 12,\n"
                    + "                                 \"category\": \n"
                    + "                                 {\n"
                    + "                                     \"level1\":\"" + phones.get(0).getPhoneID() + "\"\n"
                    + "                                 }\n"
                    + "                             }\n";
            for (int i = 1; i < phones.size(); i++) {
                phoneString += "                             ,{\n"
                        + "                                 \"name\":\"" + phones.get(i).getPhoneName() + "\",\n"
                        + "                                 \"code\":\"" + phones.get(i).getPhoneID() + "\",\n"
                        + "                                 \"quantity\": " + phones.get(i).getQuantity() + ",\n"
                        + "                                 \"price\": " + phones.get(i).getPrice() + ",\n"
                        + "                                 \"length\": 12,\n"
                        + "                                 \"width\": 12,\n"
                        + "                                 \"height\": 12,\n"
                        + "                                 \"weight\": 12,\n"
                        + "                                 \"category\": \n"
                        + "                                 {\n"
                        + "                                     \"level1\":\"" + phones.get(i).getPhoneID() + "\"\n"
                        + "                                 }\n"
                        + "                             }\n";
            }

            String json = "{\n"
                    + "                        \"from_name\":\"Hoang Dinh Nhat's Shop\",\n"
                    + "                        \"from_phone\":\"0963723616\",\n"
                    + "                        \"from_address\":\"Thanh pho Ho Chi Minh\",\n"
                    + "                        \"from_ward_name\":\"longthanhmy\",\n"
                    + "                        \"from_district_name\":\"thuduc\",\n"
                    + "                        \"from_province_name\":\"hochiminh\",\n"
                    + "\"return_name\": \"Hoang Dinh Nhat's Shop\",\n"
                    + "                        \"return_phone\": \"0963723616\",\n"
                    + "                        \"return_address\": \"Thanh pho Ho Chi Minh\",\n"
                    + "                        \"return_ward_name\": \"longthanhmy\",\n"
                    + "                        \"return_district_name\": \"thuduc\",\n"
                    + "                        \"return_province_name\":\"hochiminh\","
                    + "                        \"to_name\": \"" + info.getReceiverName() + "\",\n"
                    + "                        \"to_phone\": \"" + info.getReceiverPhone() + "\",\n"
                    + "                        \"to_address\": \"" + info.getReceiveAddress() + "\",\n"
                    + "                        \"to_ward_name\": \"" + info.getReceiveWardName() + "\",\n"
                    + "                        \"to_district_name\": \"" + info.getReceiveDistrictName() + "\",\n"
                    + "                        \"to_province_name\": \"" + info.getReceiveProvinceName() + "\",\n"
                    + "                        \"weight\": 10000,\n"
                    + "                        \"length\": 100,\n"
                    + "                        \"width\": 100,\n"
                    + "                        \"height\": 100,\n"
                    + "                        \"insurance_value\": 10000000,\n"
                    + "                        \"service_type_id\": 2,\n"
                    + "                        \"payment_type_id\": 2,\n"
                    + "                        \"required_note\": \"" + requiredNote + "\",\n"
                    + "                        \"cod_amount\": 10000000,\n"
                    + "                        \"items\": [\n"
                    + phoneString
                    + "                             \n"
                    + "                         ]\n"
                    + "                    }";
            StringEntity entity = new StringEntity(json);
            entity.setContentType(ContentType.APPLICATION_JSON.getMimeType());

            // Content-Type: application/x-www-form-urlencoded
            post.setEntity(entity);
            post.addHeader("token", config.get("token"));
            post.addHeader("shop_id", config.get("shop_id"));

            CloseableHttpResponse res = client.execute(post);
            BufferedReader rd = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
            StringBuilder resultJsonStr = new StringBuilder();
            String line;

            while ((line = rd.readLine()) != null) {
                resultJsonStr.append(line);
            }

            JSONObject result = new JSONObject(resultJsonStr.toString());
            String code = "";
            String codeMessage = "";
            for (String key : result.keySet()) {
                if (key.equals("code")) {
                    code = result.get(key).toString();
                }
                if (key.equals("code_message")) {
                    codeMessage = result.get(key).toString();
                }
                if(key.equals("data"))
                {
                    String temps [] = result.get(key).toString().split(",");
                    String temp2 = temps[3];
                    String temp3 = temp2.split(":")[1];
                    //"2023-03-18T23
                    String temp4 = temp3.split("T")[0].substring(1);
                    expectedDeliveryDate = temp4;
                }
                System.out.format("%s = %s\n", key, result.get(key));
            }
            if (!code.equals("200")) {
                throw new Exception(codeMessage);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return expectedDeliveryDate;
    }
}
