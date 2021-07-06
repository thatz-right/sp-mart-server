package com.spmart.server.auth.domain;

import com.spmart.server.common.BaseTimeEntity;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Getter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class User extends BaseTimeEntity {

    @Id
    private String id;
    private String email;
    private String phone;
    private String name;
    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public User(String id, String email, String phone, String name, String address) {
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.address = address;
        this.role = Role.USER;
    }
}
