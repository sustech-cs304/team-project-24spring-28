package org.example.backend.service;

import org.example.backend.api.AbstractUserRepository;
import org.example.backend.api.UserRepository;
import org.example.backend.domain.AbstractUser;
import org.example.backend.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbstractUserServiceImpl implements AbstractUserService {
    @Autowired
    private AbstractUserRepository abstractUserRepository;
    @Autowired
    private UserRepository userRepository;
//    @Autowired
//    JedisService jedisService;

    @Override
    public AbstractUser checkUser(String username, String password) {
        return abstractUserRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public boolean saveUser(AbstractUser abstractUser) {
        abstractUserRepository.save(abstractUser);
        return true;
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

    @Override
    public AbstractUser findUserByUsername(String username) {
        return abstractUserRepository.findByUsername(username);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
