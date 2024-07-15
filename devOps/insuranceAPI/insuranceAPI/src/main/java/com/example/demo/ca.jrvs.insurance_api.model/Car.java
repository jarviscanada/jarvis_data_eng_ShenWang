package com.example.demo.ca.jrvs.insurance_api.model;

import java.util.Objects;

public class Car {
    private String model;
    private int maxSpeed;
    private String make;

    public String getModel() {
        return model;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public String getMake() {
        return make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setMake(String make) {
        this.make = make;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", maxSpeed=" + maxSpeed +
                ", make='" + make + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return maxSpeed == car.maxSpeed && Objects.equals(model, car.model) && Objects.equals(make, car.make);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, maxSpeed, make);
    }
}
