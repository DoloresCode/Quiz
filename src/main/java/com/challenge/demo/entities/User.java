package com.challenge.demo.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

// Representation of the user in the platform
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private UUID uuid;

    // ManyToOne relationship between User and Site. "true" means that the user can exist without the site. when the User is loaded, its associated will be fetched from the database at the same moment.
    // the user table have a sitId column that is a foreign key to the site table
    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "site_id", referencedColumnName = "site_id")
    private Site site;

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }
}