/**
 * 
 */
package com.fit.rezervacije.pages;

import javax.inject.Inject;

import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.fit.rezervacije.entities.Korisnik;
import com.fit.rezervacije.entities.Uloga;

/**
 * @author NaMi92
 *
 */
public class Login {

	@Property
	private Korisnik k;

	@SessionState
	private Korisnik ulogovani;

	@Inject
	private Session session;

	@Inject
	private AlertManager alertManager;

	@InjectPage
	private Index index;

	@SessionState
	private Korisnik korisnik;

	public void onActivate() {
		k = new Korisnik();
	}

	@CommitAfter
	public Object onSubmit() {

		Korisnik korIzBaze = (Korisnik) session.createCriteria(Korisnik.class)
				.add(Restrictions.eq("username", k.getUsername()))
				.uniqueResult();

		if (korIzBaze == null) {
			alertManager.error("korisnik sa username-om: " + k.getUsername()
					+ " ne postoji u bazi");
			return null;
		}

		if (!korIzBaze.getPassword().equals(k.getPassword())) {
			alertManager.error("passwordi se ne poklapaju");
			return null;
		}

		ulogovani = korIzBaze;
		index.setPoruka("uspjesno ste se logovali");
		return index;
		// return Index.class;
	}
}