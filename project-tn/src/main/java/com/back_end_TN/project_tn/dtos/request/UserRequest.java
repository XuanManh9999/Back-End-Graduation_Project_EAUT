package com.back_end_TN.project_tn.dtos.request;

import com.back_end_TN.project_tn.dtos.response.RoleResponseDTO;
import com.back_end_TN.project_tn.dtos.response.UserRoleDTO;
import com.back_end_TN.project_tn.enums.Gender;
import com.back_end_TN.project_tn.enums.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRequest {
    private String user_name;
    private Long point;
    private String phone_number;
    private String password;
    private Gender gender;
    private String email;
    private Date birthday;
    private String avatar;
    private List<UserRoleDTO> roles;
}
