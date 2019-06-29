package com.zelic.TestProject.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
// TODO Dodati restrikciju na duplikat emjla
@Entity(name = "User")
@Table(name = "users")
public class User {
	@Id
    @GeneratedValue
    private Long id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String lastname;
	@Column(nullable = false)
	@NotEmpty
	private String email;
	@Column(nullable = false)
	@NotEmpty
//	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;	
	private String cardNumber;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<UserAccount> accounts = new ArrayList<>();

    public User() {
    }

    public User(Long id, String name, String lastname, String email) {
		super();
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.email = email;
	}
    
    

	public User(String name, String lastname, String email, String password, String cardNumber) {
		super();
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.cardNumber = cardNumber;
	}

	public void addAccount(Account account, boolean isOwner) {
        UserAccount userAccount = new UserAccount(this, account, isOwner?1:0);
        this.accounts.add(userAccount);
        account.getUsers().add(userAccount);
    }

    public void removeTag(Account account) {
        for (Iterator<UserAccount> iterator = accounts.iterator(); iterator.hasNext(); ) {
            UserAccount userAccount = iterator.next();
            if (userAccount.getUser().equals(this) && userAccount.getAccount().equals(account)) {
                iterator.remove();
                userAccount.getAccount().getUsers().remove(userAccount);
                userAccount.setUser(null);
                userAccount.setAccount(null);
            }
        }
    }

    @Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", accountsNumber=" + accounts.size()+ "]";
	}

//	GETERS & SETERS
    public Long getId() {
        return id;
    }
    public List<UserAccount> getAccounts() {
        return accounts;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAccounts(List<UserAccount> accounts) {
		this.accounts = accounts;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cardNumber == null) ? 0 : cardNumber.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((accounts == null) ? 0 : accounts.hashCode());
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
		User other = (User) obj;
		if (cardNumber == null) {
			if (other.cardNumber != null)
				return false;
		} else if (!cardNumber.equals(other.cardNumber))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (accounts == null) {
			if (other.accounts != null)
				return false;
		} else if (!accounts.equals(other.accounts))
			return false;
		return true;
	}

}