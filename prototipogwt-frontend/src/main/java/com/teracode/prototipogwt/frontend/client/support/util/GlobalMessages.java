package com.teracode.prototipogwt.frontend.client.support.util;

import static com.google.gwt.query.client.GQuery.$;

import javax.validation.ConstraintViolation;

/**
 * @author LaNzA
 * Displays all the errors in the <span id="globalMessages"></span> defined.
 */
public class GlobalMessages implements ValidatorType {
		
		static GlobalMessages instance;
		
		public GlobalMessages() {
			$("#globalMessages").html("");
		}
		
		public static GlobalMessages getInstance(){
			if (instance==null) {
				return new GlobalMessages();
			} else {
				return instance;
			}
		}
		
		/**
		 * @param violation (the constraint violations)
		 */
		public void displayErrors(ConstraintViolation<Object> violation) {
			$("#globalMessages").append("<span class='error'>"+violation.getMessage()+"</span>");
		}
	}
