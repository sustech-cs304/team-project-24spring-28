package org.example.backend.api;

import org.example.backend.domain.AbstractUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbstractUserRepository extends JpaRepository<AbstractUser, Long> {
    AbstractUser findByUsernameAndPassword(String username, String password);

    AbstractUser findById(long id);
}
