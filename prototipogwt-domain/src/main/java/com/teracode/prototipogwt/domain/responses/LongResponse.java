package com.teracode.prototipogwt.domain.responses;

import java.io.Serializable;

@javax.xml.bind.annotation.XmlRootElement
@javax.xml.bind.annotation.XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class LongResponse implements Serializable {

	private static final long serialVersionUID = -633152701536500454L;
	
	private long value;
	
	/**
	 * Required by RestEasy
	 */
	public LongResponse() {}
	
	public LongResponse(Long value) {
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public long getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(long value) {
		this.value = value;
	}
	
	

}
