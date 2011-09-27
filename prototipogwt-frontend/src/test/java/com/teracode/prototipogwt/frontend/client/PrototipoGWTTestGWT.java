package com.teracode.prototipogwt.frontend.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.teracode.prototipogwt.frontend.client.support.injector.GinInjector;

/**
 * @author Maxi
 *
 */
public abstract class PrototipoGWTTestGWT extends GWTTestCase {
	
	private static GinInjector ginInjector;

    /* (non-Javadoc)
     * @see com.google.gwt.junit.client.GWTTestCase#getModuleName()
     */
    public String getModuleName() {
        return "com.teracode.prototipogwt.frontend.prototipogwt";
    }
    
	/**
     * 
     */
    public GinInjector getInjector() {
    	if(ginInjector==null) {
    		ginInjector = GWT.create(GinInjector.class);
    	}
    	return ginInjector;
    }
}