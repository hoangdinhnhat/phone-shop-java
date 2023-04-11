/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author Lenovo
 */
public class MainController extends HttpServlet {
    
    private static final Logger LOGGER = Logger.getLogger(MainController.class);

    private static final String ERROR = "error.jsp";

    private static final String LOGIN = "Login";
    private static final String LOGIN_CONTROLLER = "LoginController";
    private static final String SEARCH_PHONE = "Search Product";
    private static final String SEARCH_PHONE_CONTROLLER = "SearchPhoneController";
    private static final String SEARCH_ACCOUNT = "Search Account";
    private static final String SEARCH_ACCOUNT_CONTROLLER = "SearchAccountController";
    private static final String DELETE = "Delete";
    private static final String DELETE_CONTROLLER = "DeleteController";
    private static final String UPDATE = "Update";
    private static final String UPDATE_CONTROLLER = "UpdateController";
    private static final String CHANGE_PASSWORD = "ChangePassword";
    private static final String CHANGE_PASSWORD_CONTROLLER = "ChangePasswordController";
    private static final String CREATE = "Create";
    private static final String CREATE_CONTROLLER = "CreateController";
    private static final String ADD = "Add";
    private static final String ADD_CONTROLLER = "AddController";
    private static final String VIEW = "View";
    private static final String VIEW_PAGE = "viewCart.jsp";
    private static final String EDIT = "Edit";
    private static final String EDIT_CONTROLLER = "EditController";
    private static final String REMOVE = "Remove";
    private static final String REMOVE_CONTROLLER = "RemoveController";
    private static final String LOGOUT = "Logout";
    private static final String LOGOUT_CONTROLLER = "LogoutController";
    private static final String REGISTE_CONFIRM = "Registe Confirm";
    private static final String REGISTE_CONFIRM_CONTROLLER = "RegisteConfirmController";
    private static final String LOGIN_WITH_GOOGLE = "LoginWithGoogle";
    private static final String LOGIN_WITH_GOOGLE_CONTROLLER = "LoginGoogleController";
    private static final String CHECKOUT = "Checkout";
    private static final String CHECKOUT_CONTROLLER = "CheckoutController";
    private static final String ZALOPAY_CONFIRM = "ZaloConfirm";
    private static final String ZALOPAY_CONFIRM_CONTROLLER = "ConfirmZalopayController";
    private static final String ORDER_PLACED = "OrderPlaced";
    private static final String ORDER_PLACED_CONTROLLER = "SearchOrderController";
    private static final String FIND_ACCOUNT = "FindAccount";
    private static final String FIND_ACCOUNT_CONTROLLER = "FindAccountController";
    private static final String CONFIRM_FORGET_CODE = "CheckForgetCode";
    private static final String CONFIRM_FORGET_CODE_CONTROLLER = "ConfirmForgetCodeController";
    private static final String CHANGE_AVATAR = "ChangeAvatar";
    private static final String CHANGE_AVATAR_CONTROLLER = "ChangeAvatarController";
    private static final String SHOW_TWO = "show_two";
    private static final String SHOW_TWO_CONTROLLER = "ShowTwoController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            if (LOGIN.equals(action)) {
                url = LOGIN_CONTROLLER;
            } else if (SEARCH_PHONE.equals(action)) {
                url = SEARCH_PHONE_CONTROLLER;
            } else if (SEARCH_ACCOUNT.equals(action)) {
                url = SEARCH_ACCOUNT_CONTROLLER;
            } else if (DELETE.equals(action)) {
                url = DELETE_CONTROLLER;
            } else if (UPDATE.equals(action)) {
                url = UPDATE_CONTROLLER;
            } else if (CREATE.equals(action)) {
                url = CREATE_CONTROLLER;
            } else if (ADD.equals(action)) {
                url = ADD_CONTROLLER;
            } else if (VIEW.equals(action)) {
                url = VIEW_PAGE;
            } else if (EDIT.equals(action)) {
                url = EDIT_CONTROLLER;
            } else if (REMOVE.equals(action)) {
                url = REMOVE_CONTROLLER;
            } else if (LOGOUT.equals(action)) {
                url = LOGOUT_CONTROLLER;
            } else if (LOGIN_WITH_GOOGLE.equals(action)) {
                url = LOGIN_WITH_GOOGLE_CONTROLLER;
            } else if (CHECKOUT.equals(action)) {
                url = CHECKOUT_CONTROLLER;
            } else if (ZALOPAY_CONFIRM.equals(action)) {
                url = ZALOPAY_CONFIRM_CONTROLLER;
            } else if (ORDER_PLACED.equals(action)) {
                url = ORDER_PLACED_CONTROLLER;
            } else if (REGISTE_CONFIRM.equals(action)) {
                url = REGISTE_CONFIRM_CONTROLLER;
            } else if (FIND_ACCOUNT.equals(action)) {
                url = FIND_ACCOUNT_CONTROLLER;
            } else if (CONFIRM_FORGET_CODE.equals(action)) {
                url = CONFIRM_FORGET_CODE_CONTROLLER;
            } else if (CHANGE_AVATAR.equals(action)) {
                url = CHANGE_AVATAR_CONTROLLER;
            }else if (CHANGE_PASSWORD.equals(action)) {
                url = CHANGE_PASSWORD_CONTROLLER;
            }else if (SHOW_TWO.equals(action)) {
                url = SHOW_TWO_CONTROLLER;
            }
            else {
                request.setAttribute("ERROR", "Your role is not support!");
            }

        } catch (Exception e) {
            LOGGER.error("Error at MainController: " + e.toString());
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
