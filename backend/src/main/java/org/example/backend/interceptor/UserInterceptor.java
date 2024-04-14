package org.example.backend.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.backend.domain.AbstractUser;
import org.example.backend.domain.enums.UserType;
import org.springframework.web.servlet.HandlerInterceptor;
import org.example.backend.util.JwtUtil;
import org.example.backend.config.MyException;


public class UserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        AbstractUser abstractUser = JwtUtil.verifyToken(token);
        assert abstractUser != null;
        if (abstractUser.getUserType() != UserType.User) {
            System.out.println(request.getRequestURL());
            System.out.println("no authority");
            throw new MyException(3, "no authority");
        }
        return true;
    }
}
