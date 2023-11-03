 package com.example.stock.management.controller;

 import java.time.LocalDate;
 import java.time.LocalDateTime;
 import java.util.List;

 import com.example.stock.management.event.StockAddedEvent;
 import com.example.stock.management.event.StockRemovedEvent;
 import com.example.stock.management.model.EventStore;
 import com.example.stock.management.model.Person;
 import com.example.stock.management.model.Stock;
 import com.example.stock.management.model.dto.EventStoreDto;
 import com.example.stock.management.service.EventService;
 import lombok.RequiredArgsConstructor;
 import org.springframework.web.bind.annotation.*;

 import com.fasterxml.jackson.core.JsonProcessingException;
 import com.google.gson.Gson;

 @RestController
 @RequiredArgsConstructor
 public class StockController {

 	private final EventService service;

 	@PostMapping("/stock")
 	public void addStock(@RequestBody Stock stockRequest) throws JsonProcessingException {
 		StockAddedEvent event = StockAddedEvent.builder().stockDetails(stockRequest).build();
 		service.addEvent(event);
 	}

 	@DeleteMapping("/stock")
 	public void removeStock(@RequestBody Stock stock) throws JsonProcessingException {
 		StockRemovedEvent event = StockRemovedEvent.builder().stockDetails(stock).build();
 		service.addEvent(event);
 	}

 	@GetMapping("/stock")
 	public Stock getStock(@RequestParam("name") String name) {
 		Iterable<EventStore> events = service.fetchAllStockEvents(name);
 		Stock currentStock = new Stock();

 		currentStock.setName(name);
 		currentStock.setUser("NA");

 		for (EventStore event : events) {
 			Stock stock = new Gson().fromJson(event.getEventData(), Stock.class);
 			if (event.getEventType().equals("STOCK_ADDED")) {
 				currentStock.setQuantity(currentStock.getQuantity() + stock.getQuantity());
 			} else if (event.getEventType().equals("STOCK_REMOVED")) {
 				currentStock.setQuantity(currentStock.getQuantity() - stock.getQuantity());
 			}
 		}
 		return currentStock;
 	}

 	@GetMapping("/person/{id}")
 	public Person getPerson(@RequestParam("id") String id) {
 		return service.fetchPerson(id);
 	}
	
 	@GetMapping("/events")
 	public List<EventStoreDto> getEvents(@RequestParam("name") String name) {
 		return service.fetchEventsByName(name);
 	}

 	@GetMapping("/allEvents")
 	public List<EventStoreDto> getAllEvents()  {
 		return service.fetchEvents();
 	}

 	@GetMapping("/stock/history")
 	public Stock getStockUntilDate(@RequestParam("date") String date,@RequestParam("name") String name) {
 		String[] dateArray = date.split("-");
 		LocalDateTime dateTill = LocalDate.of(Integer.parseInt(dateArray[0]), Integer.parseInt(dateArray[1]), Integer.parseInt(dateArray[2])).atTime(23, 59);

 		Iterable<EventStore> events = service.fetchAllEventsTillDate(name,dateTill);
 		Stock currentStock = new Stock();
 		currentStock.setName(name);
 		currentStock.setUser("NA");

 		for (EventStore event : events) {
 			Stock stock = new Gson().fromJson(event.getEventData(), Stock.class);
 			if (event.getEventType().equals("STOCK_ADDED")) {
 				currentStock.setQuantity(currentStock.getQuantity() + stock.getQuantity());
 			} else if (event.getEventType().equals("STOCK_REMOVED")) {
 				currentStock.setQuantity(currentStock.getQuantity() - stock.getQuantity());
 			}
 		}
 		return currentStock;
 	}

 	@PostMapping("/person")
 	public void addPerson(@RequestBody Person person) {
 		service.addPerson(person);
 	}

 	@PutMapping("/person")
 	public void updatePerson(@RequestBody Person person) {
 		service.updatePerson(person);
 	}

 	@DeleteMapping("/person/{id}")
 	public void removePerson(@RequestParam String id) {
 		service.removePerson(id);
 	}
 }
