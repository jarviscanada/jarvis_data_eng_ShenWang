package com.example.demo.ca.jrvs.insurance_api.controller;


import com.example.demo.ca.jrvs.insurance_api.model.Person;
import com.example.demo.ca.jrvs.insurance_api.service.PersonService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/insurance_api")

public class PersonController {

    @Autowired
    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @PostMapping("person")
    @ResponseStatus(HttpStatus.CREATED)
    public void postPerson(@RequestBody Person person) {
        service.save(person);
    }

    @GetMapping("people")
    public List<Person> getPeople() {
        return service.findAll();
    }

    @GetMapping("person/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable ObjectId id) {
        Optional<Person> o = service.findOne(id);
        if (o.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(o.get());
    }

    //FINISH THE REST ACCORDING TO THE FEATURES IN PERSONSERVICE

}
