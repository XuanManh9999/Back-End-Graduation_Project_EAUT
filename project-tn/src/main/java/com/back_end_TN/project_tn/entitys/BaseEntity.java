package com.back_end_TN.project_tn.entitys;

import com.back_end_TN.project_tn.enums.Active;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity <T> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private T id;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "active")
    private Active active = Active.CHUA_HOAT_DONG;  // Giá trị mặc định là 0
    // Them mot cai ID
    @CreatedBy // tu dong them user_id vao record
    @Column(name = "create_by")
    private T createBy;

    @LastModifiedDate //
    @Column(name = "updated_by")
    private T updatedBy;

    @Column(name = "create_at")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @Column(name = "update_at")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;
}
