package com.fit.rezervacije.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.apache.tapestry5.beaneditor.NonVisual;

@Entity
public class Let {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonVisual
	private long id;

	private String polaziste;

	private String odrediste;

	private Date vrijemePolaska;

	private Date vrijemeDolaska;

	@ManyToOne
	private Avion avion;

	public Let() {
	}

	public Let(long id, String polaziste, String odrediste,
			Date vrijemePolaska, Date vrijemeDolaska) {
		super();
		this.id = id;
		this.polaziste = polaziste;
		this.odrediste = odrediste;
		this.vrijemePolaska = vrijemePolaska;
		this.vrijemeDolaska = vrijemeDolaska;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPolaziste() {
		return polaziste;
	}

	public void setPolaziste(String polaziste) {
		this.polaziste = polaziste;
	}

	public String getOdrediste() {
		return odrediste;
	}

	public void setOdrediste(String odrediste) {
		this.odrediste = odrediste;
	}

	public Date getVrijemePolaska() {
		return vrijemePolaska;
	}

	public void setVrijemePolaska(Date vrijemePolaska) {
		this.vrijemePolaska = vrijemePolaska;
	}

	public Date getVrijemeDolaska() {
		return vrijemeDolaska;
	}

	public void setVrijemeDolaska(Date vrijemeDolaska) {
		this.vrijemeDolaska = vrijemeDolaska;
	}

	public Avion getAvion() {
		return avion;
	}

	public void setAvion(Avion avion) {
		this.avion = avion;
	}

	@Override
	public String toString() {
		return "Let [id=" + id + ", polaziste=" + polaziste + ", odrediste="
				+ odrediste + ", vrijemePolaska=" + vrijemePolaska
				+ ", vrijemeDolaska=" + vrijemeDolaska + "]";
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
		Let other = (Let) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
