package org.example.backend.dto;
import lombok.Getter;
import lombok.Setter;
import org.example.backend.domain.enums.UserType;

@Setter
@Getter
public class AbstractUserDto {
    Long id;
    String username;
    String name;
    String password;
    UserType userType;
    Boolean hasUnread;

    public AbstractUserDto() {
    }

}
