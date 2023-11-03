package com.example.stock.management.service;

import com.example.stock.management.model.Person;
import com.example.stock.management.repository.EventRepository;
import com.example.stock.management.repository.PersonRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@AutoConfigureWebTestClient
@EnableAutoConfiguration
class EventStoreTest {

    @InjectMocks
    EventService eventService;

    @Mock
    PersonRepository personRepo;

    @Mock
    EventRepository eventRepo;
    Person person;

    @BeforeEach
    void setUp() {
        person = new Person(
                "123",
                "Raeshawn",
                "Gordon",
                "12 Test LAne",
                "1234567899",
                35

        );
    }

    @Test
    void addNewPerson(){
        when(personRepo.existsById(person.getId())).thenReturn(false);
        eventService.addPerson(person);
        verify(personRepo, times(1)).save(person);

    }
}
