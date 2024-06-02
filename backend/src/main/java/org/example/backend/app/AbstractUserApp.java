package org.example.backend.app;

import org.example.backend.config.MyException;
import org.example.backend.domain.AbstractUser;
import org.example.backend.domain.Admin;
import org.example.backend.domain.User;
import org.example.backend.dto.AbstractUserDto;
import org.example.backend.service.AbstractUserService;
import org.example.backend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Base64;
import java.util.List;


@RestController
public class AbstractUserApp {
    private final AbstractUserService abstractUserService;
    private final ResourceUrlProvider mvcResourceUrlProvider;

    @Autowired
    public AbstractUserApp(AbstractUserService abstractUserService, ResourceUrlProvider mvcResourceUrlProvider) {
        this.abstractUserService = abstractUserService;
        this.mvcResourceUrlProvider = mvcResourceUrlProvider;
    }

    @PostMapping(value = "/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        AbstractUser user = abstractUserService.checkUser(username, password);
        if (user == null) {
            throw new MyException(4, "incorrect username or password");
        }
        System.out.println(user.getId());
        return JwtUtil.getToken(String.valueOf(user.getId()), password);
    }

    @PostMapping("/signUp")
    public boolean signUp(@RequestParam String username, @RequestParam String password) {
        AbstractUser user = new User();
        if (abstractUserService.findUserByUsername(username) != null) {
            throw new MyException(5, "username already exists");
        }
        user.setUsername(username);
        user.setPassword(password);
        user.setName(username);
        user.setAvatar("default_avatar.jpg");
        try {
            abstractUserService.saveUser(user);
            return true;
        } catch (Exception e) {
            throw new MyException(6, "sign up failed");
        }
    }

    @GetMapping("/userInfo")
    public AbstractUserDto getUserByToken(@RequestHeader("Authorization") String token) {
        AbstractUser user = JwtUtil.verifyToken(token);
        if(user == null) {
            throw new MyException(7, "token invalid");
        }
        return new AbstractUserDto(user);
    }
}
