package com.tq.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tq.entities.AccountRoleEntity;
import com.tq.enums.AccountRole;

@Repository
public interface AccountRoleRepository extends CrudRepository<AccountRoleEntity, Integer> {
	@Query(value = "select a from AccountRoleEntity a \r\n" + 
			"where a.role = ?1")
	public AccountRoleEntity getAccountRoleByRole(AccountRole accountRole);
}
