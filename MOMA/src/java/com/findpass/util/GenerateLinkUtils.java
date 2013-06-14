/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.findpass.util;
import com.entity.moma.User;
import java.security.MessageDigest;  
import java.security.NoSuchAlgorithmException;  
  
import javax.servlet.ServletRequest;  
/**
 *
 * @author ASUS
 */
public class GenerateLinkUtils {
    
 
    //generate the new password link
    public static String generateResetPwdLink(User user) {  
        return "http://localhost:8080/MOMA/resetPasswordUI?userName="   
                + user.getUserName() ;
    }  
}
