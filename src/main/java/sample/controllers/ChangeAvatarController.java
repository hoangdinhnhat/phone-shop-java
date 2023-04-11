/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.log4j.Logger;
import sample.test.Test;
import sample.user.UserDAO;
import sample.user.UserDTO;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "ChangeAvatarController", urlPatterns = {"/ChangeAvatarController"})
@MultipartConfig
public class ChangeAvatarController extends HttpServlet {
    
    private static final Logger LOGGER = Logger.getLogger(ChangeAvatarController.class);

    private static final String ERROR = "userProfile.jsp";
    private static final String SUCCESS = "userProfile.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            Part filepart = request.getPart("imgFile");
            String exten = filepart.getContentType();
            String[] extens = exten.split("/");

            if (extens[1].equals("jpeg") || extens[1].equals("png")) {
                InputStream input = filepart.getInputStream();
                String path = Test.class.getResource("../").getPath().substring(1);
                String temp[] = path.split("/");
                int position = temp.length - 1 - 5;
                String positionPath = "";
                for (int i = 0; i <= position; i++) {
                    positionPath += temp[i] + "\\";
                }
                positionPath += "src\\main\\webapp\\avatar\\";
                positionPath += String.format("%s.avatar", loginUser.getUserID());
                OutputStream output = new FileOutputStream(positionPath);
                int i;
                while ((i = input.read()) != -1) {
                    output.write(i);
                }
                UserDAO dao = new UserDAO();
                UserDTO newLoginUser = new UserDTO(loginUser.getUserID(), loginUser.getFullName(), "avatar/" + loginUser.getUserID() + ".avatar", loginUser.getEmail(), loginUser.getPassword(), loginUser.getRoleID());
                dao.changeAvatar(newLoginUser);
                session.setAttribute("LOGIN_USER", newLoginUser);
                url = SUCCESS;
            }
        } catch (Exception e) {
            LOGGER.error("Error at ChangeAvatarController " + e.toString());
        } finally {
            response.setContentType("text/html");
            response.getWriter().println("<script>window.close()</script>");
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
