package com.teracode.prototipogwt.frontend.client.support.module;

import com.google.gwt.event.shared.ResettableEventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.inject.Provider;

/**
 * @author Maxi
 *
 */
public class ResettableEventBusProvider implements Provider<ResettableEventBus> {
	
	/* (non-Javadoc)
	 * @see javax.inject.Provider#get()
	 */
	@Override
	public ResettableEventBus get() {
		return new ResettableEventBus(new SimpleEventBus());
	}
}
