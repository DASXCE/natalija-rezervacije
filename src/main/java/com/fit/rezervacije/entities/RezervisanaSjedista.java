package com.fit.rezervacije.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class RezervisanaSjedista {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	private Let let;

	@ManyToOne
	private Sjediste sjediste;

	@ManyToOne
	private Putnik putnik;

	public RezervisanaSjedista() {
		// TODO Auto-generated constructor stub
	}

	public RezervisanaSjedista(Let let, Sjediste sjediste) {
		super();
		this.let = let;
		this.sjediste = sjediste;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Let getLet() {
		return let;
	}

	public void setLet(Let let) {
		this.let = let;
	}

	public Sjediste getSjediste() {
		return sjediste;
	}

	public void setSjediste(Sjediste sjediste) {
		this.sjediste = sjediste;
	}

	public Putnik getPutnik() {
		return putnik;
	}

	public void setPutnik(Putnik putnik) {
		this.putnik = putnik;
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
		RezervisanaSjedista other = (RezervisanaSjedista) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
