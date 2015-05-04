package com.fit.rezervacije.pages;

import java.util.Date;

import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.internal.services.FlashPersistentFieldStrategy;
import org.apache.tapestry5.ioc.annotations.*;
import org.apache.tapestry5.corelib.components.*;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.alerts.AlertManager;
import org.webbitserver.netty.FlashPolicyFileHandler;

import com.fit.rezervacije.entities.Korisnik;

/**
 * Start page of application rezervacije.
 */
public class Index
{
    @Property
    @Inject
    @Symbol(SymbolConstants.TAPESTRY_VERSION)
    private String tapestryVersion;

    @InjectComponent
    private Zone zone;

    @Persist
    @Property
    private int clickCount;

    @Inject
    private AlertManager alertManager;
    
    @Persist
    private String poruka;
    
    public Date getCurrentTime()
    {
        return new Date();
    }

    void onActionFromIncrement()
    {
        alertManager.info("Increment clicked");

        clickCount++;
    }

    Object onActionFromIncrementAjax()
    {
        clickCount++;

        alertManager.info("Increment (via Ajax) clicked");

        return zone;
    }

	public String getPoruka() {
		return poruka;
	}

	public void setPoruka(String poruka) {
		this.poruka = poruka;
	}
    
	
	@SessionState
	private Korisnik ulogovani;

	@Property
	private boolean ulogovaniExists;
	
	public boolean isGuest(){
		if (ulogovaniExists) {
			return false;
		}else {
			return true;
		}
	}
	
	public boolean isAdmin() {
		if (ulogovaniExists && ulogovani.isAdmin()) {
			return true;
		} else {
			return false;
		}
	}
    
}
