/**
 * 
 */
package com.fit.rezervacije.pages;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.fit.rezervacije.entities.Avion;
import com.fit.rezervacije.entities.Korisnik;
import com.fit.rezervacije.entities.Let;

/**
 * @author NaMi92
 *
 */
public class DodavanjeLetova {

	@Property
	private Let let;
	
	@Property
	private Date polazakOd;

	@Property
	private Date polazakDo;

	@Property
	private String odabraniAvion;

	@Inject
	private Session session;

	@SessionState
	private Korisnik ulogovani;

	@Property
	private boolean ulogovaniExists;

	@Property
	@Persist
	private List<Let> letovi;
	
	public Object onActivate() {
		if (!ulogovaniExists) {
			return Login.class;
		}

		let = new Let();
		let.setVrijemePolaska(getStartOfDay());
		let.setVrijemeDolaska(getStartOfDay());

		if (isAdmin()) {
			this.letovi = session.createCriteria(Let.class).list();
		}
		
		return null;
	}
	
	public Date getStartOfDay(){
		Date d = new Date();
		d.setHours(0);
		d.setMinutes(0);
		d.setSeconds(0);
		return d;
	}


	public List<String> getAvioni() {
		List<String> l = new ArrayList<String>();
		List<Avion> a = session.createCriteria(Avion.class).list();

		for (int i = 0; i < a.size(); i++) {
			l.add(a.get(i).getOznaka());
		}

		return l;
	}

	public boolean isAdmin() {
		if (ulogovaniExists && ulogovani.isAdmin()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isUser() {
		if (ulogovaniExists && ulogovani.isUser()) {
			return true;
		} else {
			return false;
		}
	}

	@CommitAfter
	public void onSubmitFromNoviLet() {
		Avion a = (Avion) session.createCriteria(Avion.class)
				.add(Restrictions.eq("oznaka", odabraniAvion)).uniqueResult();
		let.setAvion(a);

		session.save(let);

	}

	public Object onSubmitFromPretraga() {
		List<Let> l = session.createCriteria(Let.class).list();

		if ((this.let.getPolaziste() == null || this.let.getPolaziste().isEmpty()) && (this.let.getOdrediste() == null || this.let.getOdrediste().isEmpty())) {
			this.letovi = l;
			return null;
		}
		
		// ako nije stavljen datum onda vrati sve za od do
		if (polazakOd == null && polazakDo == null) {
			this.letovi = session.createCriteria(Let.class)
					.add(Restrictions.eq("polaziste", this.let.getPolaziste()))
					.add(Restrictions.eq("odrediste", this.let.getOdrediste())).list();
			return null;
		}
		List<Let> pronadjeni = new ArrayList<Let>();
		
		// stavljamo polazaDo na kraj dana jer je po defaultu pocetak dana: 0h0m0s, sto je problem ako se trazi za danas, jer ce if da pita da li postoji let izmedju 0h0m0s i 0h0m0s.
		polazakDo.setHours(23);
		polazakDo.setMinutes(59);
		polazakDo.setSeconds(59);
		for (Let let : l) {
			if (let.getPolaziste().equals(this.let.getPolaziste())
					&& let.getOdrediste().equals(this.let.getOdrediste())
					&& (let.getVrijemePolaska().getTime() >= polazakOd.getTime())
					&& (let.getVrijemePolaska().getTime() <= polazakDo.getTime())) {
				pronadjeni.add(let);
			}
		}
		this.letovi = pronadjeni;
		return null;
	}
		
}