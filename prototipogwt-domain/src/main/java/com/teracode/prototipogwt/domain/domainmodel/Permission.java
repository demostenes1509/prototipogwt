package com.teracode.prototipogwt.domain.domainmodel;


/**
 * @generated
 */
@org.hibernate.annotations.Where(clause = "active=\'S\'")
@javax.xml.bind.annotation.XmlRootElement
@javax.persistence.Entity
public class Permission extends EntityBase implements java.io.Serializable {
	/**
	 * @generated
	 */
	private static final long serialVersionUID = 890052816L;

	/**
	 * @generated
	 */
	@javax.persistence.OneToMany(mappedBy = "permission")
	private java.util.Set<RolePermission> rolePermission = new java.util.HashSet<RolePermission>();

	/**
	 * @generated
	 */
	private String clazz;

	/**
	 * @generated
	 */
	private String method;

	/**
	 * @generated
	 */
	public Permission() {
	}

	/**
	 * @generated
	 */
	public String toString() {
		return "Permission" + " clazz=" + clazz + " method=" + method;
	}

	/**
	 * @generated
	 */
	public java.util.Set<RolePermission> getRolePermission() {
		return this.rolePermission;
	}

	/**
	 * @generated
	 */
	public void setRolePermission(java.util.Set<RolePermission> rolePermission) {
		this.rolePermission = rolePermission;
	}

	/**
	 * @generated
	 */
	public void addRolePermission(RolePermission rolePermission) {
		getRolePermission().add(rolePermission);
		rolePermission.setPermission(this);
	}

	/**
	 * @generated
	 */
	public void removeRolePermission(RolePermission rolePermission) {
		getRolePermission().remove(rolePermission);
		rolePermission.setPermission(null);
	}

	/**
	 * @generated
	 */
	public String getClazz() {
		return this.clazz;
	}

	/**
	 * @generated
	 */
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	/**
	 * @generated
	 */
	public String getMethod() {
		return this.method;
	}

	/**
	 * @generated
	 */
	public void setMethod(String method) {
		this.method = method;
	}
}