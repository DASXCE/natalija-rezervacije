package com.fit.rezervacije.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.apache.tapestry5.beaneditor.NonVisual;

@Entity
public class Putnik {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@NonVisual
	private long id;
	
	private String brojPasosa;
	
	private String ime;
	
	private String prezime;
	
	private Date datumRodjenja;
	
	public Putnik(String brojPasosa, String ime, String prezime,
			Date datumRodjenja) {
		super();
		this.brojPasosa = brojPasosa;
		this.ime = ime;
		this.prezime = prezime;
		this.datumRodjenja = datumRodjenja;
	}

	public Putnik() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBrojPasosa() {
		return brojPasosa;
	}

	public void setBrojPasosa(String brojPasosa) {
		this.brojPasosa = brojPasosa;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public Date getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
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
		Putnik other = (Putnik) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Putnik [id=" + id + ", brojPasosa=" + brojPasosa + ", ime="
				+ ime + ", prezime=" + prezime + ", datumRodjenja="
				+ datumRodjenja + "]";
	}
	
	
	
}
