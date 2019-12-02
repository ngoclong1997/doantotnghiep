package com.drato.graduationthesis.service.implementation;

import com.drato.graduationthesis.dto.UserDto;
import com.drato.graduationthesis.model.Role;
import com.drato.graduationthesis.model.User;
import com.drato.graduationthesis.repository.UserRepository;
import com.drato.graduationthesis.service.interfaces.RoleService;
import com.drato.graduationthesis.service.interfaces.SecurityService;
import com.drato.graduationthesis.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    SecurityService securityService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Override
    public User findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    @Override
    public boolean emailExisted(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean usernameExisted(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public void createUser(UserDto registration){
        User user = new User();
        user.setUsername(registration.getUsername());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setEmail(registration.getEmail());
        user.setEnabled(true);
        user.setRoles(Collections.singletonList(roleService.getRoleByName(registration.getRole())));
        Date now = Calendar.getInstance().getTime();
        user.setCreatedDate(now);
        user.setModifiedDate(now);
        user.setAddBy(securityService.getCurrentUsername());
        user.setModifiedBy(securityService.getCurrentUsername());
        userRepository.save(user);
    }

    @Override
    public void editUser(User user) {
        Date now = Calendar.getInstance().getTime();
        user.setModifiedDate(now);
        user.setModifiedBy(securityService.getCurrentUsername());
        userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public boolean emailUsedByOther(Long id, String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return false;
        } else {
            return !user.getId().equals(id);
        }

    }

    @Override
    public boolean usernameUsedByOther(Long id, String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return false;
        } else {
            return !user.getId().equals(id);
        }
    }

    @Override
    public void updatePassword(Long id, String password) {
        User user = findById(id);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    @Override
    public boolean currentUserPasswordMatch(String oldPassword) {
        User currentUser = securityService.getCurrentUser();
        return (passwordEncoder.matches(oldPassword, currentUser.getPassword()));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        try {
            user = userRepository.findByUsername(username);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}

