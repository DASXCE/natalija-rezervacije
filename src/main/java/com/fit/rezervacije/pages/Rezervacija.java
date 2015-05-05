/**
 * 
 */
package com.fit.rezervacije.pages;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.fit.rezervacije.entities.Korisnik;
import com.fit.rezervacije.entities.Let;


public class Rezervacija {

	@Property
	private Let let;

	@Property
	private Date polazakOd;

	@Property
	private Date polazakDo;

	@Property
	@Persist
	private List<Let> letovi;

	@Inject
	private Session session;
	
	@SessionState
	private Korisnik ulogovani;

	@Property
	private boolean ulogovaniExists;

	public void onActivate() {

		let = new Let();
		let.setVrijemePolaska(getStartOfDay());
		let.setVrijemeDolaska(getStartOfDay());
	}

	public Date getStartOfDay() {
		Date d = new Date();
		d.setHours(0);
		d.setMinutes(0);
		d.setSeconds(0);
		return d;
	}
	
	public boolean isUser() {
		if (ulogovaniExists && ulogovani.isUser()) {
			return true;
		} else {
			return false;
		}
	}

	@InjectPage
	private KreirajRezervaciju kreirajRezervaciju;
	
	public Object onActionFromRezervisi(Let l) {

		kreirajRezervaciju.setOdabraniLet(l);
		return kreirajRezervaciju;
	}
	
	@InjectPage
	private LetInfo letInfo;
	
	public Object onActionFromInfo(Let l){
		letInfo.setOdabraniLet(l);
		return letInfo;
	}

	public Object onSubmitFromPretraga() {
		List<Let> l = session.createCriteria(Let.class).list();

		if ((this.let.getPolaziste() == null || this.let.getPolaziste()
				.isEmpty())
				&& (this.let.getOdrediste() == null || this.let.getOdrediste()
						.isEmpty())) {
			this.letovi = l;
			return null;
		}

		// ako nije stavljen datum onda vrati sve za od do
		if (polazakOd == null && polazakDo == null) {
			this.letovi = session.createCriteria(Let.class)
					.add(Restrictions.eq("polaziste", this.let.getPolaziste()))
					.add(Restrictions.eq("odrediste", this.let.getOdrediste()))
					.list();
			return null;
		}
		List<Let> pronadjeni = new ArrayList<Let>();

		// stavljamo polazaDo na kraj dana jer je po defaultu pocetak dana:
		// 0h0m0s, sto je problem ako se trazi za danas, jer ce if da pita da li
		// postoji let izmedju 0h0m0s i 0h0m0s.
		polazakDo.setHours(23);
		polazakDo.setMinutes(59);
		polazakDo.setSeconds(59);
		for (Let let : l) {
			if (let.getPolaziste().equals(this.let.getPolaziste())
					&& let.getOdrediste().equals(this.let.getOdrediste())
					&& (let.getVrijemePolaska().getTime() >= polazakOd
							.getTime())
					&& (let.getVrijemePolaska().getTime() <= polazakDo
							.getTime())) {
				pronadjeni.add(let);
			}
		}
		this.letovi = pronadjeni;
		return null;
	}
}