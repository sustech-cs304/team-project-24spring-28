package org.example.backend.interceptor;

import com.example.dormitoryselection.config.MyException;
import com.example.dormitoryselection.dto.UserInfoDto;
import com.example.dormitoryselection.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class UserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (token == null) {
            System.out.println(request.getRequestURL());
            System.out.println("token is null");
            throw new MyException(1, "token is null");
        }
        UserInfoDto userInfoDto = JwtUtil.verifyToken(token);
        if (userInfoDto == null) {
            System.out.println(request.getRequestURL());
            System.out.println("token is invalid");
            throw new MyException(2, "token is invalid");
        }
        return true;
    }
}
