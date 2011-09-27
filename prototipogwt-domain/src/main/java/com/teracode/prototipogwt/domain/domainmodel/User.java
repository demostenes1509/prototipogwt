package com.teracode.prototipogwt.domain.domainmodel;

import org.hibernate.annotations.Parameter;
import org.jasypt.hibernate.type.EncryptedStringType;



/**
 * @generated
 */
@javax.xml.bind.annotation.XmlRootElement
@org.hibernate.annotations.Where(clause = "active=\'S\'")
@javax.persistence.Entity
@org.hibernate.annotations.TypeDef(name = "encryptedString", typeClass = EncryptedStringType.class, parameters = {@Parameter(name="encryptorRegisteredName", value="hibernateStringEncryptor")})
public class User extends EntityBase implements java.io.Serializable {
	/**
	 * @generated
	 */
	private static final long serialVersionUID = 1065016204L;

	/**
	 * @generated
	 */
	@javax.persistence.ManyToOne(optional = false,fetch = javax.persistence.FetchType.LAZY)
	private Role role;

	/**
	 * @generated
	 */
	private String email;

	/**
	 * @generated
	 */
	@org.hibernate.annotations.Type(type = "encryptedString")
	@javax.xml.bind.annotation.XmlTransient
	private String password;

	/**
	 * @generated
	 */
	@javax.persistence.OneToMany(mappedBy = "user")
	private java.util.Set<UserSession> userSession = new java.util.HashSet<UserSession>();
	
	/**
	 * @generated
	 */
	public User() {
	}

	/**
	 * @generated
	 */
	public String toString() {
		return "User" + " email=" + email + " password=" + password;
	}

	/**
	 * @generated
	 */
	public Role getRole() {
		return this.role;
	}

	/**
	 * @generated
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * @generated
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * @generated
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @generated
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * @generated
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @generated
	 */
	public java.util.Set<UserSession> getUserSession() {
		return this.userSession;
	}

	/**
	 * @generated
	 */
	public void setUserSession(java.util.Set<UserSession> userSession) {
		this.userSession = userSession;
	}

	/**
	 * @generated
	 */
	public void addUserSession(UserSession userSession) {
		getUserSession().add(userSession);
		userSession.setUser(this);
	}

	/**
	 * @generated
	 */
	public void removeUserSession(UserSession userSession) {
		getUserSession().remove(userSession);
		userSession.setUser(null);
	}
}