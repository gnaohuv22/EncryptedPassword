/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 *
 * @author hoang
 */
public class PasswordEncryption {
    //Generate a random salt value
    public static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
    
    //Convert a hexadecimal string to a byte array
    public static byte[] hexToBytes (String hex) {
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < bytes.length; ++i) {
            bytes[i] = (byte) Integer.parseInt(hex.substring(i * 2, i * 2 + 2), 16);
        }
        return bytes;
    }
    
    //Convert a byte array to a hexadecimal string
    public static String byteToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
    
    //Encrypt a password with SHA-256 and salt
    public static String encryptPassword (String password, byte[] salt) {
        try {
            //Create a message digest instance for SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            
            //Update the message digest with the salt
            md.update(salt);
            
            //hash the password
            byte[] hashedPassword = md.digest(password.getBytes());
            
            //Convert the hashed password and salt to hexadecimal strings
            String hashedPasswordHex = byteToHex(hashedPassword);
            String saltHex = byteToHex(salt);
            
            //Return the concatenated string of hashed password and salt
            return hashedPasswordHex + saltHex;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
