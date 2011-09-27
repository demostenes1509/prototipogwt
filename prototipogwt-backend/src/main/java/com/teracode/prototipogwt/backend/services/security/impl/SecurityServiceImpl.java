package com.teracode.prototipogwt.backend.services.security.impl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.jboss.resteasy.core.ResourceMethod;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teracode.prototipogwt.backend.services.security.SecurityService;
import com.teracode.prototipogwt.domain.domainmodel.Role;
import com.teracode.prototipogwt.domain.domainmodel.RolePermission;
import com.teracode.prototipogwt.domain.domainmodel.User;
import com.teracode.prototipogwt.domain.domainmodel.UserSession;

/**
 * @author Maxi
 */
@Service
@Scope(value=BeanDefinition.SCOPE_PROTOTYPE)
@Transactional
public class SecurityServiceImpl implements SecurityService {
	
	private static Logger logger = Logger.getLogger(SecurityServiceImpl.class);
		
	@PersistenceContext
	private EntityManager entityManager;

	public SecurityServiceImpl() {} 
		
	/* (non-Javadoc)
	 * @see com.citibank.citibeneficios.rest.services.SecurityService#hasPermissions(java.lang.String, org.jboss.resteasy.core.ResourceMethod)
	 */
	@Override
	public boolean hasPermissions(String token, ResourceMethod method) {

		String roleName = null;
		
		try {
		
			logger.debug("Searching token to find related user");
			UserSession userSession = (UserSession) entityManager.createQuery("select us from UserSession as us where us.token = ?")
										.setParameter(1, token)
										.getSingleResult();

			logger.debug("Getting User from UserSession");
			User user = userSession.getUser();
			
			logger.debug("Getting Role from User");
			Role role = user.getRole();
			
			roleName = role != null ? role.getName() : null;
			logger.debug("Role obtained: " + roleName);
			
			// I search for permission in User's role
			logger.info("Searching for permission. Method = " + method.getMethod().getName() + " Class:" + method.getResourceClass().getSimpleName());
			RolePermission rolePermission = (RolePermission) entityManager.createQuery("select rp from RolePermission as rp where rp.role = ? and rp.permission.method = ? and rp.permission.clazz = ?").
					setParameter(1, role).
					setParameter(2, method.getMethod().getName()).
					setParameter(3, method.getResourceClass().getSimpleName()).
					getSingleResult();
			
			logger.info("Permission Found with ID:" + rolePermission.getId());
			return true;
		
		} catch (NoResultException nre) {

			// Token not found
			String methodName = method.getMethod().getName();
			String className  = method.getResourceClass().getSimpleName();
			String errorMsg = "Error checking permissions for token " + token + "\n"
							+ "Trying to access method " + methodName + " for class " + className + (roleName != null ? " using role " + roleName : "") + "\n"
							+ "EXCEPTION: " + nre + "\n";
			
			logger.error(errorMsg);
			return false;
		
		}
	}
	
}