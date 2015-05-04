package com.fit.rezervacije.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Sjediste {

	public enum KlasaSjedista {
		PRVA_KLASA, BIZNIS_KLASA, EKONOMSKA_KLASA
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="sjediste_id")
	private long id;

	@ManyToOne
	private Avion avion;

	@Column(unique = true)
	private String brojSjedista;

	@Enumerated(EnumType.STRING)
	private KlasaSjedista klasa;

	public Sjediste() {
		// TODO Auto-generated constructor stub
	}

	public Sjediste(long id, Avion avion, String brojSjedista) {
		super();
		this.id = id;
		this.avion = avion;
		this.brojSjedista = brojSjedista;
	}

	public Sjediste(Avion avion, String brojSjedista, KlasaSjedista klasa) {
		super();
		this.avion = avion;
		this.brojSjedista = brojSjedista;
		this.klasa = klasa;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Avion getAvion() {
		return avion;
	}

	public void setAvion(Avion avion) {
		this.avion = avion;
	}

	public String getBrojSjedista() {
		return brojSjedista;
	}

	public void setBrojSjedista(String brojSjedista) {
		this.brojSjedista = brojSjedista;
	}

	public KlasaSjedista getKlasa() {
		return klasa;
	}

	public void setKlasa(KlasaSjedista klasa) {
		this.klasa = klasa;
	}

	@Override
	public String toString() {
		return "Sjediste [id=" + id + ", avion=" + avion + ", brojSjedista="
				+ brojSjedista + ", klasa=" + klasa + "]";
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
		Sjediste other = (Sjediste) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
