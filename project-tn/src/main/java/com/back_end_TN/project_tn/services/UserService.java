package com.back_end_TN.project_tn.services;

import com.back_end_TN.project_tn.dtos.request.UserRequest;
import com.back_end_TN.project_tn.dtos.response.CommonResponse;
import com.back_end_TN.project_tn.entitys.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDetailsService userDetailsService();
    Optional<UserEntity> findUserByUsername(String username);
    ResponseEntity<CommonResponse> getAllUsers(Integer limit, Integer offset);
    ResponseEntity<CommonResponse> getUserById(Long userId);
    ResponseEntity<CommonResponse> addUser(UserRequest userRequest);
    ResponseEntity<CommonResponse> updateUser(UserRequest userRequest);
    ResponseEntity<CommonResponse> deleteUser(Long userId);
}
