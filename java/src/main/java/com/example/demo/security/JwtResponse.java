package com.example.demo.security;

import lombok.*;

import java.util.List;

@EqualsAndHashCode
@Data
public class JwtResponse {

    private String token;

    private Integer id;

    private static final String TYPE = "Bearer";

    private String username;

    private List roles;

    public JwtResponse(String token, Integer id, String username, List roles){
        this.token = token;
        this.id = id;
        this.username = username;
        this.roles = roles;
    }
}
