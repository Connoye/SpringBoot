 package com.example.stock.management.repository;

 import com.example.stock.management.model.Person;
 import org.springframework.data.repository.CrudRepository;
 import org.springframework.stereotype.Component;

 @Component
 public interface PersonRepository extends CrudRepository<Person, String>{
 }

