package com.ticket.business.exception;

import javax.ejb.EJBException;

public abstract class BusinessException extends EJBException {

    BusinessException(String message) {
        super(message);
    }

}
