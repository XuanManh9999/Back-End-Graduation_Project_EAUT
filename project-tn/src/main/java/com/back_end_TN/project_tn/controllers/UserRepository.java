package com.back_end_TN.project_tn.controllers;

import com.back_end_TN.project_tn.dtos.request.UserRequest;
import com.back_end_TN.project_tn.dtos.response.CommonResponse;
import com.back_end_TN.project_tn.services.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserRepository {
    UserService userService;

    @GetMapping("")
    public ResponseEntity<CommonResponse> getAllUsers (
            @RequestParam(required = false, defaultValue = "10") Integer limit,
            @RequestParam(required = false, defaultValue = "0") Integer offset) {
        if (limit < 0 || offset < 0 || limit > 100) {
            limit = 10;
            offset = 0;
        }
        return userService.getAllUsers(limit, offset);
    }

    @GetMapping("/by-id/{userId}")
    public ResponseEntity<CommonResponse> getUserById(@PathVariable Long userId) {
        if (userId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return userService.getUserById(userId);
    }


    @PostMapping("")
    public ResponseEntity<CommonResponse> createUser(@RequestBody UserRequest userRequest) {
        return null;
    }


    @PutMapping("")
    public ResponseEntity<CommonResponse> updateUser(@RequestBody UserRequest userRequest) {
        return null;
    }

    @DeleteMapping("/by-id/{userId}")
    public ResponseEntity<CommonResponse> deleteUserById(@PathVariable Long userId) {
        return null;
    }



}
