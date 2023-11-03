 package com.example.stock.management;

 import java.time.LocalDateTime;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Optional;

 import com.google.gson.Gson;
 import lombok.RequiredArgsConstructor;
 import org.springframework.http.HttpStatus;
 import org.springframework.stereotype.Service;

 import com.fasterxml.jackson.core.JsonProcessingException;
 import com.fasterxml.jackson.databind.ObjectMapper;
 import org.springframework.web.server.ResponseStatusException;

 @Service
 @RequiredArgsConstructor
 public class EventService {

 	private final EventRepository repo;
 	private final PersonRepository personRepo;

 	public void addEvent(StockAddedEvent event) throws JsonProcessingException {

 		EventStore eventStore = new EventStore();

 		eventStore.setEventData(new ObjectMapper().writeValueAsString(event.getStockDetails()));

 		eventStore.setEventType("STOCK_ADDED");

 		eventStore.setEntityId(event.getStockDetails().getName());

 		eventStore.setEventTime(LocalDateTime.now());

 		repo.save(eventStore);
 	}

 	public void addEvent(StockRemovedEvent event) throws JsonProcessingException {

 		EventStore eventStore = new EventStore();

 		eventStore.setEventData(new ObjectMapper().writeValueAsString(event.getStockDetails()));

 		eventStore.setEventType("STOCK_REMOVED");
 		eventStore.setEntityId(event.getStockDetails().getName());

 		eventStore.setEventTime(LocalDateTime.now());

 		repo.save(eventStore);
 	}

 	public List<EventStore> fetchAllStockEvents(String name) {
 		return (repo.findByEntityId(name).stream().filter(stock->stock.getEventType().contains("STOCK"))).toList();
 	}

 	public Person fetchPerson(String id) {
		 Optional<Person> person = personRepo.findById(id);
        return person.orElse(null);
    }

 	public List<EventStoreDto> fetchEventsByName(String name) {
 		Iterable<EventStore> events = repo.findByEntityId(name);
 		List<EventStoreDto> eventsDto = new ArrayList<>();
 		for(EventStore store: events){
 			EventStoreDto dto = new EventStoreDto();
 			dto.setEventId(store.getEventId());
 			dto.setEventType(store.getEventType());
 			dto.setEntityId(store.getEntityId());
 			dto.setEventData(new Gson().fromJson(store.getEventData(), Object.class));
 			dto.setEventTime(store.getEventTime());
 			eventsDto.add(dto);
 		}
 		return eventsDto;
 	}

 	public List<EventStoreDto> fetchEvents() {
 		Iterable<EventStore> events = repo.findAll();
 		List<EventStoreDto> eventsDto = new ArrayList<>();
 		for(EventStore store: events){
 			EventStoreDto dto = new EventStoreDto();
 			dto.setEventId(store.getEventId());
 			dto.setEventType(store.getEventType());
 			dto.setEntityId(store.getEntityId());
 			dto.setEventData(new Gson().fromJson(store.getEventData(), Object.class));
 			dto.setEventTime(store.getEventTime());
 			eventsDto.add(dto);
 		}
 		return eventsDto;
 	}

 	public Iterable<EventStore> fetchAllEventsTillDate(String name,LocalDateTime date) {
 		return repo.findByEntityIdAndEventTimeLessThanEqual(name, date);
 	}

 	public void addEvent(PersonAddEvent event) throws JsonProcessingException {

 		EventStore eventStore = new EventStore();

 		eventStore.setEventData(new ObjectMapper().writeValueAsString(event.getPersonDetails()));

 		eventStore.setEventType("PERSON_ADDED");

 		eventStore.setEntityId(event.getPersonDetails().getId());

 		eventStore.setEventTime(LocalDateTime.now());

 		repo.save(eventStore);
 	}

 	public void addEvent(PersonRemoveEvent event) throws JsonProcessingException {

 		EventStore eventStore = new EventStore();

 		eventStore.setEventData(new ObjectMapper().writeValueAsString(event.getPersonDetails()));

 		eventStore.setEventType("PERSON_REMOVED");

 		eventStore.setEntityId(event.getPersonDetails().getId());

 		eventStore.setEventTime(LocalDateTime.now());

 		repo.save(eventStore);
 	}

 	public void addEvent(PersonUpdateEvent event) throws JsonProcessingException {

 		EventStore eventStore = new EventStore();

 		eventStore.setEventData(new ObjectMapper().writeValueAsString(event.getPersonDetails()));

 		eventStore.setEventType("PERSON_UPDATED");

 		eventStore.setEntityId(event.getPersonDetails().getId());

 		eventStore.setEventTime(LocalDateTime.now());

 		repo.save(eventStore);
 	}

 	public void addPerson(Person person){
 		try{
 			if(personRepo.existsById(person.getId())){
 				throw new ResponseStatusException(HttpStatus.CONFLICT, "Person record was not saved. This person id already exists.");
 			}
 			personRepo.save(person);
 			addEvent(
 				new PersonAddEvent(
 						person
 				)
 			);
 		}catch (JsonProcessingException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
         }
     }

 	public void updatePerson(Person person){
 		try{
 			Optional<Person> existingPerson = personRepo.findById(person.getId());
 			if(existingPerson.isEmpty()){
 				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person record was not found .");
 			}
 			existingPerson.get().setAddress(person.getAddress());
 			existingPerson.get().setAge(person.getAge());
 			existingPerson.get().setFirstName(person.getFirstName());
 			existingPerson.get().setLastName(person.getLastName());
 			existingPerson.get().setTelephone(person.getTelephone());
 			personRepo.save(existingPerson.get());
 			addEvent(
 				new PersonUpdateEvent(
 						existingPerson.get()
 				)
 			);
 		}catch(RuntimeException ex){
 			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Person record was not save due to system issues. Please try again later");
 		} catch (JsonProcessingException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
         }
     }

 	public void removePerson(String id){
 		try{
 			Optional<Person> person = personRepo.findById(id);
 			if(person.isEmpty()){
 				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person record was not found .");
 			}
 			personRepo.delete(person.get());
 			addEvent(
 					new PersonRemoveEvent(
 							person.get()
 					)
 			);
 		}catch (JsonProcessingException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
 		}
 	}
 }
