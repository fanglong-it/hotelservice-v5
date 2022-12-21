package fiveman.hotelservice.xmlservice;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class test {
    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String raw = "receptionist";
        String newPass = bCryptPasswordEncoder.encode(raw);
        System.out.println(newPass);
    }
}
