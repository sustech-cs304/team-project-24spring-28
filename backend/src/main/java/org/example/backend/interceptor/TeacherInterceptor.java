package org.example.backend.interceptor;

import com.example.dormitoryselection.config.MyException;
import com.example.dormitoryselection.dto.UserInfoDto;
import com.example.dormitoryselection.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class TeacherInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        UserInfoDto userInfoDto = JwtUtil.verifyToken(token);
        if (userInfoDto.getType() != 1) {
            System.out.println(request.getRequestURL());
            System.out.println("no authority");
            throw new MyException(3, "no authority");
        }
        return true;
    }
}
