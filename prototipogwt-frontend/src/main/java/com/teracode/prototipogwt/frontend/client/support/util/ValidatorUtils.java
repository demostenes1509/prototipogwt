package com.teracode.prototipogwt.frontend.client.support.util;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ValidatorFactory;

/**
 * @author LaNzA
 *
 */
public class ValidatorUtils {

	ValidatorType validatorType;
	
	
	/**
	 * Receives the type of the error messages should be shown.
	 * @param validatorType
	 */
	public ValidatorUtils(ValidatorType validatorType) {
		this.validatorType = validatorType;
	}
	
	
	/**
	 * @param validatorFactory
	 * @param request
	 * @return
	 */
	public boolean validate(final ValidatorFactory validatorFactory, Object request) {
		Set<ConstraintViolation<Object>> validations = validatorFactory.getValidator().validate(request);
		for(ConstraintViolation<Object> violation : validations) {
			validatorType.displayErrors(violation);
		}
		return validations.size()==0;
	}
	
}
