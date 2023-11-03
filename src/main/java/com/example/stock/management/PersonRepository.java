 package com.example.stock.management;

 import org.springframework.data.repository.CrudRepository;
 import org.springframework.stereotype.Component;

 @Component
 public interface PersonRepository extends CrudRepository<Person, String>{
 }

