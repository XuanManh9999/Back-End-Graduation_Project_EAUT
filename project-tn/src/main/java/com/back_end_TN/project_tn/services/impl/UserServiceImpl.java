package com.back_end_TN.project_tn.services.impl;

import com.back_end_TN.project_tn.entitys.UserEntity;
import com.back_end_TN.project_tn.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public List<String> getAllUsers() {
        return List.of(
                "MANH", "PHUONG"
        );
    }
}
