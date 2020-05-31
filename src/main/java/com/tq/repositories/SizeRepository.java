package com.tq.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tq.entities.Size;

@Repository
public interface SizeRepository extends CrudRepository<Size, Integer> {
	
}
