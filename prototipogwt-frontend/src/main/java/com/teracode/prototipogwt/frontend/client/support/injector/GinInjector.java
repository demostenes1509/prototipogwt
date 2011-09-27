package com.teracode.prototipogwt.frontend.client.support.injector;

import com.google.gwt.event.shared.ResettableEventBus;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.teracode.prototipogwt.frontend.client.pages.LoginContent;
import com.teracode.prototipogwt.frontend.client.support.module.GinModule;

/**
 * @author Maxi 
 */
@GinModules(GinModule.class)
public interface GinInjector extends Ginjector {
	LoginContent getLoginContent();
	ResettableEventBus getResettableEventBus();
}
