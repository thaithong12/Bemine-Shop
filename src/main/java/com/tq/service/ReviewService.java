package com.tq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tq.entities.Review;
import com.tq.repositories.ReviewRepository;

@Service
public class ReviewService {
	
	@Autowired
	ReviewRepository reviewRepository;
	
	public void addReview(Review rv ) {
		reviewRepository.save(rv);
	}
}
