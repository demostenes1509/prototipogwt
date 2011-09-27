package com.teracode.prototipogwt.domain.usecasesdto;

import java.io.Serializable;

@javax.xml.bind.annotation.XmlRootElement
@javax.xml.bind.annotation.XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class LoginResponse implements Serializable {

	private static final long serialVersionUID = -4383292883062195806L;
	
	/** 
	 * The session token response. This is necessary because User / UserSession operations may change the session!
	 */
	private String sessionToken;
	
	private Boolean isNewDevice;
	
	/**
	 * Required by RestEasy.
	 */
	public LoginResponse() {}
	
	/**
	 * @param sessionToken
	 */
	public LoginResponse(String sessionToken,Boolean isNewDevice) {
		this.sessionToken = sessionToken;
		this.isNewDevice = isNewDevice;
	}

	/**
	 * @return the sessionToken
	 */
	public String getSessionToken() {
		return sessionToken;
	}

	/**
	 * @param sessionToken the sessionToken to set
	 */
	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}

	/**
	 * @return the isNewDevice
	 */
	public Boolean getIsNewDevice() {
		return isNewDevice;
	}

	/**
	 * @param isNewDevice the isNewDevice to set
	 */
	public void setIsNewDevice(Boolean isNewDevice) {
		this.isNewDevice = isNewDevice;
	}
}
