package com.tq.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tq.entities.Review;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Integer>{
	
}
