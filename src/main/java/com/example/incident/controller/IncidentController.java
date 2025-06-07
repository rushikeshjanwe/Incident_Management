package com.example.incident.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.incident.dto.IncidentRequestDTO;
import com.example.incident.dto.IncidentResponseDTO;
import com.example.incident.dto.PriorityCountDTO ;
import com.example.incident.dto.SystemIncidentCountResponse;
import com.example.incident.service.IncidentService;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {

    @Autowired
    private IncidentService service;

    
    // Point 1 : Add Data
    @PostMapping
    public IncidentResponseDTO createIncident(@RequestBody IncidentRequestDTO dto) {
        return service.createIncident(dto);
    }

    // point 1 : Update Data
    @PutMapping("/{id}")
    public IncidentResponseDTO updateIncident(@PathVariable String id, @RequestBody IncidentRequestDTO dto) {
        return service.updateIncident(id, dto);
    }
    
    
   // Point 4 - Get incident details by ID
    @GetMapping("/{incidentId}")
    public IncidentResponseDTO getIncidentDetails(@PathVariable String incidentId) {
        return service.getIncidentById(incidentId);
    }
    
    
    @GetMapping("/priority-counts")
    public ResponseEntity<List<PriorityCountDTO>> getPriorityCountsByAppId(
            @RequestParam(name = "appId") String appId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to,
            @RequestParam(name = "status", required = false) String status) {
        List<PriorityCountDTO> response = service.getPriorityCountsByAppId(appId, from, to, status);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/system-counts")
    public ResponseEntity<List<SystemIncidentCountResponse>> getSystemIncidentCountsByPriority(
            @RequestParam(name = "priority") Integer priority,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        List<SystemIncidentCountResponse> response = service.getSystemIncidentCountsByPriority(priority, from, to);
        return ResponseEntity.ok(response);
    }
   
   
//    @GetMapping("/count")
//    public ResponseEntity<PriorityCountResponse> getPriorityCounts(
//        @RequestParam(name = "appId") String appId,
//        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
//        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to,
//        @RequestParam String status
//    ) {
//        PriorityCountResponse response = service.getPriorityCounts(appId, from, to, status);
//        return ResponseEntity.ok(response);
//    }
    
    


}