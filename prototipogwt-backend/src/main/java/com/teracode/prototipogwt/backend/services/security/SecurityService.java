package com.teracode.prototipogwt.backend.services.security;

import org.jboss.resteasy.core.ResourceMethod;

/**
 * @author Maxi
 */
public interface SecurityService {

	/**
	 * Check whether there is a {@link UserSession} with the given token, with access to the method.
	 */
	boolean hasPermissions(String token, ResourceMethod method);

}