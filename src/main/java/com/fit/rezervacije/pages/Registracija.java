/**
 * 
 */
package com.fit.rezervacije.pages;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transaction;

import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.fit.rezervacije.entities.Korisnik;
import com.fit.rezervacije.entities.Uloga;

/**
 * @author NaMi92
 *
 */
public class Registracija {

	@Property
	private Korisnik k;

	@Property
	private String password2;

	@Property
	private String odabranaUloga;

	@Inject
	private Session session;
	
	@Inject
    private AlertManager alertManager;

	public void onActivate() {
		k = new Korisnik();
	}

	public List<String> getUloge() {
		List<String> l = new ArrayList<String>();
		List<Uloga> a = session.createCriteria(Uloga.class).list();
		
		for (int i = 0; i < a.size(); i++) {
			l.add(a.get(i).getUloga());
		}
		
		return l;
	}

	@CommitAfter
	public void onSubmit() {
		
		Korisnik korIzBaze = (Korisnik) session.createCriteria(Korisnik.class)
				.add(Restrictions.eq("username", k.getUsername())).uniqueResult();
		
		if (korIzBaze != null) {
			alertManager.error("korisnik sa username-om: "+k.getUsername() + " vec postoji u bazi");
			return;
		}
		
		if (!k.getPassword().equals(password2)) {
			alertManager.error("passwordi se ne poklapaju" );
			return;
		}
		
		Uloga u = (Uloga) session.createCriteria(Uloga.class)
				.add(Restrictions.eq("uloga", odabranaUloga)).uniqueResult();
		
		k.setUloga(u);
		
		session.save(k);
		alertManager.info("uspjesno ste dodali korisnika" );

	}

}
