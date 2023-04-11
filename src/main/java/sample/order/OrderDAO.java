/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sample.phone.PhoneDTO;
import sample.user.UserDTO;
import sample.utils.DBUtils;

/**
 *
 * @author Lenovo
 */
public class OrderDAO {

    private static final String GET_ORDER_DETAIL = "WITH Rows AS \n"
            + "(\n"
            + "	SELECT ROW_NUMBER() OVER (ORDER BY OD.orderDate DESC) [Row]\n"
            + "	, OD.orderID, OD.orderDate, OD.expectedDeliveryDate, OD.paymentMethod, OD.shippingUnit\n"
            + "	FROM Orders as O INNER JOIN OrderDetails as OD ON O.orderID = OD.orderID \n"
            + "	WHERE O.userID = ?\n"
            + ")\n"
            + "SELECT TOP 10 *\n"
            + "FROM Rows\n"
            + "WHERE Row >= ? AND Row <= ?";
    private static final String GET_PHONES_IN_ORDER = "SELECT P.phoneID, P.phoneName FROM OrderPhone as OP INNER JOIN Phones as P ON OP.phoneID = P.phoneID where OP.orderID = ?";
    private static final String INSERT_TO_ORDERS = "INSERT INTO Orders (orderID, userID) VALUES (?, ?)";
    private static final String INSERT_TO_ORDERDETAILS = "INSERT INTO OrderDetails (orderID, orderDate, expectedDeliveryDate, paymentMethod, shippingUnit) VALUES(?, ?, ?, ?, ?)";
    private static final String INSERT_TO_ORDERPHONE = "INSERT INTO OrderPhone (orderID, phoneID) VALUES(?, ?)";

    public List<OrderDetails> getOrderDetails(String userID, int curPage) throws SQLException {
        int startPage = (curPage - 1) * 5 + 1;
        int endPage = curPage * 5;
        List<OrderDetails> orderDetailses = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ORDER_DETAIL);
                ptm.setString(1, userID);
                ptm.setInt(2, startPage);
                ptm.setInt(3, endPage);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String orderID = rs.getString("orderID");
                    String orderDate = rs.getString("orderDate");
                    String expectedDeliveryDate = rs.getString("expectedDeliveryDate");
                    String paymentMethod = rs.getString("paymentMethod");
                    String shippingUnit = rs.getString("shippingUnit");
                    List<PhoneDTO> phones = getListPhone(orderID);
                    orderDetailses.add(new OrderDetails(orderID, orderDate, expectedDeliveryDate, paymentMethod, shippingUnit, phones));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return orderDetailses;
    }

    public List<PhoneDTO> getListPhone(String orderID) throws SQLException {
        List<PhoneDTO> listPhone = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_PHONES_IN_ORDER);
                ptm.setString(1, orderID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String phoneID = rs.getString("phoneID");
                    String phoneName = rs.getString("phoneName");
                    listPhone.add(new PhoneDTO(phoneID, phoneName, "", "", 0, 0, "", ""));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return listPhone;
    }

    public boolean CreateOrder(UserDTO user, OrderDetails orderDetails) throws SQLException {
        boolean isSuccess = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            conn.setAutoCommit(false);
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT_TO_ORDERS);
                ptm.setString(1, orderDetails.getOrderID());
                ptm.setString(2, user.getUserID());
                ptm.executeUpdate();

                ptm = conn.prepareStatement(INSERT_TO_ORDERDETAILS);
                ptm.setString(1, orderDetails.getOrderID());
                ptm.setString(2, orderDetails.getOrderDate());
                ptm.setString(3, orderDetails.getExpectedDeliveryDate());
                ptm.setString(4, orderDetails.getPaymentMethod());
                ptm.setString(5, orderDetails.getShippingUnit());
                ptm.executeUpdate();

                for (PhoneDTO phone : orderDetails.getPhones()) {
                    ptm = conn.prepareStatement(INSERT_TO_ORDERPHONE);
                    ptm.setString(1, orderDetails.getOrderID());
                    ptm.setString(2, phone.getPhoneID());
                    ptm.executeUpdate();
                }
                conn.commit();
                isSuccess = true;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            conn.rollback();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return isSuccess;
    }
}
