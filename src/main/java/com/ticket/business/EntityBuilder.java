package com.ticket.business;

import com.ticket.business.ticket.entity.Ticket;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class EntityBuilder {

    @PersistenceContext(unitName = "TicketModel")
    private EntityManager em;

    public <T> EntityGraph getGraphWithFields(final Class<T> entityClass, final List<String> expandedFields) {
        EntityGraph<T> expandedTicketGraph = em.createEntityGraph(entityClass);

        for (String expandedField : expandedFields) {
            expandedTicketGraph.addAttributeNodes(expandedField);
        }

        return expandedTicketGraph;
    }
}
