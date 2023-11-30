package com.learning.entity;

import com.learning.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(name = "seq_gen", sequenceName = "user_seq_gen", allocationSize = 1, initialValue = 1)
@Table(name = "users")
public class UserEntity extends BaseEntity {
    private String fullName;
    private String email;
    private String password;
}
