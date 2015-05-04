/**
 * 
 */
package com.fit.rezervacije.pages;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.regexp.recompile;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.internal.antlr.PropertyExpressionParser.list_return;
import org.apache.tapestry5.services.RelativeElementPosition;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.fit.rezervacije.entities.AvioKarta;
import com.fit.rezervacije.entities.Let;
import com.fit.rezervacije.entities.Putnik;
import com.fit.rezervacije.entities.RezervisanaSjedista;
import com.fit.rezervacije.entities.Sjediste.KlasaSjedista;

public class LetInfo {

	@Persist
	private Let odabraniLet;

	@Inject
	private Session session;

	@Property
	private int brojSPrveKlase;

	@Property
	private int brojSBiznisKlase;

	@Property
	private int brojSEkonomskeKlase;
	
	@Property
	private Putnik putnik;
	
	public void onActivate(){
		getBrojSjedista();
	}
	
	public List<Putnik> getPutnici(){
		List<AvioKarta> karte = session.createCriteria(AvioKarta.class).add(Restrictions.eq("let", odabraniLet)).list();
		
		List<Putnik> putnici = new ArrayList<>();
		for (AvioKarta avioKarta : karte) {
			putnici.add(avioKarta.getPutnik());
		}
		return putnici;
	}

	
	public void getBrojSjedista() {
		List<RezervisanaSjedista> rs = session
				.createCriteria(RezervisanaSjedista.class)
				.add(Restrictions.eq("let", odabraniLet)).list();

		for (RezervisanaSjedista rezervisanaSjedista : rs) {
			KlasaSjedista ks = rezervisanaSjedista.getSjediste().getKlasa();
			switch (ks) {
			case PRVA_KLASA:
				brojSPrveKlase++;
				break;
			case BIZNIS_KLASA:
				brojSBiznisKlase++;
				break;
			case EKONOMSKA_KLASA:
				brojSEkonomskeKlase++;
				break;
			default:
				break;
			}
		}

	}
	
	public String getSjediste(){
		RezervisanaSjedista rs =  (RezervisanaSjedista) session
				.createCriteria(RezervisanaSjedista.class)
				.add(Restrictions.eq("let", odabraniLet))
				.add(Restrictions.eq("putnik", putnik)).uniqueResult();
		return rs.getSjediste().getBrojSjedista();
	}

	public Let getOdabraniLet() {
		return odabraniLet;
	}

	public void setOdabraniLet(Let odabraniLet) {
		this.odabraniLet = odabraniLet;
	}

}