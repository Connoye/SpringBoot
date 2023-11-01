 package com.example.stock.management;

 import java.time.LocalDateTime;
 import java.util.Map;

 import jakarta.persistence.Column;
 import jakarta.persistence.Entity;
 import jakarta.persistence.GeneratedValue;
 import jakarta.persistence.GenerationType;
 import jakarta.persistence.Id;

 import lombok.Builder;
 import lombok.Data;

 @Entity
 @Data

 public class EventStore {

 	@Id
 	@GeneratedValue(strategy = GenerationType.IDENTITY)
 	private long eventId;

 	private String eventType;

 	private String entityId;
	
 	private String eventData;

 	private LocalDateTime eventTime;

 }
