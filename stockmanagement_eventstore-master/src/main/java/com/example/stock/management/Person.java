package com.example.stock.management;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Person {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String address;
    private String telephone;
    private int age;
}
