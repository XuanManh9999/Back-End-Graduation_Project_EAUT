package com.back_end_TN.project_tn.services.impl;

import com.back_end_TN.project_tn.repositorys.UserEntityRepository;
import com.back_end_TN.project_tn.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserEntityRepository userRepository;
    @Override
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public List<String> getAllUsers() {
        return List.of(
                "MANH", "PHUONG"
        );
    }
}
