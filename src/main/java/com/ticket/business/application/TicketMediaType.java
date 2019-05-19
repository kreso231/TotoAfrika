package com.ticket.business.application;

import javax.ws.rs.core.MediaType;

public class TicketMediaType extends MediaType {

    public static class API {

        public final static String V1 = "application/vnd.tickets-v1+json";
        public final static String EXPAND = "expand";

    }

}
