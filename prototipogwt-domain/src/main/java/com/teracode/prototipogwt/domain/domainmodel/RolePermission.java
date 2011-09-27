package com.teracode.prototipogwt.domain.domainmodel;


/**
 * @generated
 */
@javax.persistence.Entity
@org.hibernate.annotations.Where(clause = "active=\'S\'")
@javax.xml.bind.annotation.XmlRootElement
public class RolePermission extends EntityBase implements java.io.Serializable {
	/**
	 * @generated
	 */
	@javax.persistence.ManyToOne(optional = false,fetch = javax.persistence.FetchType.LAZY)
	@javax.xml.bind.annotation.XmlTransient
	private Role role;
	/**
	 * @generated
	 */
	@javax.persistence.ManyToOne(optional = false,fetch = javax.persistence.FetchType.LAZY)
	@javax.xml.bind.annotation.XmlTransient
	private Permission permission;

	/**
	 * @generated
	 */
	private static final long serialVersionUID = 1602713958L;
	/**
	 * @generated
	 */
	public RolePermission() {
	}

	/**
	 * @generated
	 */
	public String toString() {
		return "RolePermission";
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
	public Permission getPermission() {
		return this.permission;
	}

	/**
	 * @generated
	 */
	public void setPermission(Permission permission) {
		this.permission = permission;
	}
}