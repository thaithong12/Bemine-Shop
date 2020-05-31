
package com.tq.security;

import com.tq.entities.AccountEntity;
import com.tq.entities.AccountRoleEntity;
import com.tq.enums.StatusAccount;
import com.tq.service.AccountService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class CustomAuthenticationProvider implements
        AuthenticationProvider {
	
	

    @Autowired
    private AccountService accountService;

    @Override
    public Authentication authenticate(Authentication a) throws AuthenticationException {
        String email = a.getName().trim();
        String password = a.getCredentials().toString().trim();
        if (!StringUtils.isEmpty(email)
                && !StringUtils.isEmpty(password)) {
            AccountEntity account = accountService.findByEmail(email);
            if(BCrypt.checkpw(password, account.getPassword())) {
                if (account != null && account.getId() > 0 && account.getStatus().equals(StatusAccount.ACTIVE)) {
                    List<GrantedAuthority> roles = new ArrayList<>();
                    for (AccountRoleEntity accountRole : account.getAccountRoles()) {
                        roles.add(
                                new SimpleGrantedAuthority(accountRole.getRole().toString().trim())
                        );
                    }
                    return new UsernamePasswordAuthenticationToken(account, account.getEmail(), roles);
                } else {
                    return null;
                }
            }else {
            	return null;
            }
            
            
        
        } else {
            return null;
          
        }
    }

    @Override
    public boolean supports(Class<?> type) {
        return type.equals(UsernamePasswordAuthenticationToken.class);
    }

}
