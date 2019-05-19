package com.ticket.business.ticket.boundary;

import com.ticket.business.application.Application;
import com.ticket.business.application.URIHelper;
import com.ticket.business.ticket.entity.DTO.TicketDTO;
import com.ticket.business.ticket.entity.Ticket;
import org.apache.commons.collections4.CollectionUtils;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;

import com.ticket.business.application.TicketMediaType.API;
import com.ticket.business.application.URIHelper;
import com.ticket.business.ticket.entity.Ticket;
import com.ticket.business.ticket.entity.DTO.TicketDTO;
import org.apache.commons.collections4.CollectionUtils;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Stateless
@Path("/tickets")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TicketResourceV1 {

    private @EJB TicketService ticketService;
    private @EJB URIHelper uriHelper;

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") final Integer ticketId, @QueryParam("expand") final List<String> expandedFields) {
       TicketDTO ticketDTO = ticketService.read(ticketId, expandedFields);

        return Response.ok().entity(ticketDTO).build();
    }

    @GET
    @Path("/")
    public Response getAll(@QueryParam("offset") final Integer offset, @QueryParam("limit") final Integer limit) {
        List<Ticket> tickets = ticketService.readPage(offset, limit);

        if (CollectionUtils.isNotEmpty(tickets)) {
            return Response.noContent().build();
        }

        return Response.ok().entity(tickets).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") final Integer id) {
        ticketService.delete(id);

        return Response.ok().build();
    }

    @POST
    @Path("/")
    public Response post(TicketDTO ticketDTO) {
        ticketService.create(ticketDTO);

        URI ticketURI = uriHelper.createURIForResource(ticketDTO.getId());

        return Response.created(ticketURI).build();
    }

    @PUT
    @Path("/")
    public Response put(TicketDTO ticketDTO) {
        ticketService.update(ticketDTO);

        return Response.ok().build();
    }

}
