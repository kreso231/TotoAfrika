package com.ticket.business.exception;

import com.ticket.business.user.entity.Role;

public class AccessDeniedException extends BusinessException {

    public AccessDeniedException(Role role) {
        super("User is not in role: " + role);
    }

}
