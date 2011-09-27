package com.teracode.prototipogwt.frontend.client.support.util;

import static com.google.gwt.query.client.GQuery.$;

import javax.validation.ConstraintViolation;

/**
 * @author LaNzA
 * Displays the error in each spcefic field that has the <span id="name+Error"></span> defined.
 */
public class SpecificField implements ValidatorType {
		
		static SpecificField instance;
		
		public SpecificField() {}
		
		public static SpecificField getInstance(){
			if (instance==null) {
				return new SpecificField();
			} else {
				return instance;
			}
		}
		
		/**
		 * Displays the error in each spcefic field that has the <span id="name+Error"></span> defined.
		 * @param violation (the constraint violations)
		 */
		public void displayErrors(ConstraintViolation<Object> violation) {
			$("#"+violation.getPropertyPath().toString()+"Error").html("");
			$("#"+violation.getPropertyPath().toString()+"Error").append("<span class='error'>"+violation.getMessage()+"</span>");
		}
	}
