package com.teracode.prototipogwt.frontend.client.support.util;

import javax.validation.ConstraintViolation;


/**
 * @author LaNzA
 *
 */
public interface ValidatorType {
	
	/**
	 * @param violation
	 */
	public void displayErrors(ConstraintViolation<Object> violation);
}