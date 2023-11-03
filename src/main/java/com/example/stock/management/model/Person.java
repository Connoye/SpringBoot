 package com.example.stock.management.model;

 import jakarta.persistence.Entity;
 import jakarta.persistence.Id;
 import lombok.AllArgsConstructor;
 import lombok.Data;
 import lombok.NoArgsConstructor;

 @Data
 @Entity
 @AllArgsConstructor
 @NoArgsConstructor
 public class Person {
     @Id
     private String id;
     private String firstName;
     private String lastName;
     private String address;
     private String telephone;
     private int age;
 }
