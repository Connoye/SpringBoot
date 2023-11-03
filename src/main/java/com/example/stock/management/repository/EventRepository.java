 package com.example.stock.management.repository;

 import java.time.LocalDateTime;
 import java.util.List;

 import com.example.stock.management.model.EventStore;
 import org.springframework.data.repository.CrudRepository;
 import org.springframework.stereotype.Component;

 @Component
 public interface EventRepository extends CrudRepository<EventStore, Long>{


 	List<EventStore> findByEntityId(String entityId);
	
 	Iterable<EventStore> findByEntityIdAndEventTimeLessThanEqual(String entityId,LocalDateTime date);
 }

