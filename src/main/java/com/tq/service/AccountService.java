
package com.tq.service;

import com.tq.entities.AccountEntity;
import com.tq.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public AccountEntity
            findAccountByEmailAndPassword(String email, String password) {
        return accountRepository.findAccountByEmailAndPassword(email, password);
    }
    
    public AccountEntity findByEmail(String email) {
    	return accountRepository.findByEmail(email);
    }
    
    public AccountEntity findAccountById(int id) {
    	return accountRepository.findOne(id);
    }
    
    public AccountEntity saveAccount(AccountEntity accountEntity) {
    	return accountRepository.save(accountEntity);
    }
    
}
