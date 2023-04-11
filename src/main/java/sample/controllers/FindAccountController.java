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
import sample.sendingEmail.ForgetCodeTemplete;
import sample.sendingEmail.GMailer;
import sample.user.UserDAO;
import sample.user.UserDTO;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "FindAccountController", urlPatterns = {"/FindAccountController"})
public class FindAccountController extends HttpServlet {
    
    private static final Logger LOGGER = Logger.getLogger(FindAccountController.class);
    
    private static final String ERROR = "FindAccount.jsp";
    private static final String SUCCESS = "ConfirmCode.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String emailToFind = request.getParameter("email-forget-pass");
            UserDAO dao = new UserDAO();
            UserDTO findedUser = dao.getUserByEmail(emailToFind);
            if(findedUser != null)
            {
                int forgetCode = Math.round((float) Math.random() * (900000) + 100000);
                GMailer gMailer = new GMailer();
                ForgetCodeTemplete templetes = new ForgetCodeTemplete();
                String templete = templetes.getTemplete("SHOPPING CART", "" + forgetCode);
                gMailer.sendEmail("FORGET CODE CONFIRMATION", templete, emailToFind);
                request.getSession().setAttribute("FORGET_CODE", "" + forgetCode);
                request.getSession().setAttribute("PENDING_NEW_PASS", findedUser);
                url = SUCCESS;
            }else
            {
                request.setAttribute("ERROR", "Can't find the account with this email. Please try again!");
            }
        } catch (Exception e) {
            LOGGER.error("Error at FindAccountControler " + e.toString());
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
