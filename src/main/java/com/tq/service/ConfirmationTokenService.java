package com.tq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tq.entities.ConfirmationToken;
import com.tq.repositories.ConfirmationTokenRepository;

@Service
public class ConfirmationTokenService {
	@Autowired
	ConfirmationTokenRepository confirmationTokenRepository;
	
	public ConfirmationToken getToken(String confirmationToken) {
		return confirmationTokenRepository.findByConfirmationToken(confirmationToken);
	}
	
	public ConfirmationToken saveToken(ConfirmationToken tk) {
		return confirmationTokenRepository.save(tk);
	}
}
