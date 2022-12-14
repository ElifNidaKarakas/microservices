package com.kodlamaio.invonteryServer.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.invonteryServer.entities.Model;

public interface ModelRepository extends JpaRepository<Model, String>{
Model findByName(String name);
}