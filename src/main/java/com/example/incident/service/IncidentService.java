package com.example.incident.service;

import java.time.LocalDateTime; 
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.incident.dto.IncidentRequestDTO;
import com.example.incident.dto.IncidentResponseDTO;
import com.example.incident.dto.PriorityCountDTO ;
import com.example.incident.dto.SystemIncidentCountResponse;
import com.example.incident.entity.Incident;
import com.example.incident.repository.IncidentRepository;

@Service
public class IncidentService {
	
	 @Autowired
	    private IncidentRepository repository;
	    
	    //Point 1
	    public IncidentResponseDTO createIncident(IncidentRequestDTO dto) {
	        Incident incident = new Incident();
	        incident.setIncidentId(dto.getIncidentId());
	        incident.setApplicationId(dto.getApplicationId());
	        incident.setApplicationName(dto.getApplicationName());
	        incident.setPriority(dto.getPriority());
	        incident.setIncidentName(dto.getIncidentName());
	        incident.setDescription(dto.getDescription());
	        incident.setStatus(dto.getStatus());
	       

	        Incident saved = repository.save(incident);
	        return mapToResponseDTO(saved);
	    }

	    //Point 1
	    public IncidentResponseDTO updateIncident(String id, IncidentRequestDTO dto) {
	        Optional<Incident> optionalIncident = repository.findById(id);
	        if (optionalIncident.isEmpty()) {
	            throw new RuntimeException("Incident not found with ID: " + id);
	        }

	        Incident incident = optionalIncident.get();
	        incident.setApplicationId(dto.getApplicationId());
	        incident.setApplicationName(dto.getApplicationName());
	        incident.setPriority(dto.getPriority());
	        incident.setIncidentName(dto.getIncidentName());
	        incident.setDescription(dto.getDescription());
	        incident.setStatus(dto.getStatus());
	        incident.setDateTime(LocalDateTime.now());

	        Incident updated = repository.save(incident);
	        return mapToResponseDTO(updated);
	    }
	    
	    // Point 2
	    public List<PriorityCountDTO> getPriorityCountsByAppId(String appId, LocalDateTime from, LocalDateTime to, String status) {
	        return repository.findPriorityCountsByAppId(appId, from, to, status);
	    }
	
	    // Point 3
	    public List<SystemIncidentCountResponse> getSystemIncidentCountsByPriority(Integer priority, LocalDateTime from, LocalDateTime to) {
	        return repository.findSystemIncidentCountsByPriority(priority, from, to);
	    }
	    
	  
	    //Point 4
	    public IncidentResponseDTO getIncidentById(String incidentId) {
	        Optional<Incident> incident = repository.findById(incidentId);
	        if (incident.isEmpty()) {
	            throw new RuntimeException("Incident not found");
	        }

	        Incident i = incident.get();
	        IncidentResponseDTO response = new IncidentResponseDTO();
	        response.setIncidentId(i.getIncidentId());
	        response.setApplicationId(i.getApplicationId());
	        response.setApplicationName(i.getApplicationName());
	        response.setDateTime(i.getDateTime());
	        response.setDescription(i.getDescription());
	        response.setIncidentName(i.getIncidentName());
	        response.setPriority(i.getPriority());
	        response.setStatus(i.getStatus());
	        return response;
	    }
	    
	    
	  

	    private IncidentResponseDTO mapToResponseDTO(Incident incident) {
	        IncidentResponseDTO dto = new IncidentResponseDTO();
	        dto.setIncidentId(incident.getIncidentId());
	        dto.setApplicationId(incident.getApplicationId());
	        dto.setApplicationName(incident.getApplicationName());
	        dto.setPriority(incident.getPriority());
	        dto.setIncidentName(incident.getIncidentName());
	        dto.setDescription(incident.getDescription());
	        dto.setStatus(incident.getStatus());
	        dto.setDateTime(incident.getDateTime());
	        return dto;
	    }
	    
	    
	}

