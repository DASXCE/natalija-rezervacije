package com.fit.rezervacije.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.apache.tapestry5.beaneditor.NonVisual;

@Entity
public class Avion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonVisual
	private long id;

	@Column(unique=true)
	private String oznaka;

	private int brojSjedistaPrveKlase;

	private int brojSjedistaBiznisKlase;

	private int brojSjedistaEkonomskeKlase;

	public Avion() {
		// TODO Auto-generated constructor stub
	}

	public Avion(long id, String oznaka, int brojSjedistaPrveKlase,
			int brojSjedistaBiznisKlase, int brojSjedistaEkonomskeKlase) {
		super();
		this.id = id;
		this.oznaka = oznaka;
		this.brojSjedistaPrveKlase = brojSjedistaPrveKlase;
		this.brojSjedistaBiznisKlase = brojSjedistaBiznisKlase;
		this.brojSjedistaEkonomskeKlase = brojSjedistaEkonomskeKlase;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOznaka() {
		return oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}


	public int getBrojSjedistaPrveKlase() {
		return brojSjedistaPrveKlase;
	}

	public void setBrojSjedistaPrveKlase(int brojSjedistaPrveKlase) {
		this.brojSjedistaPrveKlase = brojSjedistaPrveKlase;
	}

	public int getBrojSjedistaBiznisKlase() {
		return brojSjedistaBiznisKlase;
	}

	public void setBrojSjedistaBiznisKlase(int brojSjedistaBiznisKlase) {
		this.brojSjedistaBiznisKlase = brojSjedistaBiznisKlase;
	}

	public int getBrojSjedistaEkonomskeKlase() {
		return brojSjedistaEkonomskeKlase;
	}

	public void setBrojSjedistaEkonomskeKlase(int brojSjedistaEkonomskeKlase) {
		this.brojSjedistaEkonomskeKlase = brojSjedistaEkonomskeKlase;
	}

	@Override
	public String toString() {
		return "Avion [id=" + id + ", oznaka=" + oznaka
				+ ", brojSjedistaAKlase=" + brojSjedistaPrveKlase
				+ ", brojSjedistaBiznisKlase=" + brojSjedistaBiznisKlase
				+ ", brojSjedistaEkonomskeKlase=" + brojSjedistaEkonomskeKlase + "]";
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
		Avion other = (Avion) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
