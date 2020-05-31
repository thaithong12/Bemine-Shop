package com.tq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tq.entities.AccountRoleEntity;
import com.tq.enums.AccountRole;
import com.tq.repositories.AccountRoleRepository;

@Service
public class AccountRoleService {
	@Autowired
	AccountRoleRepository accountRoleRepository;

	public AccountRoleEntity findByRole(AccountRole accountRoleEntity) {
		return accountRoleRepository.getAccountRoleByRole(accountRoleEntity);
	}

}
