 package com.example.stock.management;

 import lombok.Builder;
 import lombok.Data;

 @Builder
 @Data
 public class PersonRemoveEvent {
     private Person personDetails;
 }
