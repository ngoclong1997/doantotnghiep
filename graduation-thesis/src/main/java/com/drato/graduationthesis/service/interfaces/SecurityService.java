package com.drato.graduationthesis.service.interfaces;


import com.drato.graduationthesis.model.User;

public interface SecurityService {
    public User getCurrentUser();
    public String getCurrentUsername();
    public boolean currentUserIsAdmin();
}
