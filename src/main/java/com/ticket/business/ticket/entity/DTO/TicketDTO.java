package com.ticket.business.ticket.entity.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ticket.business.ticket.entity.Category;
import com.ticket.business.ticket.entity.Priority;
import com.ticket.business.user.entity.User;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

public class TicketDTO {

    private Integer id;

    private User assignedAgent;

    private User createdBy;

    private User createdFor;

//    private Category category;
//
//    private Priority priority;

    private String title;

    private String description;

    private Date created;

    private Date deleted;

    private Date resolved;

    private Date assigned;

    private Date expiry;

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
