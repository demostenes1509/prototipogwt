package com.teracode.prototipogwt.domain.dto;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.teracode.prototipogwt.domain.domainmodel.Role;

/**
 * @author Maxi
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class RoleSet implements EntityBaseSet<Role> {

	private static final long serialVersionUID = -8117722605063662318L;

    protected Set<Role> item = new HashSet<Role>();
	
	public RoleSet() {}

    public Set<Role> getItem() { return this.item; }

	public void setItem(Set<Role> item) { this.item = item; }
}
    
    

