package com.hrms.hrms_backend;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordTest {

    public static void main(String[] args) {

        BCryptPasswordEncoder encoder =
                new BCryptPasswordEncoder();

        String pass = encoder.encode("123");

        System.out.println(pass);
    }
}