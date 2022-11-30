package com.kodlamaio.invonteryServer.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.invonteryServer.entities.Car;

public interface CarRepository extends JpaRepository<Car, String>{
Car findByPlate(String plate);
}