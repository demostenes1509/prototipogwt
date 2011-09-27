package com.teracode.prototipogwt.frontend.client.support.module;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import com.google.inject.Provider;

/**
 * @author Maxi
 *
 */
public class ValidatorFactoryProvider implements Provider<ValidatorFactory> {
	
	/* (non-Javadoc)
	 * @see javax.inject.Provider#get()
	 */
	@Override
	public ValidatorFactory get() {
		return Validation.byDefaultProvider().configure().buildValidatorFactory();
	}
}
