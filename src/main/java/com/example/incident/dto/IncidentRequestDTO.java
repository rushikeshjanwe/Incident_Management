package com.example.incident.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncidentRequestDTO {
	
	   private String incidentId;
	    private String applicationId;
	    private String applicationName;
	    private Integer priority;
	    private String incidentName;
	    private String description;
	    private String status;
	    


}
