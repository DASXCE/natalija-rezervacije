/**
 * 
 */
package com.fit.rezervacije.pages;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.fit.rezervacije.entities.AvioKarta;
import com.fit.rezervacije.entities.Let;
import com.fit.rezervacije.entities.Putnik;
import com.fit.rezervacije.entities.RezervisanaSjedista;
import com.fit.rezervacije.entities.Sjediste;
import com.fit.rezervacije.entities.Sjediste.KlasaSjedista;

public class KreirajRezervaciju {

	@Property
	@Persist
	private KlasaSjedista klasa;

	@Persist
	private Let odabraniLet;

	@Property
	private Putnik putnik = new Putnik();

	@Inject
	private Session session;

	@Inject
	private AlertManager alert;

	@CommitAfter
	public Object onSuccess() {
		List<Sjediste> sjedista = session.createCriteria(Sjediste.class)
				.add(Restrictions.eq("avion", odabraniLet.getAvion()))
				.add(Restrictions.eq("klasa", klasa)).list();

		boolean dodao = false;

		for (Sjediste s : sjedista) {
			RezervisanaSjedista rsj = (RezervisanaSjedista) session
					.createCriteria(RezervisanaSjedista.class)
					.add(Restrictions.eq("let", odabraniLet))
					.add(Restrictions.eq("sjediste", s)).uniqueResult();
			if (rsj == null) {
				RezervisanaSjedista rsnew = new RezervisanaSjedista(
						odabraniLet, s);
				rsnew.setPutnik(putnik);
				session.save(rsnew);
				dodao = true;
				break;
			}
		}

		if (dodao == false) {
			alert.error("nema slobodnih mjesta za klasu: " + klasa);
			return null;
		}
		session.save(putnik);
		Date d = new Date();
		System.out.println(d.toString());
		AvioKarta karta = new AvioKarta(odabraniLet, putnik, d);
		session.save(karta);
		alert.info("uspjesno ste rezervisali kartu");
		return Rezervacija.class;
	}

	public KlasaSjedista getPrva() {
		return KlasaSjedista.PRVA_KLASA;
	}

	public KlasaSjedista getBiznis() {
		return KlasaSjedista.BIZNIS_KLASA;
	}

	public KlasaSjedista getEkonomska() {
		return KlasaSjedista.EKONOMSKA_KLASA;
	}

	public Let getOdabraniLet() {
		return odabraniLet;
	}

	public void setOdabraniLet(Let odabraniLet) {
		this.odabraniLet = odabraniLet;
	}

}