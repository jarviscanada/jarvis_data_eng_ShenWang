package com.example.demo.ca.jrvs.insurance_api.service;

import com.example.demo.ca.jrvs.insurance_api.model.Person;
import com.example.demo.ca.jrvs.insurance_api.repository.PersonRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.*;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    static final Logger logger = LoggerFactory.getLogger(PersonService.class);
    @Autowired
    private final PersonRepository repo;

    public PersonService(PersonRepository repo) {
        this.repo = repo;
        logger.info("PersonService created");
    }

    public void save(Person person) {
        repo.save(person);
        logger.info("Person saved");
    }
    public void saveAll(List<Person> people) {
        repo.saveAll(people);
        logger.info("all Person saved");
    }
    public Optional<Person> findOne(ObjectId id) {
        return repo.findById(id);
    }
    public List<Person> findAll() {
        return repo.findAll();
    }
}

    /* public List<Person> findAll(List<ObjectId> ids) { //TODO }
    public void delete(ObjectIdid) { //TODO }
    public void delete(List<ObjectId> ids) { //TODO }
    public void deleteAll() { //TODO }
    public void update(Person person) { //TODO }
    public void update(List<Person> people) { //TODO }
    public long count() { //TODO }
    public double getAverageAge() { //TODO }
    public int getMaxCars() { //TODO }*/