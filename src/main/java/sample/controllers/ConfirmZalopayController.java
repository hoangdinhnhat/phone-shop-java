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
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import sample.crypto.HMACUtil;
import sample.order.OrderDAO;
import javax.xml.bind.DatatypeConverter;
import sample.order.OrderDetails;
import sample.orderUtils.GHN;
import sample.orderUtils.Status;
import sample.orderUtils.UserReceiveItemInfo;
import sample.orderUtils.getStatusOfOrder;
import sample.phone.Cart;
import sample.phone.PhoneDTO;
import sample.sendingEmail.GMailer;
import sample.sendingEmail.OrderConfirmation;
import sample.user.UserDTO;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "ConfirmZalopayController", urlPatterns = {"/ConfirmZalopayController"})
public class ConfirmZalopayController extends HttpServlet {
    
    private static final Logger LOGGER = Logger.getLogger(ConfirmZalopayController.class);

    private static final String ERROR = "cart.jsp";
    private static final String SUCCESS = "confirmOrder.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        String key2 = "trMrHtvjo6myautxDUiAcYsVtaeQ8nhf";
        String mac = HMACUtil.HMACSHA256;
        String checksumData = request.getParameter("appid") + "|" + request.getParameter("apptransid") + "|" + request.getParameter("pmcid") + "|" + request.getParameter("bankcode") + "|"
                + request.getParameter("amount") + "|" + request.getParameter("discountamount") + "|" + request.getParameter("status");
        String checksum = HMACUtil.HMacHexStringEncode(mac, key2, checksumData);

        try {
            JSONObject result = new JSONObject();
            if (!checksum.equals(request.getParameter("checksum"))) {
                response.sendError(400, "Bad request");
                return;
            } else {
                getStatusOfOrder getStatusOfOrder1 = new getStatusOfOrder();
                Status status = getStatusOfOrder1.get(request.getParameter("apptransid"));
                if (status.getReturn_code() == 1) {
                    String orderID = "OD" + Math.round(Math.random() * 999999);
                    HttpSession session = request.getSession();
                    Cart cart = (Cart) session.getAttribute("CART");
                    UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");

                    List<PhoneDTO> phones = new ArrayList<>();
                    for (PhoneDTO phone : cart.getCart().values()) {
                        phones.add(phone);
                    }
                    UserReceiveItemInfo urii = (UserReceiveItemInfo) session.getAttribute("PENDING_PAYMENT");
                    GHN ghn = new GHN();
                    String expectedDate = ghn.shippingOrders(urii, phones, "KHONGCHOXEMHANG");

                    String pattern = "yyyy-MM-dd";
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                    Date curDate = new Date();
                    String orderDate = simpleDateFormat.format(curDate);

                    OrderDetails orderDetails = new OrderDetails(orderID, orderDate, expectedDate, "ZALOPAY", "GHN", phones);
                    request.setAttribute("ORDER_DETAILS", orderDetails);

                    GMailer gMailer = new GMailer();
                    OrderConfirmation confirmation = new OrderConfirmation();
                    String templete = confirmation.getTemplete("Shopping Cart", urii, orderID, "ZALOPAY", phones);

                    gMailer.sendEmail("ORDER CONFIRMATION", templete, loginUser.getEmail());
                    OrderDAO orderDao = new OrderDAO();
                    orderDao.CreateOrder(loginUser, orderDetails);
                    session.setAttribute("CART", null);
                    session.setAttribute("PENDING_PAYMENT", null);

                    url = SUCCESS;
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error at ConfirmZalopayController " + e.toString());
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
