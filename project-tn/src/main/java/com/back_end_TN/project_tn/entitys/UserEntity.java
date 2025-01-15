package com.back_end_TN.project_tn.entitys;

import com.back_end_TN.project_tn.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity  extends BaseEntity{

     @Column(name = "username")
     private String username;
     @Column(name = "password")
     @Min(value = 6, message = "Mật khẩu phải có tối thiểu 6 kí tự")
     @Max(value = 12, message = "Mật khẩu có thể chứa tối đa 12 kí tự")
     private String password;

     @Column(name = "email", unique = true, nullable = false)
     @Email
     private String email;

     private Gender gender;
     @Temporal(TemporalType.DATE)
     private Date birthday;
     private Long point;
     @Column(name = "avatar", columnDefinition = "TEXT")
     private String avatar;
     @Column(nullable = false, unique = true)
     @Min(value = 10)
     @Max(value = 10)
     private String phoneNumber;


     @OneToMany(mappedBy = "userId", orphanRemoval = true)
     private List<UserRoleEntity> userRoles;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AuthProvider> authProviders;
}
