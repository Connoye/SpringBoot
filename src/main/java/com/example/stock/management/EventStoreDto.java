 package com.example.stock.management;

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
