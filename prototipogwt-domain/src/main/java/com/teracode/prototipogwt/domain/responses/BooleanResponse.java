package com.teracode.prototipogwt.domain.responses;

import java.io.Serializable;

@javax.xml.bind.annotation.XmlRootElement
@javax.xml.bind.annotation.XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class BooleanResponse implements Serializable {

	private static final long serialVersionUID = -633152701536500454L;
	
	private boolean value;
	
	/**
	 * Required by RestEasy
	 */
	public BooleanResponse() {}
	
	public BooleanResponse(boolean value) {
		this.value = value;
	}

	public boolean isValue() { return value; }

	public void setValue(boolean value) { this.value = value; }
}
