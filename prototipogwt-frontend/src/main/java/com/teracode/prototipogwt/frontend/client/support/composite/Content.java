package com.teracode.prototipogwt.frontend.client.support.composite;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.teracode.prototipogwt.frontend.client.pages.template.BasePage;

/**
 * Base class for all composites (pages) which can track the URL history.
 * 
 * @author Maxi
 * @author andran
 */
public abstract class Content extends Composite {
	
	private BasePage basePage;
	
	/**
	 * @param basePage
	 */
	public Content(BasePage basePage) {
		this.basePage = basePage;
	}

	/**
	 * @param params URL parameters.
	 */
	@SuppressWarnings("deprecation")
	public final void showCurrentPage(Map<String, String> params) throws InvalidParamsException {

		
		// Decode parameters
		Map<String,String> decodedParams = new HashMap<String,String>();
		for(Map.Entry<String, String> entry:params.entrySet()) {
			decodedParams.put(URL.decodeComponent(entry.getKey()), URL.decodeComponent(entry.getValue()));
		}
		
		// The subclassed page uses the parameters
		basePage.setCenter(getElement());
		basePage.setParameters(decodedParams);
		setParameters(decodedParams);
		RootPanel.get().add( basePage );
	}
	
	/**
	 * Method called when setting URL parameters for this Composite.<br>
	 * To be implemented by subclasses.
	 * 
	 * @param params URL parameters.
	 */
	public abstract void setParameters(Map<String, String> params) throws InvalidParamsException;

}
