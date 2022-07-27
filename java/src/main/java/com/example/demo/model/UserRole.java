package com.example.demo.model;

import com.example.demo.enums.RoleEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "roles")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RoleEnum name;

    public UserRole() {
        this(RoleEnum.ROLE_USER);
    }

    public UserRole(RoleEnum name) {
        this.name = name;
    }
}
