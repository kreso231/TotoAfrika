package com.ticket.business.ticket.entity;

import com.ticket.business.user.entity.Role;

import java.util.Arrays;
import java.util.List;

public enum GroupEnum {
    ADMINISTRATOR,
    USER,
    AGENT;

    private final List<Role> roles;

    GroupEnum(Role... roles) {
        this.roles = Arrays.asList(roles);
    }

    public List<Role> getRoles() {
        return roles;
    }


}