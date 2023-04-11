/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
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
@WebServlet(name = "RegisteConfirmController", urlPatterns = {"/RegisteConfirmController"})
public class RegisteConfirmController extends HttpServlet {
    
    private static final Logger LOGGER = Logger.getLogger(RegisteConfirmController.class);

    private static final String ERROR = "registeConfirmPage.jsp";
    private static final String SUCCESS = "login.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String registeConfirmCode = request.getParameter("registeConfirmCode");
            String trueCode = (String)request.getSession().getAttribute("REGISTE_CODE");
            System.out.println(registeConfirmCode + ", " + trueCode);
            if(trueCode.equals(registeConfirmCode))
            {
                UserDAO dao = new UserDAO();
                HttpSession session = request.getSession();
                UserDTO user = (UserDTO) session.getAttribute("PENDING_USER");
                boolean checkInsert = dao.create2(user);
                if(checkInsert)
                {
                    url = SUCCESS;
                    session.setAttribute("REGISTE_CODE", null);
                    session.setAttribute("PENDING_USER", null);
                }
                url = SUCCESS;
                request.getSession().setAttribute("REGISTE_CODE", null);
            }
            else
            {
                request.setAttribute("REGISTE_CODE_ERROR", "Wrong registe code!");
            }
        } catch (Exception e) {
            LOGGER.error("Error at RegisteConfirmController: " + e.toString());
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
