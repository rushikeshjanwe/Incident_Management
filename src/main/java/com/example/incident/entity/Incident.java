package com.example.incident.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Incident {

	    @Id
	    private String incidentId;

	    private String applicationId;
	    private String applicationName;
	    private Integer priority;
	    private String incidentName;
	    private String description;
	    private String status;
	    private LocalDateTime dateTime;

	    @PrePersist
	    public void onCreate() {
	        this.dateTime = LocalDateTime.now();
	    }
  
	    @PreUpdate
	    public void preUpdate() {
	    	this.dateTime=LocalDateTime.now();
	    }
	}


