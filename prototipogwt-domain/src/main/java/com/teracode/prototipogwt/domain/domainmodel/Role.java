package com.teracode.prototipogwt.domain.domainmodel;



/**
 * @generated
 */
@org.hibernate.annotations.Where(clause = "active=\'S\'")
@javax.xml.bind.annotation.XmlRootElement
@javax.persistence.Entity
public class Role extends EntityBase implements java.io.Serializable {
	/**
	 * @generated
	 */
	private static final long serialVersionUID = 1064923191L;

	/**
	 * @generated
	 */
	@javax.persistence.OneToMany(mappedBy = "role")
	@javax.xml.bind.annotation.XmlTransient
	private java.util.Set<User> user = new java.util.HashSet<User>();

	/**
	 * @generated
	 */
	@javax.persistence.OneToMany(mappedBy = "role")
	private java.util.Set<RolePermission> rolePermission = new java.util.HashSet<RolePermission>();

	/**
	 * @generated
	 */
	private String name;
	
	/**
	 * @generated
	 */
	public Role() {
	}

	/**
	 * @generated
	 */
	public String toString() {
		return "Role" + " name=" + name+", id="+getId();
	}

	/**
	 * @generated
	 */
	public java.util.Set<User> getUser() {
		return this.user;
	}

	/**
	 * @generated
	 */
	public void setUser(java.util.Set<User> user) {
		this.user = user;
	}

	/**
	 * @generated
	 */
	public void addUser(User user) {
		getUser().add(user);
		user.setRole(this);
	}

	/**
	 * @generated
	 */
	public void removeUser(User user) {
		getUser().remove(user);
		user.setRole(null);
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
		rolePermission.setRole(this);
	}

	/**
	 * @generated
	 */
	public void removeRolePermission(RolePermission rolePermission) {
		getRolePermission().remove(rolePermission);
		rolePermission.setRole(null);
	}

	/**
	 * @generated
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @generated
	 */
	public void setName(String name) {
		this.name = name;
	}
}