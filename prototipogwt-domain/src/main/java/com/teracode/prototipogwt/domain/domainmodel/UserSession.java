package com.teracode.prototipogwt.domain.domainmodel;


/**
 * @generated
 */
@javax.xml.bind.annotation.XmlRootElement
@org.hibernate.annotations.Where(clause = "active=\'S\'")
@javax.persistence.Entity
public class UserSession extends EntityBase implements java.io.Serializable {
	/**
	 * @generated
	 */
	private static final long serialVersionUID = -1923716758L;

	/**
	 * @generated
	 */
	@javax.persistence.ManyToOne(optional = false,fetch = javax.persistence.FetchType.LAZY)
	@javax.xml.bind.annotation.XmlTransient
	private User user;
 
	/**
	 * @generated
	 */
	private String device;

	/**
	 * @generated
	 */
	private String token;

	/**
	 * @generated
	 */
	public String toString() {
		return "UserSession" + " device=" + device + " token=" + token;
	}

	/**
	 * @generated
	 */
	public UserSession() {
	}

	/**
	 * @generated
	 */
	public User getUser() {
		return this.user;
	}

	/**
	 * @generated
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @generated
	 */
	public String getDevice() {
		return this.device;
	}

	/**
	 * @generated
	 */
	public void setDevice(String device) {
		this.device = device;
	}

	/**
	 * @generated
	 */
	public String getToken() {
		return this.token;
	}

	/**
	 * @generated
	 */
	public void setToken(String token) {
		this.token = token;
	}
}