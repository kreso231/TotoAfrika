package com.ticket.business.ticket.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Priority {

    @Id
    private PriorityEnum id;

    private String name;

}
