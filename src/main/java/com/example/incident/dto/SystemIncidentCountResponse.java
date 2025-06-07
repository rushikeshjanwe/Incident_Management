package com.example.incident.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SystemIncidentCountResponse {
	
	    private String applicationName;
	    private Long count;
	    private String status;

	 // Constructor required by JPA query
	    public SystemIncidentCountResponse(String applicationName, Long count, String status) {
	        this.applicationName = applicationName;
	        this.count = count;
	        this.status = status;
	    }

}
