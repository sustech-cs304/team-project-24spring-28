package org.example.backend.service;


import org.example.backend.domain.AbstractUser;

import java.util.List;

public interface AbstractUserService {
    AbstractUser checkUser(String username, String password);


    boolean deleteUserById(long id);

    List<AbstractUser> findAllUser();

    AbstractUser findUserById(long id);
}
