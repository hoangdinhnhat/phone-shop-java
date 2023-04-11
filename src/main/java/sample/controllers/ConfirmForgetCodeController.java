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
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import sample.user.UserDTO;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "ConfirmForgetCodeController", urlPatterns = {"/ConfirmForgetCodeController"})
public class ConfirmForgetCodeController extends HttpServlet {
    
    private static final Logger LOGGER = Logger.getLogger(ConfirmForgetCodeController.class);

    private static final String ERROR = "ConfirmCode.jsp";
    private static final String SUCCESS = "ChangePassword.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            String inputCode = request.getParameter("forget-confirm-code");
            String trueCode = (String) session.getAttribute("FORGET_CODE");
            if(trueCode.equals(inputCode))
            {
                request.setAttribute("PENDING_USER", (UserDTO)session.getAttribute("PENDING_NEW_PASS"));
                session.setAttribute("FORGET_CODE", null);
                url = SUCCESS;
            }else
            {
                request.setAttribute("ERROR", "The forget code is wrong. Please try again!");
            }
        } catch (Exception e) {
            LOGGER.error("Error at ConfirmForgetCodeController " + e.toString());
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
