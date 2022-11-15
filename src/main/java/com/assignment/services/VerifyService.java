package com.assignment.services;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class VerifyService {

    public String createToken() {
        String alpha = "abcdefghijklmnopqrstuvwxyz"; // a-z
        String alphaUpperCase = alpha.toUpperCase(); // A-Z
        String digits = "0123456789"; // 0-9
        String ALPHA_NUMERIC = alpha + alphaUpperCase + digits;
    
        Random generator = new Random();
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 40; i++) {
            int number =generator.nextInt((ALPHA_NUMERIC.length()));
            char ch = ALPHA_NUMERIC.charAt(number);
            sb.append(ch);
        }
        return System.currentTimeMillis() + sb.toString();
    }
    
    public String createVerifyCode() {
        String digits = "0123456789"; // 0-9
        Random generator = new Random();
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int number =generator.nextInt((digits.length()));
            char ch = digits.charAt(number);
            sb.append(ch);
        }
        return sb.toString();
    }

}
