package com.generalTemplate.adapter.config.Keycloak.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserInfoKeycloak {
    private String name;
    private List<String> roles;
}
