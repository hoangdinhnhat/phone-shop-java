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
import sample.phone.Cart;
import sample.phone.PhoneDTO;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "RemoveController", urlPatterns = {"/RemoveController"})
public class RemoveController extends HttpServlet {
    
    private static final Logger LOGGER = Logger.getLogger(RemoveController.class);

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "cart.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String productID = request.getParameter("productID");
            Cart cart = (Cart) request.getSession().getAttribute("CART");
            if (cart != null) {
                if (cart.getCart().containsKey(productID)) {
                    boolean isSuccess = cart.remove(productID);
                    if (isSuccess) {
                        if(cart.getCart().size() == 0)
                        {
                            request.getSession().setAttribute("CART", null);
                        }else
                        {
                            request.getSession().setAttribute("CART", cart);
                        }
                        url = SUCCESS;
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error at RemoveController: " + e.toString());
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
