/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.recaptcha;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author Lenovo
 */
public class recaptcha {

    /**
     * Validates Google reCAPTCHA V2 or Invisible reCAPTCHA.
     *
     * @param secretKey Secret key (key given for communication between your
     * site and Google)
     * @param response reCAPTCHA response from client side.
     * (g-recaptcha-response)
     * @return true if validation successful, false otherwise.
     */
    public synchronized boolean isCaptchaValid(String secretKey, String response) {
        try {
            String url = "https://www.google.com/recaptcha/api/siteverify",
                    params = "secret=" + secretKey + "&response=" + response;

            HttpURLConnection http = (HttpURLConnection) new URL(url).openConnection();
            http.setDoOutput(true);
            http.setRequestMethod("POST");
            http.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded; charset=UTF-8");
            OutputStream out = http.getOutputStream();
            out.write(params.getBytes("UTF-8"));
            out.flush();
            out.close();

            InputStream res = http.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(res, "UTF-8"));

            String output = "";
            int i;
            while ((i = rd.read()) != -1) {
                output += (char)i;
            }
            Gson gson = new Gson();
            resType rrrr = gson.fromJson(output, resType.class);
            res.close();
            System.out.println(rrrr.getChallenge_ts());
            return rrrr.isSuccess();
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return false;
    }
}
