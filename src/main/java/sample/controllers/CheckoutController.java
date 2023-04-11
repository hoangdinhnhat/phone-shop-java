/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import sample.order.OrderDAO;
import sample.order.OrderDetails;
import sample.orderUtils.CreateOrder;
import sample.orderUtils.GHN;
import sample.orderUtils.UserReceiveItemInfo;
import sample.phone.Cart;
import sample.phone.PhoneDAO;
import sample.phone.PhoneDTO;
import sample.sendingEmail.GMailer;
import sample.sendingEmail.OrderConfirmation;
import sample.user.UserDAO;
import sample.user.UserDTO;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "CheckoutController", urlPatterns = {"/CheckoutController"})
public class CheckoutController extends HttpServlet {
    
    private static final Logger LOGGER = Logger.getLogger(CheckoutController.class);

    private static final String ERROR = "cart.jsp";
//    private static final String SUCCESS = "MainController?action=Search Product&search=";
    private static final String SUCCESS = "confirmOrder.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        boolean isRedirect = false;
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");
            List<PhoneDTO> phones = new ArrayList<>();
            for (PhoneDTO phone : cart.getCart().values()) {
                phones.add(phone);
            }
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");

            PhoneDAO dao = new PhoneDAO();
            boolean isSuccess = dao.checkout(phones);

            if (isSuccess) {
                String receiverName = request.getParameter("receiver-name");
                String receiverPhone = request.getParameter("receiver-phone");
                String receiveAddress = request.getParameter("delivery-add");
                String receiveWardName = request.getParameter("ward");
                String receiveDistrictName = request.getParameter("district");
                String receiveProvinceName = request.getParameter("province");
                String paymentMethod = request.getParameter("payment-method");
                UserReceiveItemInfo urii = new UserReceiveItemInfo(receiverName, receiverPhone, receiveAddress, receiveWardName, receiveDistrictName, receiveProvinceName);

                if (paymentMethod.equals("cod")) {
                    String orderID = "OD" + Math.round(Math.random() * 999999);
                    GHN ghn = new GHN();
                    String expectedDate = ghn.shippingOrders(urii, phones, "KHONGCHOXEMHANG");
                    System.out.println("Expected: " + expectedDate);
                    
                    String pattern = "yyyy-MM-dd";
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                    Date curDate = new Date();
                    String orderDate = simpleDateFormat.format(curDate);
                    
                    OrderDetails orderDetails = new OrderDetails(orderID, orderDate, expectedDate, "COD", "GHN", phones);
                    request.setAttribute("ORDER_DETAILS", orderDetails);
                    
                    session.setAttribute("CART", null);
                    
                    GMailer gMailer = new GMailer();
                    OrderConfirmation confirmation = new OrderConfirmation();
                    String templete = confirmation.getTemplete("Shopping Cart", urii, orderID, paymentMethod.toUpperCase(), phones);
                    
                    gMailer.sendEmail("ORDER CONFIRMATION", templete, loginUser.getEmail());
                    OrderDAO orderDao = new OrderDAO();
                    orderDao.CreateOrder(loginUser, orderDetails);
                    url = SUCCESS;
                } else {
                    request.getSession().setAttribute("PENDING_PAYMENT", urii);
                    CreateOrder zalopay = new CreateOrder();
                    
                    int total = 0;
                    for (PhoneDTO phone : phones) {
                        total += phone.getPrice();
                    }
                    String orderURL = zalopay.zalopayGateway(total);
                    
                    if (!orderURL.isEmpty()) {
                        response.sendRedirect(orderURL);
                        isRedirect = true;
                    }
                }
            } else {
                String errorMsg = "Some phone is sold out! There are ";
                for (PhoneDTO phone : phones) {
                    PhoneDTO tempPhone = dao.getPhone(phone.getPhoneID());
                    errorMsg += tempPhone.getPhoneName() + ": " + tempPhone.getQuantity() + " item;";
                }
                request.setAttribute("CHECKOUT_ERROR", errorMsg);
            }

        } catch (Exception e) {
            LOGGER.error("Error at SearchController: " + e.toString());
        } finally {
            if (!isRedirect) {
                request.getRequestDispatcher(url).forward(request, response);
            }
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
