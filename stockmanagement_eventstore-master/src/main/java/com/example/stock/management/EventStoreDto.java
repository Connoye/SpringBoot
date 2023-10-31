package com.example.stock.management;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventStoreDto {

	private long eventId;

	private String eventType;

	private String entityId;
	
	private Object eventData;

	private LocalDateTime eventTime;

}
