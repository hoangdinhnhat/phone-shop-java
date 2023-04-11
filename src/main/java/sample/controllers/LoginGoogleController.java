/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import sample.user.UserDAO;
import sample.user.UserDTO;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "LoginGoogleController", urlPatterns = {"/LoginGoogleController"})
public class LoginGoogleController extends HttpServlet {
    
    private static final Logger LOGGER = Logger.getLogger(LoginGoogleController.class);

    private static final String ERROR = "login.jsp";
    private static final String AD = "AD";
    private static final String ADMIN_PAGE = "MainController?action=Search Account&search=&curPage=1";
    private static final String US = "US";
    private static final String US_PAGE = "userProfile.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String userID = request.getParameter("userID");
            String password = request.getParameter("password");
            UserDAO dao = new UserDAO();
            UserDTO googleUser = getGoogleUser(request, response);
            UserDTO userDB = dao.getUserByEmail(googleUser.getEmail());
            if (userDB == null) {
                userDB = googleUser;
                userDB.setPassword(generatePassword());
                userDB.setRoleID(US);
                dao.create2(userDB);
            }
            
            //Author
            request.getSession().setAttribute("LOGIN_USER", userDB);
            String roleID = userDB.getRoleID();
            if (AD.equals(roleID)) {
                url = ADMIN_PAGE;
            } else if (US.equals(roleID)) {
                url = US_PAGE;
            } else {
                request.setAttribute("ERROR", "Your role is not support!");
            }
        } catch (Exception e) {
            LOGGER.error("Error at LoginController" + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    public String generatePassword() {
        String model = "!@#$%^&qwertyuiopasdfghjklzxcvbnm123456789";
        String output = "";
        for (int i = 0; i < 8; i++) {
            int index = getRandomNumber(0, model.length());
            output += model.charAt(index);
        }
        return output;
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public UserDTO getGoogleUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserDTO userDTO = null;
        String credential = request.getParameter("credential");
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                .setAudience(Collections.singletonList("96615940146-a6npdnvt227aiaou542u02q3q38v788t.apps.googleusercontent.com"))
                // Or, if multiple clients access the backend:
                //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
                .build();

// (Receive idTokenString by HTTPS POST)
        GoogleIdToken idToken = null;
        try {
            idToken = verifier.verify(credential);
        } catch (GeneralSecurityException ex) {
            LOGGER.error(ex.toString());
        }
        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();

            // Print user identifier
            String userId = payload.getSubject();

            // Get profile information from payload
            String email = payload.getEmail();
            boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
            String name = (String) payload.get("name");
            String imageURL = (String) payload.get("picture");

            userDTO = new UserDTO("gg@" + userId.substring(1, 10), name, imageURL, email, "", "");

        } else {
            System.out.println("Invalid ID token.");
        }
        return userDTO;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
