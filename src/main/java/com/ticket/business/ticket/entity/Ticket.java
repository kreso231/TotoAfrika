package com.ticket.business.ticket.entity;

import com.ticket.business.Expandable;
import com.ticket.business.user.entity.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ticket", schema = "public")
public class Ticket {

    public static class Query {
        public static final String findAll = "Ticket.findAll";
        public static final String findOne = "Ticket.findOne";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Expandable
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"assignedAgent\"")
    private User assignedAgent;

    @Expandable
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"createdBy\"")
    private User createdBy;

    @Expandable
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"createdFor\"")
    private User createdFor;

//    @ManyToOne
//    private Category category;
//
//    @ManyToOne
//    private Priority priority;

    @Basic
    private String title;

    @Basic
    private String description;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date deleted;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date resolved;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date assigned;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiry;




    /* Business logic methods */

    public TicketStatusEnum getStatus() {
        if (deleted != null) return TicketStatusEnum.deleted;
        else if (resolved != null) return TicketStatusEnum.closed;
        else return TicketStatusEnum.open;
    }

    @PrePersist
    private void prePersist() {
        created = new Date();
    }






    /* Convenience methods */

    public boolean isOpen() {
        return getStatus() == TicketStatusEnum.open;
    }

    public boolean isClosed() {
        return getStatus() == TicketStatusEnum.closed;
    }

    public boolean isDeleted() {
        return getStatus() == TicketStatusEnum.deleted;
    }

    public boolean isAssigned() {
        return assigned != null;
    }

    public boolean isUnassigned() {
        return assigned == null;
    }





    /* Getters and setters */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getAssignedAgent() {
        return assignedAgent;
    }

    public void setAssignedAgent(User assignedAgent) {
        this.assignedAgent = assignedAgent;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getCreatedFor() {
        return createdFor;
    }

    public void setCreatedFor(User createdFor) {
        this.createdFor = createdFor;
    }

//    public Category getCategory() {
//        return category;
//    }
//
//    public void setCategory(Category category) {
//        this.category = category;
//    }
//
//    public Priority getPriority() {
//        return priority;
//    }
//
//    public void setPriority(Priority priority) {
//        this.priority = priority;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getDeleted() {
        return deleted;
    }

    public void setDeleted(Date deleted) {
        this.deleted = deleted;
    }

    public Date getResolved() {
        return resolved;
    }

    public void setResolved(Date resolved) {
        this.resolved = resolved;
    }

    public Date getAssigned() {
        return assigned;
    }

    public void setAssigned(Date assigned) {
        this.assigned = assigned;
    }

    public Date getExpiry() {
        return expiry;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

}
