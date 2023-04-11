/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.sendingEmail;

import com.sendgrid.*;
import java.io.IOException;

public class GMailer {

    private static final String SHOP_EMAIL = "nhathocjavaweb@gmail.com";

    public GMailer() {

    }

    public void sendEmail(String subject, String message, String toEmail) throws IOException
    {
        Email from = new Email(SHOP_EMAIL);
        Email to = new Email(toEmail);
        Content content = new Content("text/html", message);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid("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");
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
    }

}
