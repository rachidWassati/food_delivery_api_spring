package com.rachidj.api_rest.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {
    public static String hashPassword(String password, String salt) {
        return BCrypt.hashpw(password, salt);
    }
}
