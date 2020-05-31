package com.tq.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tq.entities.Color;

@Repository
public interface ColorRepository extends CrudRepository<Color, Integer>{

}
