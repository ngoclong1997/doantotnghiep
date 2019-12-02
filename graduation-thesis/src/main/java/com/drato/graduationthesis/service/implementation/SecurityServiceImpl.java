package com.drato.graduationthesis.service.implementation;


import com.drato.graduationthesis.model.User;
import com.drato.graduationthesis.service.interfaces.RoleService;
import com.drato.graduationthesis.service.interfaces.SecurityService;
import com.drato.graduationthesis.service.interfaces.UserService;
import com.drato.graduationthesis.utils.PropertiesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userService.findByUsername(username);
    }

    @Override
    public String getCurrentUsername() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            return authentication.getName();
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    public boolean currentUserIsAdmin() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            return authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals(PropertiesUtils.getProperty(PropertiesUtils.ROLE_ADMIN, "")));
        } catch (Exception e ) {
            return false;
        }
    }
}
