package com.ticket.business.user.entity;

import javax.persistence.Entity;

public enum Role {

    DELETE_USER,
    OWN_TICKET,
    CREATE_OWN_TICKET,
    CREATE_FOREIGN_TICKET,
    ASSIGN_AGENT_TICKET,
    RESOLVE_TICKET,
    DELETE_TICKET;
    
    public enum Admin {
        DELETE_USER,
        UPDATE_USER,
        READ_USER
    }

    public static class Name {
        public final static String OWN_TICKET = "OWN_TICKET";
        public final static String CREATE_OWN_TICKET = "CREATE_OWN_TICKET";
        public final static String CREATE_FOREIGN_TICKET = "CREATE_OWN_TICKET";
        public final static String DELETE_TICKET = "CREATE_OWN_TICKET";
        public final static String RESOLVE_TICKET = "RESOLVE_TICKET";
        public final static String ASSIGN_AGENT_TICKET = "ASSIGN_AGENT_TICKET";
    }

}
