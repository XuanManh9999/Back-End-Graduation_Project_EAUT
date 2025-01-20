package com.back_end_TN.project_tn.entitys;

import com.back_end_TN.project_tn.enums.Active;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "user_role")
public class UserRoleEntity extends BaseEntity<Long>{

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userId;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity roleId;


}
