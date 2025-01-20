package com.back_end_TN.project_tn.services;

import com.back_end_TN.project_tn.entitys.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    UserDetailsService userDetailsService();
    List<String> getAllUsers();
}
