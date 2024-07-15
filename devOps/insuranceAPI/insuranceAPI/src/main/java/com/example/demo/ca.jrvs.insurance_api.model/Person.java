package com.example.demo.ca.jrvs.insurance_api.model;

import java.util.List;
import java.util.Date;
import java.util.Objects;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.crypto.Data;
@Document("people")
public class Person {

    private ObjectId id;
    private String firstName;
    private String lastName;
//    private int age;
//    private Address addressEntity;
//    private Boolean insurance;
//    private List<Car> cars;
//    private Date createdAt = new Date();


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
