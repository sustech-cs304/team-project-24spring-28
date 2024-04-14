package org.example.backend.service;

import org.example.backend.api.AbstractUserRepository;
import org.example.backend.domain.AbstractUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbstractUserServiceImpl implements AbstractUserService {
    @Autowired
    private AbstractUserRepository abstractUserRepository;
//    @Autowired
//    JedisService jedisService;

    @Override
    public AbstractUser checkUser(String username, String password) {
        return abstractUserRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public boolean deleteUserById(long id) {
        return true;
    }

    @Override
    public List<AbstractUser> findAllUser() {
        return abstractUserRepository.findAll();
    }

    @Override
    public AbstractUser findUserById(long id) {
        return abstractUserRepository.findById(id);
    }
}
