
package com.tq.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.tq.enums.StatusAccount;

@Entity
@Table(name = "account")
public class AccountEntity extends PersonalInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
    private String password;
    
    private String avatar;

	@Transient
    private String rePassword;
    @Enumerated(EnumType.STRING)
    private StatusAccount status = StatusAccount.BLOCK;
    
    @ManyToMany(cascade = {CascadeType.PERSIST,
        CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "acc_role_relationship",
            joinColumns = @JoinColumn(name = "acc_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "acc_role_id",
                    referencedColumnName = "id"))
    private List<AccountRoleEntity> accountRoles;
    
    @OneToMany(mappedBy = "accountEntity")
    List<Customer> customer;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    List<Favourite> favourites ;
    
    @OneToMany(cascade = CascadeType.ALL , mappedBy = "accountEntity")
    List<Review> reviews; 
    
    public AccountEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AccountEntity(String password, String rePassword, StatusAccount status,
			List<AccountRoleEntity> accountRoles) {
		super();
		this.password = password;
		this.rePassword = rePassword;
		this.status = status;
		this.accountRoles = accountRoles;
	}
    
	public AccountEntity(String password, String avartar, StatusAccount status) {
		super();
		this.password = password;
		this.avatar = avartar;
		this.status = status;
	}

	public AccountEntity(int id, String password, StatusAccount status, List<AccountRoleEntity> accountRoles) {
		super();
		this.id = id;
		this.password = password;
		this.status = status;
		this.accountRoles = accountRoles;
	}

	public AccountEntity(int id, String password, StatusAccount status, List<AccountRoleEntity> accountRoles,
			List<Customer> customer) {
		super();
		this.id = id;
		this.password = password;
		this.status = status;
		this.accountRoles = accountRoles;
		this.customer = customer;
	}

	public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<AccountRoleEntity> getAccountRoles() {
        return accountRoles;
    }

    public void setAccountRoles(List<AccountRoleEntity> accountRoles) {
        this.accountRoles = accountRoles;
    }

	public StatusAccount isStatus() {
		return status;
	}


	public void setStatus(StatusAccount status) {
		this.status = status;
	}



	public List<Favourite> getFavourites() {
		return favourites;
	}

	public void setFavourites(List<Favourite> favourites) {
		this.favourites = favourites;
	}

	public List<Customer> getCustomer() {
		return customer;
	}

	public void setCustomer(List<Customer> customer) {
		this.customer = customer;
	}

	public StatusAccount getStatus() {
		return status;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	

}
