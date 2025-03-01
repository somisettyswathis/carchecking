package com.example.carvaluation.model;

import lombok.*;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Car {
    public String registration;
    public String make;
    public String model;
    public String year;

    // Override equals() and hashCode() for comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return registration.equals(car.registration) &&
                make.equals(car.make) &&
                model.equals(car.model) &&
                year.equals(car.year);
    }

    @Override
    public int hashCode() {
        // Use Objects.hash() for a simple hashCode implementation
        return Objects.hash(registration, make, model, year);
    }

    @Override
    public String toString() {
        return "Car{" +
                "registration='" + registration + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}
