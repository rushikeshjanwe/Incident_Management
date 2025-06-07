package com.example.incident.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.incident.dto.PriorityCountDTO;
import com.example.incident.dto.SystemIncidentCountResponse;
import com.example.incident.entity.Incident;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, String>{

	@Query("SELECT new com.example.incident.dto.PriorityCountDTO(i.priority, COUNT(i)) " +
	           "FROM Incident i " +
	           "WHERE i.applicationId = :appId " +
	           "AND i.dateTime BETWEEN :from AND :to " +
	           "AND (:status IS NULL OR i.status = :status) " +
	           "GROUP BY i.priority")
	    List<PriorityCountDTO> findPriorityCountsByAppId(
	            @Param("appId") String appId,
	            @Param("from") LocalDateTime from,
	            @Param("to") LocalDateTime to,
	            @Param("status") String status);
	
	
//	   @Query(value = "SELECT i.priority AS priority, COUNT(i.incident_id) AS count " +
//	           "FROM incident i " +
//	           "WHERE i.application_id = :appId " +
//	           "AND i.date_time BETWEEN :from AND :to " +
//	           "AND (:status IS NULL OR i.status = :status) " +
//	           "AND i.priority IS NOT NULL " +
//	           "GROUP BY i.priority", nativeQuery = true)
//	    List<PriorityCountDTO> findPriorityCountsByAppId(
//	            @Param("appId") String appId,
//	            @Param("from") LocalDateTime from,
//	            @Param("to") LocalDateTime to,
//	            @Param("status") String status);

	   
	   @Query("SELECT new com.example.incident.dto.SystemIncidentCountResponse(i.applicationName, COUNT(i), i.status) " +
	           "FROM Incident i " +
	           "WHERE i.priority = :priority " +
	           "AND i.dateTime BETWEEN :from AND :to " +
	           "GROUP BY i.applicationName, i.status")
	    List<SystemIncidentCountResponse> findSystemIncidentCountsByPriority(
	            @Param("priority") Integer priority,
	            @Param("from") LocalDateTime from,
	            @Param("to") LocalDateTime to);
   
	   
//	   @Query(value = "SELECT i.application_name AS applicationName, COUNT(i.incident_id) AS count, i.status AS status " +
//	           "FROM incident i " +
//	           "WHERE i.priority = :priority " +
//	           "AND i.date_time BETWEEN :from AND :to " +
//	           "AND i.application_name IS NOT NULL " +
//	           "AND i.status IS NOT NULL " +
//	           "GROUP BY i.application_name, i.status", nativeQuery = true)
//	    List<SystemIncidentCountResponse> findSystemIncidentCountsByPriority(
//	            @Param("priority") Integer priority,
//	            @Param("from") LocalDateTime from,
//	            @Param("to") LocalDateTime to);
	
}
