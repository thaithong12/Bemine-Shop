
package com.tq.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tq.entities.AccountEntity;
@Repository
public interface AccountRepository extends
        CrudRepository<AccountEntity, Integer> {

    AccountEntity findByEmailLikeAndPasswordLike(
            String email, String password);

    @Query("Select acc From AccountEntity acc "
            + "Join fetch acc.accountRoles "
            + "Where acc.email Like ?1 and "
            + "acc.password Like ?2")
    AccountEntity findAccountByEmailAndPassword(
            String email, String password);
    
    
    
    public AccountEntity findByEmail(String email);
}
