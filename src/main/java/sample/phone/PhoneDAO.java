/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.phone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sample.utils.DBUtils;

/**
 *
 * @author Lenovo
 */
public class PhoneDAO {

    private static final String SEARCH_BY_NAME = "SELECT phoneID, phoneName, imageURL, seriesID, price, quantity, phoneCPU, screenSize FROM Phones WHERE phoneName LIKE ?";
    private static final String SEARCH_BY_ID = "SELECT phoneID, phoneName, imageURL, seriesID, price, quantity, phoneCPU, screenSize FROM Phones WHERE phoneID = ?";
    private static final String SEARCH_BY_SERIES = "SELECT phoneID, phoneName, imageURL, seriesID, price, quantity, phoneCPU, screenSize FROM Phones WHERE seriesID = ?";
    private static final String SEARCH_SERIES = "SELECT seriesID, seriesName, imageURL, minPrice FROM PhoneSeries WHERE seriesID LIKE ?";
    private static final String CHECKOUT = "UPDATE Phones SET quantity = quantity - ?  WHERE phoneID = ?";

    public List<PhoneDTO> getListPhone(String search) throws SQLException {
        List<PhoneDTO> listPhone = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_BY_NAME);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String phoneID = rs.getString("phoneID");
                    String phoneName = rs.getString("phoneName");
                    String imageURL = rs.getString("imageURL");
                    int price = rs.getInt("price");
                    int quantity = rs.getInt("quantity");
                    String phoneCPU = rs.getString("phoneCPU");
                    String screenSize = rs.getString("screenSize");
                    listPhone.add(new PhoneDTO(phoneID, phoneName, imageURL, screenSize, price, quantity, phoneCPU, screenSize));
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

    public List<PhoneDTO> getListPhoneBySeries(String series) throws SQLException {
        List<PhoneDTO> listPhone = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_BY_SERIES);
                ptm.setString(1, series);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String phoneID = rs.getString("phoneID");
                    String phoneName = rs.getString("phoneName");
                    String imageURL = rs.getString("imageURL");
                    int price = rs.getInt("price");
                    int quantity = rs.getInt("quantity");
                    String phoneCPU = rs.getString("phoneCPU");
                    String screenSize = rs.getString("screenSize");
                    listPhone.add(new PhoneDTO(phoneID, phoneName, imageURL, series, price, quantity, phoneCPU, screenSize));
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

    public List<PhoneSeriesDTO> getListSeries(String series) throws SQLException {
        List<PhoneSeriesDTO> listPhoneSeries = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_SERIES);
                ptm.setString(1, "%" + series + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String seriesID = rs.getString("seriesID");
                    String seriesName = rs.getString("seriesName");
                    String imageURL = rs.getString("imageURL");
                    int minPrice = rs.getInt("minPrice");
                    listPhoneSeries.add(new PhoneSeriesDTO(seriesID, seriesName, imageURL, minPrice));
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
        return listPhoneSeries;
    }

    public PhoneDTO getPhone(String id) throws SQLException {
        PhoneDTO phone = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_BY_ID);
                ptm.setString(1, id);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String phoneID = rs.getString("phoneID");
                    String phoneName = rs.getString("phoneName");
                    String imageURL = rs.getString("imageURL");
                    int price = rs.getInt("price");
                    int quantity = rs.getInt("quantity");
                    String phoneCPU = rs.getString("phoneCPU");
                    String screenSize = rs.getString("screenSize");
                    phone = new PhoneDTO(phoneID, phoneName, imageURL, screenSize, price, quantity, phoneCPU, screenSize);
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
        return phone;
    }

    public boolean checkout(List<PhoneDTO> phones) throws SQLException {
        boolean isSuccess = true;
        PhoneDTO phone = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            conn.setAutoCommit(false);
            if (conn != null) {
                for (PhoneDTO phone1 : phones) {
                    ptm = conn.prepareStatement(CHECKOUT);
                    ptm.setInt(1, phone1.getQuantity());
                    ptm.setString(2, phone1.getPhoneID());
                    int rs = ptm.executeUpdate();
                }
                conn.commit();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            isSuccess = false;
            if(conn != null) conn.rollback();
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
