package com.ticket.business.user.boundary;

import com.ticket.business.user.entity.Role;
import com.ticket.business.user.entity.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.security.enterprise.SecurityContext;
import java.util.List;

@Stateless
public class UserService {

    @PersistenceContext(unitName = "TicketModel")
    private EntityManager em;
    //private @Inject SecurityContext securityContext;

    public List<User> findAllByRole(Role role) {
        List<User> agents = em.createNamedQuery(User.Query.findAllByRole, User.class).setParameter("role", role).getResultList();
        return agents;
    }

}
