package com.example.demo.ca.jrvs.insurance_api.repository;

import com.example.demo.ca.jrvs.insurance_api.model.Person;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends MongoRepository<Person, ObjectId> {}