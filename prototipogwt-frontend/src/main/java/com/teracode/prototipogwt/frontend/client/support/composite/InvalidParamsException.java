/**
 * 
 */
package com.teracode.prototipogwt.frontend.client.support.composite;

/**
 * @author Maxi
 */
public class InvalidParamsException extends Exception {

	private static final long serialVersionUID = -8584239641947945802L;

	public InvalidParamsException() {}

	/**
	 * @param message
	 */
	public InvalidParamsException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public InvalidParamsException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public InvalidParamsException(String message, Throwable cause) {
		super(message, cause);
	}

}
