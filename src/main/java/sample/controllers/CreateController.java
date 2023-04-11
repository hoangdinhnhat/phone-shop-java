/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import sample.recaptcha.recaptcha;
import sample.sendingEmail.ConfirmCodeTemplete;
import sample.sendingEmail.GMailer;
import sample.user.UserDAO;
import sample.user.UserDTO;
import sample.user.UserError;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "CreateController", urlPatterns = {"/CreateController"})
public class CreateController extends HttpServlet {
    
    private static final Logger LOGGER = Logger.getLogger(CreateController.class);

    private static final String ERROR = "signup.jsp";
    private static final String SUCCESS = "registeConfirmPage.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        UserError userError = new UserError();
        try {
            String userID = request.getParameter("userID");
            String fullName = request.getParameter("fullName");
            String roleID = request.getParameter("roleID");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String confirm = request.getParameter("confirm");
            recaptcha test = new recaptcha();
            boolean checkValidation = true;
            //Check existed userID
            UserDAO dao = new UserDAO();
            boolean checkExist = dao.checkDuplicate(userID);
            UserDTO userEmail = dao.getUserByEmail(email);
            if (checkExist) {
                checkValidation = false;
                userError.setUserIDError("Duplicate UserID!");
            }
            
            if(userEmail != null)
            {
                checkValidation = false;
                userError.setEmailError("Duplicate Email!");
            }

            if (userID.length() > 10 || userID.length() < 2) {
                checkValidation = false;
                userError.setUserIDError("UserID size need to be in (2, 10)");
            }
            if (fullName.length() > 20 || fullName.length() < 2) {
                checkValidation = false;
                userError.setFullNameError("FullName size need to be in (2, 20)");
            }
            if (password.length() > 10 || password.length() < 2) {
                checkValidation = false;
                userError.setPasswordError("Password size need to be in (2, 10)");
            }
            if (!password.equals(confirm)) {
                checkValidation = false;
                userError.setConfirmError("Hai password khong giong nhau");
            }
            if (!test.isCaptchaValid("6LdvLckjAAAAAO7lN40MZzN3B611lOgeuRBJ6Pnf", request.getParameter("g-recaptcha-response"))) {
                checkValidation = false;
                userError.setCaptchaError("Captcha isn't valid!");
            }
            if (checkValidation) {
                UserDTO user = new UserDTO(userID, fullName, "", email, password, roleID);
                request.getSession().setAttribute("PENDING_USER", user);
//                boolean checkInsert = dao.create(user);
//                boolean checkInsert = dao.create2(user);
                int registeCode = Math.round((float) Math.random() * (900000) + 100000);
                request.getSession().setAttribute("REGISTE_CODE", "" + registeCode);
                ConfirmCodeTemplete confirmCodeTemplete = new ConfirmCodeTemplete();
                String message = confirmCodeTemplete.getTemplete("Shopping Cart", "" + registeCode);
                GMailer gMailer = new GMailer();
                gMailer.sendEmail("Register Confirm Code", message, email);
                url = SUCCESS;
            } else {
                request.setAttribute("USER_ERROR", userError);
            }
        } catch (Exception e) {
            LOGGER.error("Error at CreateController: " + e.toString());
            request.setAttribute("ERROR", "Unknown ERROR");
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
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
