package com.drato.graduationthesis.service.implementation;

import com.drato.graduationthesis.model.Role;
import com.drato.graduationthesis.repository.RoleRepository;
import com.drato.graduationthesis.service.interfaces.RoleService;
import com.drato.graduationthesis.service.interfaces.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository repository;

    @Autowired
    SecurityService securityService;

    @Override
    public List<Role> getAllRole() {
        return repository.findAll();
    }

    @Override
    public Role getRoleByName(String roleName) {
        Role role;
        if (roleNameExisted(roleName)) {
            role = repository.findByName(roleName);
        } else {
            Date now = Calendar.getInstance().getTime();
            role = new Role(roleName, "", securityService.getCurrentUsername(), securityService.getCurrentUsername(), now, now);
        }
        return role;
    }

    @Override
    public boolean roleNameExisted(String roleName) {
        return repository.existsByName(roleName);
    }
}
