package com.teracode.prototipogwt.domain.usecasesdto;

import java.io.Serializable;
import javax.validation.constraints.NotNull;


@javax.xml.bind.annotation.XmlRootElement
@javax.xml.bind.annotation.XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
@org.hibernate.annotations.Where(clause = "active=\'S\'")
public class LoginRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2804938992651571983L;
	
	@NotNull(message="{loginrequest.email.notnull}")
	private String email;
	
	private String device;
	
	@NotNull(message="{loginrequest.password.notnull}")
	private String password;
	
	/**
	 * Required by RestEasy.
	 */
	public LoginRequest() {}
	
	/**
	 * @param email
	 * @param device
	 * @param oauthToken The OAuth token for Facebook
	 */
	public LoginRequest(String email, String password, String device) {

		this.email      	= email;
		this.device     	= device;
		this.password     	= password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the device
	 */
	public String getDevice() {
		return device;
	}

	/**
	 * @param device the device to set
	 */
	public void setDevice(String device) {
		this.device = device;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
