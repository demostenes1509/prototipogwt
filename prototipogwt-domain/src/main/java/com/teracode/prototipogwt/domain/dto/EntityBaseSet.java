package com.teracode.prototipogwt.domain.dto;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Maxi
 */
public interface EntityBaseSet<E> extends Serializable {
	
    public Set<E> getItem();
    
    public void setItem(Set<E> set);

}
