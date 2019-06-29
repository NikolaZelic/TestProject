package com.zelic.TestProject.entities;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity(name = "UserAccount") 
@Table(name = "users_accounts")
public class UserAccount {
    @EmbeddedId
    private UserAccountId id;
    @Column(nullable = false)
    private Integer isOwner;

    @ManyToOne
    @MapsId("userId")
    private User user;

    @ManyToOne
    @MapsId("accountId")
    private Account account;

    private UserAccount() {}

    public UserAccount(User user, Account account, Integer isOwner) {
        this.user = user;
        this.account = account;
        this.isOwner = isOwner;
        this.id = new UserAccountId(user.getId(), account.getId());
    }

   @Override
	public String toString() {
		return "UserAccount [user=" + user + ", account=" + account + "]";
	}

	// GETERS & SETERS
    public UserAccountId getId() {
		return id;
	}

	public void setId(UserAccountId id) {
		this.id = id;
	}

	public Integer getIsOwner() {
		return isOwner;
	}

	public void setIsOwner(Integer isOwner) {
		this.isOwner = isOwner;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isOwner == null) ? 0 : isOwner.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserAccount other = (UserAccount) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isOwner == null) {
			if (other.isOwner != null)
				return false;
		} else if (!isOwner.equals(other.isOwner))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
    
	
	
}
