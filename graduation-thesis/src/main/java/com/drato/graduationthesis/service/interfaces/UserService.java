package com.drato.graduationthesis.service.interfaces;

import com.drato.graduationthesis.dto.UserDto;
import com.drato.graduationthesis.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User findByUsername(String username);
    User findById(Long id);
    boolean emailExisted(String email);
    boolean usernameExisted(String username);
    List<User> getAllUser();
    void createUser(UserDto registration);
    void editUser(User user);
    void deleteUser(User user);
    boolean emailUsedByOther(Long id, String email);
    boolean usernameUsedByOther(Long id, String username);
    void updatePassword(Long id, String password);
    boolean currentUserPasswordMatch(String oldPassword);
}
