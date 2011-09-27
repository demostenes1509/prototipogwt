package com.teracode.prototipogwt.frontend.client;

import java.util.Map;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.ResettableEventBus;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.teracode.prototipogwt.frontend.client.support.composite.Content;
import com.teracode.prototipogwt.frontend.client.support.composite.InvalidParamsException;
import com.teracode.prototipogwt.frontend.client.support.injector.GinInjector;
import com.teracode.prototipogwt.frontend.client.support.util.Utils;

/**
 * @author Maximiliano Ezequiel Carrizo 
 *
 */
public class PrototipoGWT implements EntryPoint, ValueChangeHandler<String> {
	
	/**
	 * 
	 */
	public static final String LOGIN_PAGE				= "";
	
	/**
	 * 
	 */
	GinInjector ginInjector = GWT.create(GinInjector.class);

	/* (non-Javadoc)
	 * @see com.google.gwt.core.client.EntryPoint#onModuleLoad()
	 */
	public void onModuleLoad() {
		
		// TODO Auto-generated method stub
		Utils.log(this,"Obteniendo Token ...");
		String startingToken = History.getToken();
		Utils.log(this,"Token:" + startingToken);
		
		Utils.log(this,"Configurando manejo de History");
		History.addValueChangeHandler(this);
		History.fireCurrentHistoryState();
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		
		// Reseteo el maldito Event Bus como primera medida !!
		ResettableEventBus resettableEventBus = ginInjector.getResettableEventBus();
		resettableEventBus.removeHandlers();
		
		// TODO Auto-generated method stub
		
		Utils.log(this,"******************************************************************************************************************");
		Utils.log(this,"******************************************************************************************************************");
		Utils.log(this,"******************************************************************************************************************");
		Utils.log(this,"PAGINA NUEVA");
		Utils.log(this,"******************************************************************************************************************");
		Utils.log(this,"******************************************************************************************************************");
		Utils.log(this,"******************************************************************************************************************");

		String url					= event.getValue();
		String token				= Utils.getToken(url);
		Map<String,String> params	= Utils.getParams(url);
		
		Utils.log(this,"URL:" + token);
		Utils.log(this,"Params:" + params);
	
		// I remove previous composite first, unregistering event handlers in the process
		RootPanel.get().clear();
		DOM.getElementById("loadapp").setInnerHTML("");

		Content currentContent = null;
		if (token.equals(LOGIN_PAGE)) {
			Utils.log(this,"--------------------------------------------------------------------------------");
			Utils.log(this,"Levantando pagina login ....");
			Utils.log(this,"--------------------------------------------------------------------------------");
			currentContent = ginInjector.getLoginContent();
		}
	
		if (currentContent != null) {
			try {
				currentContent.showCurrentPage(params);
			} 
			catch(InvalidParamsException ipe) {
				Window.alert("Parametros de URL no validos");
			}
		
		} else {
			Window.alert("URL no valido:'");
		}
	}
}
