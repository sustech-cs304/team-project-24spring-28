package org.example.backend.app;

import org.example.backend.config.MyException;
import org.example.backend.domain.AbstractUser;
import org.example.backend.service.AbstractUserService;
import org.example.backend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Base64;
import java.util.List;


@RestController
public class AbstractUserApp {
    private final AbstractUserService abstractUserService;

    @Autowired
    public AbstractUserApp(AbstractUserService abstractUserService) {
        this.abstractUserService = abstractUserService;
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
}
