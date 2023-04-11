/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import sample.phone.PhoneDAO;
import sample.phone.PhoneDTO;
import sample.user.UserDAO;
import sample.user.UserDTO;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "SearchAccountController", urlPatterns = {"/SearchAccountController"})
public class SearchAccountController extends HttpServlet {
    
    private static final Logger LOGGER = Logger.getLogger(SearchAccountController.class);

    private static final String ERROR = "admin.jsp";
    private static final String SUCCESS = "admin.jsp";
    private static int curPage = 1;

    protected boolean isNumber(String number) {
        boolean rs = true;
        try {
            Integer.parseInt(number);
        } catch (Exception e) {
            rs = false;
        }
        return rs;
    }

    protected void updateCurPage(int requestPage, String search, UserDAO dao) throws SQLException {
        List<UserDTO> listUser = dao.getListUser(search, requestPage);
        if (listUser.size() > 0) {
            curPage = requestPage;
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            UserDAO dao = new UserDAO();
            List<UserDTO> listUser = null;
            String search = request.getParameter("search");
            String curPageString = request.getParameter("curPage");

            if(curPageString == null)
            {
                updateCurPage(1, search, dao);
            }else if (curPageString.equals("next")) {
                updateCurPage(curPage + 1, search, dao);
            } else if (curPageString.equals("first")) {
                updateCurPage(1, search, dao);
            } else if (curPageString.equals("previous")) {
                updateCurPage(curPage - 1, search, dao);
            } else if (isNumber(curPageString)) {
                int requestCurPage = Integer.parseInt(curPageString);
                updateCurPage(requestCurPage, search, dao);
            }

            listUser = dao.getListUser(search, curPage);
            if (listUser.size() > 0) {
                request.setAttribute("LIST_USER", listUser);
                url = SUCCESS;
            }
        } catch (Exception e) {
            LOGGER.error("Error at SearchController: " + e.toString());
        } finally {
            request.setAttribute("curPage", curPage);
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
