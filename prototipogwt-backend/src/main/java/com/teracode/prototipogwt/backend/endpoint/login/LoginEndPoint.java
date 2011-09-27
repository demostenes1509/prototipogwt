package com.teracode.prototipogwt.backend.endpoint.login;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teracode.prototipogwt.domain.domainmodel.User;
import com.teracode.prototipogwt.domain.domainmodel.UserSession;
import com.teracode.prototipogwt.domain.usecasesdto.LoginRequest;
import com.teracode.prototipogwt.domain.usecasesdto.LoginResponse;
import com.teracode.prototipogwt.domain.util.DomainConstants;

/**
 * Use cases related to the {@link User}. Most or all of these use cases require a User to be logged-in.
 * 
 * @author andran
 */
@Path(DomainConstants.LOGIN_USE_CASE_PATH)
@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
@Service
@Scope(value=BeanDefinition.SCOPE_PROTOTYPE)
@Transactional
public class LoginEndPoint {
	
	private static Logger logger = Logger.getLogger(LoginEndPoint.class);
	
	@PersistenceContext
	private EntityManager entityManager;

	@PUT
	public LoginResponse login(LoginRequest loginRequest) {
		User user = null; 
		try {
			user=(User) entityManager.createQuery("select u from User u where u.email = ?").setParameter(1, loginRequest.getEmail()).getSingleResult();
			String password = loginRequest.getPassword();
			if(!password.equals(user.getPassword())) {
				throw new WebApplicationException(Response.Status.UNAUTHORIZED);
			}
		}
		catch(NoResultException nre) {
			// I didnt found token
			logger.error("User "+loginRequest.getEmail()+" does not exists");
			throw new WebApplicationException(Response.Status.UNAUTHORIZED);
		}
		
		UserSession userSession	=null;
		try {
			userSession=(UserSession) entityManager.createQuery("select u from UserSession u where u.user = ? and u.device = ?").
				setParameter(1, user).
				setParameter(2, loginRequest.getDevice()).getSingleResult();
		}
		catch(NoResultException nre) {
			userSession=new UserSession();
			userSession.setUser(user);
			userSession.setDevice(loginRequest.getDevice());
		}
		boolean isNewDevice=userSession.getId()==null;
		userSession.setToken(UUID.randomUUID().toString());
		entityManager.persist(userSession);
		entityManager.flush();
		
		return new LoginResponse(userSession.getToken(),isNewDevice);
	}
}
