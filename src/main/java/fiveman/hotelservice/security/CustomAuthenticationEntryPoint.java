package fiveman.hotelservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import fiveman.hotelservice.response.CustomResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {


    @Autowired
    ObjectMapper objectMapper;
    //If not authentication will lead to here
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse res, AuthenticationException authException) throws IOException, ServletException {
        PrintWriter out = res.getWriter();
        res.setContentType("application/json;charset=UTF-8");
        res.setStatus(403);
        String msg = objectMapper.writeValueAsString(new CustomResponseObject("403","Access is denied"));
        out.print(msg);
        out.flush();
    }
}
