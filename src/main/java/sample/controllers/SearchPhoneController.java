/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import sample.phone.PhoneDAO;
import sample.phone.PhoneDTO;
import sample.phone.PhoneSeriesDTO;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "SearchPhoneController", urlPatterns = {"/SearchPhoneController"})
public class SearchPhoneController extends HttpServlet {
    
    private static final Logger LOGGER = Logger.getLogger(SearchPhoneController.class);

    private static final String ERROR = "shopping.jsp";
    private static final String SUCCESS = "shopping.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String search = request.getParameter("search");
            if(search == null) search = "";
            PhoneDAO dao = new PhoneDAO();
            List<PhoneDTO> listPhone = dao.getListPhone(search);
            List<PhoneSeriesDTO> listSeries = dao.getListSeries("");
            if(listPhone.size() > 0 && listSeries.size() > 0)
            {
                request.setAttribute("LIST_PHONE", listPhone);
                request.setAttribute("LIST_SERIES", listSeries);
                url = SUCCESS;
            }
        } catch (Exception e) {
            LOGGER.error("Error at SearchController: " + e.toString());
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
