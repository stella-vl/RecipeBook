package bg.smg.services;

import bg.smg.model.User;
import bg.smg.util.DBManager;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserService implements UserServiceI {
    private DataSource dataSource;
    private Connection connection;

    public UserService() throws SQLException {
        dataSource = DBManager.getInstance().getDataSource();
    }
    
    @Override
    public void registerUser(User user) throws SQLException {
        try {
            this.connection = dataSource.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO `users`(`username`, `password`) VALUES ('"+user.getUsername()+"', '"+user.getPassword()+"')")) {
                statement.executeQuery();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (connection != null) {
                System.out.println("Closing database connection...");
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Connection valid: " );
            }
        }
    }
    
    @Override
    public void updateUser(User user) throws SQLException{
    try {
            this.connection = dataSource.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(
                    "UPDATE `users` SET `password`='"+user.getPassword()+"' WHERE `username`='"+user.getUsername()+"'")) {
                statement.executeQuery();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (connection != null) {
                System.out.println("Closing database connection...");
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Connection valid: " );
            }
        }
    }
    
    @Override
    public User getUser(int id) {
        return null;
    }

    @Override
    public User getUserByUsername(String username) throws SQLException {
        try {
            this.connection = dataSource.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM users WHERE username=?")) {
                statement.setString(1, username);
                ResultSet resultSet = statement.executeQuery();
                resultSet.first();
                User user = new User();
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setTimestamp(resultSet.getTimestamp("created"));
                user.setActive(resultSet.getBoolean("is_active"));
                return user;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (connection != null) {
                System.out.println("Closing database connection...");
                connection.close();
                System.out.println("Connection valid: " + connection.isValid(5));
            }
        }
        return null;
    }

    @Override
    public boolean verifyUser(User user) throws SQLException {
        User registeredUser = getUserByUsername(user.getUsername());
        String encodedPwd = encodePassword(user.getPassword());
        if(registeredUser != null && registeredUser.getPassword().equals(encodedPwd))
            return true;
        else
            return false;
    }
    
    public String encodePassword(String password){
        String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes());
        return encodedPassword;
    }

    public String decodePassword(String passwordToDecode){
        byte[] decodedBytes = Base64.getDecoder().decode(passwordToDecode);
        return new String(decodedBytes);
    }
}
