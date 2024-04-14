package org.example.backend.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    private static final Long EXPIRATION = 604800000L;//7days(ms)
    private static UserService userService;
    private static TeacherService teacherService;
    private static StudentService studentService;


    @Autowired
    public void setUserService(UserService userService) {
        JwtUtil.userService = userService;
    }

    @Autowired
    public void setTeacherService(TeacherService teacherService) {
        JwtUtil.teacherService = teacherService;
    }

    @Autowired
    public void setStudentService(StudentService studentService) {
        JwtUtil.studentService = studentService;
    }

    public static String getToken(String userId, String password) {
        Date expireDate = new Date(System.currentTimeMillis() + EXPIRATION);
        Map<String, Object> header = new HashMap<>();
        header.put("alg", "HS256");
        header.put("typ", "JWT");
        return JWT.create().withHeader(header).withClaim("userId", userId).withExpiresAt(expireDate).sign(Algorithm.HMAC256(password));
    }

    public static UserInfoDto verifyToken(String token) {
        DecodedJWT jwt;
        User user;
        try {
            long userId = Integer.parseInt(JWT.decode(token).getClaim("userId").asString());
            user = userService.findUserById(userId);
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
            jwt = verifier.verify(token);
            if (jwt.getExpiresAt().before(new Date())) {
                //token expired
                return null;
            }
        } catch (Exception e) {
            //token decoding error
            System.out.println(e.getMessage());
            return null;
        }
        //token verified successfully
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setId(user.getId());
        userInfoDto.setName(user.getName());
        userInfoDto.setUsername(user.getUsername());
        Student student = studentService.findStudentById(user.getId());
        if (student == null) {
            System.out.println("student is null");
            Teacher teacher = teacherService.findTeacherById(user.getId());
            userInfoDto.setType(1);
        } else {
            userInfoDto.setType(0);
        }
        return userInfoDto;
    }

    public static long getIdByToken(String token) {
        long userId = -1;
        try {
            userId = Integer.parseInt(JWT.decode(token).getClaim("userId").asString());
        } catch (Exception e) {
            //token decoding error
            System.out.println(e.getMessage());
        }
        return userId;
    }
}
