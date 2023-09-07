/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import controller.PasswordEncryption;
import java.sql.Date;
import java.util.ArrayList;
import model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author hoang
 */
public class UserDAO extends DBContext {

    public ArrayList<User> getAllUsers() {
        ArrayList<User> list = new ArrayList<>();
        String SQL = "SELECT * FROM [User]";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                String firstName = rs.getString(1);
                String lastName = rs.getString(2);
                String email = rs.getString(3);
                String password = rs.getString(4);
                String dob = String.valueOf(rs.getDate(5));
                String gender = rs.getString(6);
                String profilePicture = rs.getString(7);

                User u = new User(firstName, lastName, email, password, dob, gender, profilePicture);
                list.add(u);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getAllUsers" + e.getMessage());
        }
        return null;
    }

    public boolean login(String email, String password) {
        String SQL = "SELECT * FROM [User] WHERE email = ?";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            byte[] salt = PasswordEncryption.generateSalt();
            String encryptedPassword = PasswordEncryption.encryptPassword(password, salt).substring(0, 64);
            pstm.setString(1, email);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                String storedPassword = rs.getString(4).substring(0, 64);
                return encryptedPassword.equals(storedPassword);
            }
        } catch (Exception e) {
            System.out.println("login" + e.getMessage());
        }
        return false;
    }

    public boolean register(User u) {
        String SQL = "INSERT INTO [User] VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1, u.getFirstName());
            pstm.setString(2, u.getLastName());
            pstm.setString(3, u.getEmail());
            byte[] salt = PasswordEncryption.generateSalt();
            String encryptedPassword = PasswordEncryption.encryptPassword(u.getPassword(), salt);
            pstm.setString(4, encryptedPassword);
            pstm.setDate(5, Date.valueOf(u.getDob()));
            pstm.setString(6, u.getGender());
            pstm.setString(7, u.getProfilePicture());
            
            pstm.execute();
            return true;
            
        } catch (Exception e) {
            System.out.println("register" + e.getMessage());
        }
        return false;
    }
}
