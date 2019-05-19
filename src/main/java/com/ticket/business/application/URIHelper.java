package com.ticket.business.application;

import javax.ejb.Stateless;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Stateless
public class URIHelper {

    private @Context UriInfo uriInfo;

    public URI createURIForResource(Object id) {
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path((String) id);
        URI uri = builder.build();
        return uri;
    }


}
