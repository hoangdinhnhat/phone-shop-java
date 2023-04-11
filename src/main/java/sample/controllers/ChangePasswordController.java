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
import sample.user.UserDAO;
import sample.user.UserDTO;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "ChangePasswordController", urlPatterns = {"/ChangePasswordController"})
public class ChangePasswordController extends HttpServlet {
    
    private static final Logger LOGGER = Logger.getLogger(UpdateController.class);

    private static final String ERROR = "ChangePassword.jsp";
    private static final String SUCCESS = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String userID = request.getParameter("userID");
            String password = request.getParameter("password");
            UserDTO pending = (UserDTO)request.getSession().getAttribute("PENDING_NEW_PASS");
            boolean isValid = true;
            if (password.length() > 10 || password.length() < 2) {
                isValid = false;
                request.setAttribute("ERROR", "Password size need to be in (2, 10)");
                request.setAttribute("PENDING_USER", pending);
            }
            
            if(!userID.equals(pending.getUserID()))
            {
                request.setAttribute("ERROR", "Input data is changed! Please try again!");
                request.setAttribute("PENDING_USER", pending);
            }
            
            if(isValid)
            {
                UserDAO dao = new UserDAO();
                UserDTO newUser = new UserDTO(userID, "", "", "", password, "");
                boolean isSuccess = dao.changePassword(newUser);
                if(isSuccess)
                {
                    request.getSession().setAttribute("PENDING_NEW_PASS", null);
                    url = SUCCESS;
                }
            }
            
        } catch (Exception e) {
            LOGGER.error("Error at ChangePasswordController: " + e.toString());
        }finally
        {
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
