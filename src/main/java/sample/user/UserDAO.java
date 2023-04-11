/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import sample.utils.DBUtils;

/**
 *
 * @author Lenovo
 */
public class UserDAO {

    private static final String LOGIN = "SELECT fullName, imageURL, roleID, email FROM Users WHERE userID = ? AND password = ?";
    private static final String SEARCH = "WITH Rows AS \n"
            + "(\n"
            + "	SELECT ROW_NUMBER() OVER (ORDER BY userID) [Row]\n"
            + "	, userID, fullName, imageURL, email, roleID\n"
            + "	FROM\n"
            + "	Users WHERE fullName LIKE ?\n"
            + ")\n"
            + "SELECT TOP 10 *\n"
            + "FROM Rows\n"
            + "WHERE Row >= ? AND Row <= ?";
    private static final String DELETE = "DELETE FROM Users WHERE userID=?";
    private static final String UPDATE = "UPDATE Users SET fullName=?, roleID=? WHERE userID=?";
    private static final String CHANGE_PASSWORD = "UPDATE Users SET password = ? WHERE userID=?";
    private static final String CHANGE_AVATAR = "UPDATE Users SET imageURL = ? WHERE userID=?";
    private static final String CHECK_DUPLICATE = "SELECT roleID FROM Users WHERE userID = ?";
    private static final String CREATE = "INSERT INTO Users(userID, fullName, imageURL, email, password, roleID) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SEARCH_BY_EMAIL = "SELECT userID, fullName, imageURL, roleID FROM Users WHERE email = ?";
    private static final String SHOW_TWO = "SELECT TOP 2 userID, fullName, imageURL, email, roleID from Users ORDER BY userID";

    public UserDTO checkLogin(String userID, String password) throws SQLException {
        UserDTO loginUser = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(LOGIN);
                ptm.setString(1, userID);
                ptm.setString(2, password);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String imageURL = rs.getString("imageURL");
                    String roleID = rs.getString("roleID");
                    String email = rs.getString("email");
                    loginUser = new UserDTO(userID, fullName, imageURL, email, password, roleID);
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
        return loginUser;
    }

    public List<UserDTO> getListUser(String search, int curPage) throws SQLException {
        int startPage = (curPage - 1) * 5 + 1;
        int endPage = curPage * 5;
        List<UserDTO> listUser = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH);
                ptm.setString(1, "%" + search + "%");
                ptm.setInt(2, startPage);
                ptm.setInt(3, endPage);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String fullName = rs.getString("fullName");
                    String imageURL = rs.getString("imageURL");
                    String email = rs.getString("email");
                    String roleID = rs.getString("roleID");
                    String password = "***";
                    listUser.add(new UserDTO(userID, fullName, imageURL, email, password, roleID));
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
        return listUser;
    }
    
    public List<UserDTO> getTopTwo() throws SQLException {
        List<UserDTO> listUser = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SHOW_TWO);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String fullName = rs.getString("fullName");
                    String imageURL = rs.getString("imageURL");
                    String email = rs.getString("email");
                    String roleID = rs.getString("roleID");
                    String password = "***";
                    listUser.add(new UserDTO(userID, fullName, imageURL, email, password, roleID));
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
        return listUser;
    }

    public UserDTO getUserByEmail(String email) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_BY_EMAIL);
                ptm.setString(1, email);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String fullName = rs.getString("fullName");
                    String imageURL = rs.getString("imageURL");
                    String roleID = rs.getString("roleID");
                    String password = "***";
                    user = new UserDTO(userID, fullName, imageURL, email, password, roleID);
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
        return user;
    }

    public boolean delete(String userID) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE);
                ptm.setString(1, userID);
                int rowEffect = ptm.executeUpdate();
                if (rowEffect > 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }

    public boolean update(UserDTO newUser) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE);
                ptm.setString(1, newUser.getFullName());
                ptm.setString(2, newUser.getRoleID());
                ptm.setString(3, newUser.getUserID());

                int rowEffect = ptm.executeUpdate();
                if (rowEffect > 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }
    
     public boolean changePassword(UserDTO newUser) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHANGE_PASSWORD);
                ptm.setString(1, newUser.getPassword());
                ptm.setString(2, newUser.getUserID());

                int rowEffect = ptm.executeUpdate();
                if (rowEffect > 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }

    public boolean changeAvatar(UserDTO newUser) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHANGE_AVATAR);
                ptm.setString(1, newUser.getImageURL());
                ptm.setString(2, newUser.getUserID());

                int rowEffect = ptm.executeUpdate();
                if (rowEffect > 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }

    public boolean checkDuplicate(String userID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE);
                ptm.setString(1, userID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
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
        return check;
    }

    public boolean create2(UserDTO user) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE);
                String imageURL = "avatar/default.png";
                if (!user.getImageURL().isEmpty()) {
                    imageURL = user.getImageURL();
                }

                ptm.setString(1, user.getUserID());
                ptm.setString(2, user.getFullName());
                ptm.setString(3, imageURL);
                ptm.setString(4, user.getEmail());
                ptm.setString(5, user.getPassword());
                ptm.setString(6, user.getRoleID());
                int rs = ptm.executeUpdate();
                if (rs > 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }

    public boolean create(UserDTO user) throws SQLException, NamingException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE);
                ptm.setString(1, user.getUserID());
                ptm.setString(2, user.getFullName());
                ptm.setString(3, user.getRoleID());
                ptm.setString(4, user.getPassword());
                int rs = ptm.executeUpdate();
                if (rs > 0) {
                    result = true;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }

}
