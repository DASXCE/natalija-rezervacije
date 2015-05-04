/**
 * 
 */
package com.fit.rezervacije.pages;

import javax.inject.Inject;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.hibernate.Session;

import com.fit.rezervacije.entities.Avion;
import com.fit.rezervacije.entities.Sjediste;
import com.fit.rezervacije.entities.Sjediste.KlasaSjedista;

/**
 * @author NaMi92
 *
 */
public class DodavanjeAviona {

	@Property
	private Avion avion = new Avion();

	@Inject
	private Session session;

	@CommitAfter
	public void onSuccess() {
		for (int i = 0; i < avion.getBrojSjedistaPrveKlase(); i++) {
			Sjediste s = new Sjediste(avion, avion.getOznaka() + "PK-"
					+ (i + 1), KlasaSjedista.PRVA_KLASA);
			session.save(s);
		}

		for (int i = 0; i < avion.getBrojSjedistaBiznisKlase(); i++) {
			Sjediste s = new Sjediste(avion, avion.getOznaka() + "BK-"
					+ (i + 1), KlasaSjedista.BIZNIS_KLASA);
			session.save(s);
		}

		for (int i = 0; i < avion.getBrojSjedistaEkonomskeKlase(); i++) {
			Sjediste s = new Sjediste(avion, avion.getOznaka() + "EK-"
					+ (i + 1), KlasaSjedista.EKONOMSKA_KLASA);
			session.save(s);
		}
		session.save(avion);
	}

}