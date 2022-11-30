package com.kodlamaio.invonteryServer.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.invonteryServer.entities.Brand;

public interface BrandRepository extends JpaRepository<Brand, String>{
Brand findByName(String name);
}

