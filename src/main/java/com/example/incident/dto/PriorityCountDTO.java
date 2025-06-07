package com.example.incident.dto;

public class PriorityCountDTO {
	
	
	private Integer priority;
    private Long count;

    public PriorityCountDTO(Integer priority, Long count) {
        this.priority = priority;
        this.count = count;
    }

    // Getters
    public Integer getPriority() {
        return priority;
    }

    public Long getCount() {
        return count;
    }

}
