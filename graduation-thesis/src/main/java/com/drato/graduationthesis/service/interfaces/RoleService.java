package com.drato.graduationthesis.service.interfaces;

import com.drato.graduationthesis.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRole();
    Role getRoleByName(String roleName);
    boolean roleNameExisted(String roleName);
}
