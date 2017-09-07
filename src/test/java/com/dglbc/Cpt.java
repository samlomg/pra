package com.dglbc;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by LbcLT on 2016/12/28.
 */
public class Cpt {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder(4);
        String result=passwordEncoder.encode("123456");
        System.out.println(result);
    }
}
