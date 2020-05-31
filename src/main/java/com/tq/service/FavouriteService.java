package com.tq.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tq.entities.AccountEntity;
import com.tq.entities.Favourite;
import com.tq.repositories.FavouriteRepository;

@Service
public class FavouriteService {
	@Autowired
	FavouriteRepository favouriteRepository;
	
	public List<Favourite> getListFavouritesByAccount(AccountEntity accountEntity) {
		return favouriteRepository.findByAccount(accountEntity);
	}
	
	public Favourite saveFavourite(Favourite f) {
		return favouriteRepository.save(f);
	}
	
	public void DeleteFavourite(int favoriteId) {
		favouriteRepository.delete(favoriteId);
	}
}
