package com.fit.rezervacije.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class AvioKarta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	private Let let;

	@ManyToOne
	private Putnik putnik;

	private Date datumRezervacije;

	public AvioKarta() {
	}

	public AvioKarta(Let let, Putnik putnik, Date datumRezervacije) {
		super();
		this.let = let;
		this.putnik = putnik;
		this.datumRezervacije = datumRezervacije;
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

	public Date getDatumRezervacije() {
		return datumRezervacije;
	}

	public void setDatumRezervacije(Date datumRezervacije) {
		this.datumRezervacije = datumRezervacije;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AvioKarta other = (AvioKarta) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AvioKarta [id=" + id + ", let=" + let + ", putnik=" + putnik
				+ "]";
	}

}
