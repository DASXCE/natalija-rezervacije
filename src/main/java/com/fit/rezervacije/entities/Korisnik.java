package com.fit.rezervacije.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.tapestry5.beaneditor.NonVisual;

@Entity
public class Korisnik {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonVisual
	private long id;

	private String username;

	private String password;

	@ManyToOne
	private Uloga uloga;

	public Korisnik(long id, String username, String password, Uloga uloga) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.uloga = uloga;
	}

	public Korisnik() {
		// TODO Auto-generated constructor stub
	}

	public boolean isAdmin() {
		if (this.getUloga().getUloga().equals("admin")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isUser() {
		if (this.getUloga().getUloga().equals("user")) {
			return true;
		} else {
			return false;
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Uloga getUloga() {
		return uloga;
	}

	public void setUloga(Uloga uloga) {
		this.uloga = uloga;
	}

	@Override
	public String toString() {
		return "Korisnik [id=" + id + ", username=" + username + ", password="
				+ password + ", uloga=" + uloga + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Korisnik other = (Korisnik) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
