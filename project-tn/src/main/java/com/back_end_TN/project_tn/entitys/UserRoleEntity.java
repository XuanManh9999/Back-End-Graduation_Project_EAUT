package com.back_end_TN.project_tn.entitys;

import com.back_end_TN.project_tn.enums.Active;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@IdClass(UserRoleId.class)  // Chỉ định lớp khóa chính composite
public class UserRoleEntity {

    @Id
    @ManyToOne
    private UserEntity userId;

    @Id
    @ManyToOne
    private RoleEntity roleId;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "active")
    private Active active = Active.CHUA_HOAT_DONG;  // Giá trị mặc định là 0
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

}
