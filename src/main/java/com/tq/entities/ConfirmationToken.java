package com.tq.entities;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "confirm_token")
public class ConfirmationToken {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="token_id")
    private long tokenid;

    @Column(name="confirmation_token")
    private String confirmationToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @OneToOne(targetEntity = AccountEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "account_id")
    private AccountEntity accountEntity;

    public ConfirmationToken(AccountEntity accountEntity) {
       this.accountEntity = accountEntity;
        createdDate = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }
    
    public ConfirmationToken() {
		// TODO Auto-generated constructor stub
	}

	public long getTokenid() {
		return tokenid;
	}

	public String getConfirmationToken() {
		return confirmationToken;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public AccountEntity getAccountEntity() {
		return accountEntity;
	}

	public void setTokenid(long tokenid) {
		this.tokenid = tokenid;
	}

	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setAccountEntity(AccountEntity accountEntity) {
		this.accountEntity = accountEntity;
	}

	public ConfirmationToken(String confirmationToken, AccountEntity accountEntity) {
		super();
		this.confirmationToken = confirmationToken;
		this.accountEntity = accountEntity;
	}
    
}
