package com.kodlamaio.invonteryServer.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.kodlamaio.invonteryServer.entities.Car;

public interface CarRepository extends JpaRepository<Car, String>{
Car findByPlate(String plate);
boolean existsByPlateIgnoreCase(String plate);
@Modifying
@Query(value = "update Cars set state = :state where id = :id", nativeQuery = true)
@Transactional
void changeStateByCarId(int state, String id);

}