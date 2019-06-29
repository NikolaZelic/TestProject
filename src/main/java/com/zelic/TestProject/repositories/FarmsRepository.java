package com.zelic.TestProject.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zelic.TestProject.entities.Farm;

@Repository
public interface FarmsRepository
extends CrudRepository<Farm, Long>{

}
