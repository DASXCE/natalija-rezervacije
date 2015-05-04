package com.fit.rezervacije.components;

import org.apache.tapestry5.*;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.*;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.SymbolConstants;

import com.fit.rezervacije.entities.Korisnik;
import com.fit.rezervacije.pages.Index;

/**
 * Layout component for pages of application rezervacije.
 */
@Import(stylesheet = "context:layout/layout.css")
public class Layout
{
    /**
     * The page title, for the <title> element and the <h1> element.
     */
    @Property
    @Parameter(required = true, defaultPrefix = BindingConstants.LITERAL)
    private String title;

    @Property
    private String pageName;

    @Property
    @Parameter(defaultPrefix = BindingConstants.LITERAL)
    private String sidebarTitle;

    @Property
    @Parameter(defaultPrefix = BindingConstants.LITERAL)
    private Block sidebar;

    @Inject
    private ComponentResources resources;

    @Property
    @Inject
    @Symbol(SymbolConstants.APPLICATION_VERSION)
    private String appVersion;
    
    @SessionState 
    @Property
    private Korisnik korisnik;
    
    @Property
    private boolean korisnikExists;


    public String getClassForPageName()
    {
        return resources.getPageName().equalsIgnoreCase(pageName)
                ? "current_page_item"
                : null;
    }
    
    public Object onActionFromLogout(){
    	korisnik = null;
    	return Index.class;
    }

    public String[] getPageNames()
    {
        return new String[]{"Index", "About", "Contact"};
    }
}
