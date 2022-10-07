package fiveman.hotelservice.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Encoder {
    public static void main(String[] args) {
        String rawPass = "123456";
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String newPass = bCryptPasswordEncoder.encode(rawPass);
        System.out.println(newPass);
    }
}
