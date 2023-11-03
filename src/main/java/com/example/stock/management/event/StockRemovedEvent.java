 package com.example.stock.management.event;

 import com.example.stock.management.service.StockEvent;
 import com.example.stock.management.model.Stock;
 import lombok.Builder;
 import lombok.Data;

 @Builder
 @Data
 public class StockRemovedEvent implements StockEvent {

 	private Stock stockDetails;
 }
