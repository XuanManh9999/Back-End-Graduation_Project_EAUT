package com.back_end_TN.project_tn.services.impl;

import com.back_end_TN.project_tn.configs.ModelMapperConfig;
import com.back_end_TN.project_tn.dtos.request.UserRequest;
import com.back_end_TN.project_tn.dtos.response.CommonResponse;
import com.back_end_TN.project_tn.dtos.response.RoleResponseDTO;
import com.back_end_TN.project_tn.dtos.response.UserResponseDTO;
import com.back_end_TN.project_tn.dtos.response.UserRoleDTO;
import com.back_end_TN.project_tn.entitys.RoleEntity;
import com.back_end_TN.project_tn.entitys.UserEntity;
import com.back_end_TN.project_tn.entitys.UserRoleEntity;
import com.back_end_TN.project_tn.enums.Active;
import com.back_end_TN.project_tn.repositorys.UserEntityRepository;
import com.back_end_TN.project_tn.repositorys.UserRoleRepository;
import com.back_end_TN.project_tn.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserEntityRepository userRepository;
    private final ModelMapperConfig modelMapperConfig;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public Optional<UserEntity> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public ResponseEntity<CommonResponse> getAllUsers(Integer limit, Integer offset) {
        try {
            Pageable pageable = PageRequest.of(offset, limit);
            Page<UserEntity> userPage = userRepository.findAll(pageable);
            List<UserEntity> users = userPage.getContent();

            List<UserResponseDTO> userResponses = users.stream().map(user -> {
                UserResponseDTO userResponse = modelMapperConfig.modelMapper().map(user, UserResponseDTO.class);
                userResponse.setCreatedAt(user.getCreateAt());
                userResponse.setUpdatedAt(user.getUpdateAt());

                List<RoleResponseDTO> roleResponseDTOS = new ArrayList<>();
                List<UserRoleEntity> userRoleEntities = user.getUserRoles();
                for (UserRoleEntity userRoleEntity : userRoleEntities) {
                    RoleEntity roleEntity = userRoleEntity.getRoleId();
                    RoleResponseDTO roleResponseDTO = new RoleResponseDTO();
                    roleResponseDTO.setId(roleEntity.getId());
                    roleResponseDTO.setName(roleEntity.getName());
                    roleResponseDTO.setDescRole(roleEntity.getDescRole());
                    roleResponseDTO.setCreatedAt(roleEntity.getCreateAt());
                    roleResponseDTO.setUpdatedAt(roleEntity.getUpdateAt());
                    roleResponseDTOS.add(roleResponseDTO);
                }
                userResponse.setRoles(roleResponseDTOS);
                return userResponse;
            }).collect(Collectors.toList());

            CommonResponse response = CommonResponse.builder()
                    .status(HttpStatus.OK.value())
                    .totalPage(userPage.getTotalPages())
                    .data(userResponses)
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw e;
        }
    }


    @Override
    public ResponseEntity<CommonResponse> getUserById(Long userId) {
        try {
            Optional<UserEntity> user = userRepository.findById(userId);
            if (user.isPresent()) {
                UserResponseDTO userResponse = modelMapperConfig.modelMapper().map(user.get(), UserResponseDTO.class);
                userResponse.setCreatedAt(user.get().getCreateAt());
                userResponse.setUpdatedAt(user.get().getUpdateAt());
                List<UserRoleEntity> userRoleEntitys = user.get().getUserRoles();
                List<RoleResponseDTO> RoleResponseDTOs = new ArrayList<>();
                for (UserRoleEntity userRoleEntity : userRoleEntitys) {
                    RoleEntity roleEntity = userRoleEntity.getRoleId();
                    RoleResponseDTO roleResponseDTO = new RoleResponseDTO();
                    roleResponseDTO.setId(roleEntity.getId());
                    roleResponseDTO.setName(roleEntity.getName());
                    roleResponseDTO.setDescRole(roleEntity.getDescRole());
                    roleResponseDTO.setCreatedAt(roleEntity.getCreateAt());
                    roleResponseDTO.setUpdatedAt(roleEntity.getUpdateAt());
                    RoleResponseDTOs.add(roleResponseDTO);
                }

                userResponse.setRoles(RoleResponseDTOs);

                return ResponseEntity.ok().body(
                        CommonResponse.builder()
                                .status(HttpStatus.OK.value())
                                .data(userResponse)
                                .build()
                );

            }else {
                throw new UsernameNotFoundException("User not found");
            }
        }catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ResponseEntity<CommonResponse> addUser(UserRequest userRequest) {
        try {
            Optional<UserEntity> user = userRepository.findByUsername(userRequest.getUser_name());
            Optional<UserEntity> user2 = userRepository.findByEmail(userRequest.getEmail());
            if (user.isPresent() || user2.isPresent()) {
                throw new UsernameNotFoundException("Tài khoản đã tồn tại trong hệ thống");
            }
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(userRequest.getUser_name());
            userEntity.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            userEntity.setEmail(userRequest.getEmail());
            userEntity.setActive(Active.HOAT_DONG);
            userEntity.setBirthday(userRequest.getBirthday());
            userEntity.setGender(userRequest.getGender());
            userEntity.setAvatar(userRequest.getAvatar());

            List<UserRoleEntity> userRoleEntities = new ArrayList<>();
            List<UserRoleDTO> userRoleDTOS = userRequest.getRoles();

//            for (UserRoleDTO userRoleDTO : userRoleDTOS) {
//               UserRoleEntity userRoleEntity = new UserRoleEntity();
//
//
//            }


//            userEntity.setUserRoles();
            userRepository.save(userEntity);


        }catch (Exception e) {
            throw e;
        }

    }

    @Override
    public ResponseEntity<CommonResponse> updateUser(UserRequest userRequest) {
        return null;
    }

    @Override
    public ResponseEntity<CommonResponse> deleteUser(Long userId) {
        try {
            Optional<UserEntity> user = userRepository.findById(userId);
            if (user.isPresent()) {
                UserEntity userEntity = user.get();
                userEntity.setActive(Active.VO_HIEU_HOA);
                userRepository.save(userEntity);
                return ResponseEntity.ok().body(
                        CommonResponse
                                .builder()
                                .status(HttpStatus.OK.value())
                                .data(userEntity)
                                .build()
                );
            }else {
                throw new UsernameNotFoundException("User not found");
            }

        }catch (Exception e) {
            throw e;
        }
    }
}
