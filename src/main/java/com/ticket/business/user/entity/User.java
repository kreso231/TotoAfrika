package com.ticket.business.user.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "\"users\"", schema = "public")
public class User {

    public static class Query {
        public static final String findAllByRole = "Ticket.findByRole";
        public static final String findForAssignment = "Ticket.findForAssignment";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    private String username;

    @Basic
    private String email;

//    @ManyToMany
//    private List<Group> groups;


//    public List<Role> getRoles() {
//        return roles;
//    }
//
//    public boolean isInRole(Role role) {
//        return roles.stream().anyMatch(r -> r.equals(role));
//    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public List<Group> getGroups() {
//        return groups;
//    }
//
//    public void setGroups(List<Group> groups) {
//        this.groups = groups;
//    }
}
