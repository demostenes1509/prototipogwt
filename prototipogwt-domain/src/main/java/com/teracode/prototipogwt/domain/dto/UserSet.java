package com.teracode.prototipogwt.domain.dto;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.teracode.prototipogwt.domain.domainmodel.User;

/**
 * @author Maxi
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class UserSet implements EntityBaseSet<User> {

	private static final long serialVersionUID = -8117722605063662318L;

    protected Set<User> item = new HashSet<User>();
	
	public UserSet() {}

    public Set<User> getItem() { return this.item; }
    
	public void setItem(Set<User> item) { this.item = item; }

	public UserSet(Set<User> item) { this.item = item; }
	
}
