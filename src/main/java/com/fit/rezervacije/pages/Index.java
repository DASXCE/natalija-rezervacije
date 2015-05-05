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
public class Index{

    @Inject
    private AlertManager alertManager;

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
