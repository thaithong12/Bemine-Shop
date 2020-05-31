package com.tq.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tq.entities.AccountEntity;
import com.tq.entities.Favourite;

@Repository
public interface FavouriteRepository extends CrudRepository<Favourite, Integer> {
	public List<Favourite> findByAccount(AccountEntity accountEntity);
}
