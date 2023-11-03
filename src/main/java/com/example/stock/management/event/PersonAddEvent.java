 package com.example.stock.management.event;

 import com.example.stock.management.model.Person;
 import lombok.AllArgsConstructor;
 import lombok.Builder;
 import lombok.Data;
 import lombok.NoArgsConstructor;

 @Builder
 @Data
 @AllArgsConstructor
 @NoArgsConstructor
 public class PersonAddEvent {
     private Person personDetails;
 }
