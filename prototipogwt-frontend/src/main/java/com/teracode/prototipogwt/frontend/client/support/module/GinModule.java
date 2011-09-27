package com.teracode.prototipogwt.frontend.client.support.module;

import javax.validation.ValidatorFactory;

import com.google.gwt.event.shared.ResettableEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author Maxi
 */
public class GinModule extends AbstractGinModule {
 
	@Override
	protected void configure() {
		bind( ResettableEventBus.class ).toProvider( ResettableEventBusProvider.class ).in( Singleton.class );		
		bind( ValidatorFactory.class ).toProvider( ValidatorFactoryProvider.class ).in( Singleton.class );		
	}
}

