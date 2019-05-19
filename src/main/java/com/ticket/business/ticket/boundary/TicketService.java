package com.ticket.business.ticket.boundary;

import com.ticket.business.EntityBuilder;
import com.ticket.business.Expandable;
import com.ticket.business.ticket.entity.Ticket;
import com.ticket.business.user.entity.Role;
import com.ticket.business.ticket.entity.DTO.TicketDTO;
import com.ticket.business.user.entity.User;
import com.ticket.business.user.boundary.UserService;
import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@PermitAll
@Stateless
public class TicketService {

    @PersistenceContext(unitName = "TicketModel")
    private EntityManager em;
//    private @EJB EJBContext ejbContext;

    private @Inject UserService userService;
    private @Inject EntityBuilder entityBuilder;

    public void create(final TicketDTO ticketDTO) {
        Ticket ticket;

        checkIfAssigned(ticketDTO);
        checkIfForSelf(ticketDTO);


    }

    private void getTicketFromDTOForCreate(TicketDTO ticketDTO) {
        Ticket ticket = new Ticket();

        //ticket.setCategory(ticketDTO.getCategory());
        ticket.setDescription(ticketDTO.getDescription());
        ticket.setTitle(ticketDTO.getTitle());
        //ticket.setPriority(ticketDTO.getPriority());
        ticket.setExpiry(ticketDTO.getExpiry());
        ticket.setAssignedAgent(ticketDTO.getAssignedAgent());
        ticket.setCreatedFor(ticketDTO.getCreatedFor());
    }

    private void checkIfAssigned(TicketDTO ticketDTO) {
        if (ticketDTO.getAssignedAgent() != null) {
            //createAssigned();
        }
        else {
            //createUnassigned();
        }
    }

    private void checkIfForSelf(TicketDTO ticketDTO) {
        if (ticketDTO.getCreatedFor() != null) {
            createForSelf();
        }
        else {
            createForAnotherUser();
        }
    }

    @RolesAllowed(Role.Name.CREATE_OWN_TICKET)
    private void createForSelf() {

    }

    @RolesAllowed(Role.Name.CREATE_FOREIGN_TICKET)
    private void createForAnotherUser() {

    }

    private void persist(final TicketDTO ticketDTO) {
        Ticket newTicket = new Ticket();

        // each field annotated is set

        newTicket.setDescription(ticketDTO.getDescription());
        newTicket.setCreated(new Date());

        em.persist(newTicket);
    }

    public TicketDTO read(final Integer ticketId, final List<String> expandedFields) {
        Ticket ticket;

        if (CollectionUtils.isEmpty(expandedFields)) {
            ticket = find(ticketId);
        }
        else {
            ticket = find(ticketId, expandedFields);
        }

        ModelMapper modelMapper = new ModelMapper();

        Condition<?, ?> isExpandable = new Condition<TicketDTO, Ticket>() {
            public boolean applies(MappingContext<TicketDTO, Ticket> context) {
                return context.getSourceType().isAnnotationPresent(Expandable.class);
            }
        };

        modelMapper.addMappings(new PropertyMap<TicketDTO, Ticket>() {
            protected void configure() {
                when(isExpandable).skip();
            }
        });

        TicketDTO ticketDTO = modelMapper.map(ticket, TicketDTO.class);


        return ticketDTO;
    }

    public List<Ticket> readAll() {
        return em.createNamedQuery(Ticket.Query.findAll, Ticket.class).getResultList();
    }

    public List<Ticket> readPage(int offset, int limit) {
        return em.createNamedQuery(Ticket.Query.findAll, Ticket.class).setFirstResult(offset).setMaxResults(limit).getResultList();
    }

    public void update(final TicketDTO ticketDTO) {
        Ticket ticket = find(ticketDTO.getId());

        ticket.setDescription(ticketDTO.getDescription());
        //ticket.setPriority(ticketDTO.getPriority());
        //ticket.setCategory(ticketDTO.getCategory());

        em.merge(ticket);
    }

    public void delete(Integer ticketId) {
        Ticket ticket = find(ticketId);

        ticket.setDeleted(new Date());

        em.merge(ticket);
    }

    private TicketDTO convertToDTO(Ticket ticket) {
        TicketDTO ticketDTO = new TicketDTO();

        ticketDTO.setAssigned(ticket.getAssigned());

        return ticketDTO;
    }

    private Ticket find(final Integer ticketId) {
        if (ticketId == null) throw new EntityNotFoundException();

        return em.find(Ticket.class, ticketId);
    }

    private Ticket find(final Integer ticketId, final List<String> expandedFields) {
        EntityGraph expandedTicketGraph = entityBuilder.getGraphWithFields(Ticket.class, expandedFields);

        Ticket ticket = em.createNamedQuery(Ticket.Query.findOne, Ticket.class)
            .setParameter("id", ticketId)
            .setHint("javax.persistence.fetchgraph", expandedTicketGraph)
            .getSingleResult();

        return ticket;
    }

    public void assignToCurrentAgent(final Integer ticketId) {
        final Ticket ticket = find(ticketId);

        User agent = null;

        assignAny(ticket, agent);
    }

    @RolesAllowed(Role.Name.ASSIGN_AGENT_TICKET)
    public void assign(final Integer ticketId, final User agent) {
        final Ticket ticket = find(ticketId);

//        if (!agent.isInRole(Role.RESOLVE_TICKET)) {
//            throw new AccessDeniedException(Role.RESOLVE_TICKET);
//        }

        if (ticket.isAssigned()) {
            //reassign();
        }

        reassign(ticket, agent);
    }

    private void reassign(final Ticket ticket, final User agent) {

        assignAny(ticket, agent);
    }

    private void assignAny(final Ticket ticket, final User agent) {
        ticket.setAssignedAgent(agent);

        em.merge(ticket);
    }

    public void unassign(final Integer ticketId, final User agent) {
        final Ticket ticket = find(ticketId);


    }

    public void open(final Integer ticketId) {
        final Ticket ticket = find(ticketId);

        ticket.setDeleted(null);
    }


    public void close(final Integer ticketId) {
        final Ticket ticket = find(ticketId);

        if (ticket.isAssigned()) return;

        ticket.setResolved(new Date());
    }

    public void close(final Integer ticketId, final User closer) {



    }

}
