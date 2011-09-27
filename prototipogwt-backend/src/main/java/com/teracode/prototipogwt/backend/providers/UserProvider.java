package com.teracode.prototipogwt.backend.providers;

import java.util.HashSet;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teracode.prototipogwt.backend.providers.UserProvider;
import com.teracode.prototipogwt.domain.domainmodel.User;
import com.teracode.prototipogwt.domain.dto.UserSet;
import com.teracode.prototipogwt.domain.util.DomainConstants;

/**
 * @author Maxi
 */
@Service
@Scope(value=BeanDefinition.SCOPE_PROTOTYPE)
@Transactional
@Path(DomainConstants.USER_PATH)
@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
//@Restrict
public class UserProvider {
	
	private static Logger logger = Logger.getLogger(UserProvider.class);

	@PersistenceContext
	private EntityManager entityManager;
	
	@GET
	@Path("{id}")
	public User get(@PathParam("id") long id) {
		
		// TODO: This code will change after Jettison / Jackson research finish !
		/*
		User user=new User();
		user.setFirstName("John");
		user.setLastName("Doe");
		user.setEmail("john.doe@gmail.com");
		user.setType("wtf?");
		user.setUserName("johnny");
		user.setId(id);
		
		Role role=new Role();
		role.setName("admin");
		role.setHomepage("http://myhomepage.html");
		
		user.setRole(role);
		
		UserSession userSession1=new UserSession();
		userSession1.setDevice("web");
		userSession1.setToken("8898d6ad-bc94-4740-8a58-f0a079102e2d");
		UserSession userSession2=new UserSession();
		userSession2.setDevice("iphone");
		userSession2.setToken("8898d6ad-bc94-4740-8a58-f0a079102e2d");
		UserSession userSession3=new UserSession();
		userSession3.setDevice("blackberry");
		userSession3.setToken("8898d6ad-bc94-4740-8a58-f0a079102e2d");
		
		user.getUserSession().add(userSession1);
		user.getUserSession().add(userSession2);
		user.getUserSession().add(userSession3);
		return user;
		*/
		
		try {
			
			User user = (User) entityManager.createQuery("select user from User user inner join fetch user.role role inner join fetch user.userSession userSession where user.id = ?").setParameter(1, id).getSingleResult();
			return user;
	
		} catch (NoResultException nre) {
			throw new WebApplicationException(nre,Response.Status.NOT_FOUND);
		}
	}
	
	@SuppressWarnings("unchecked")
	@GET 
	@Path("/")
	public UserSet find(@Context UriInfo uriInfo) {
		
		logger.info("Query Parameters:" + uriInfo.getQueryParameters());

		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(User.class);
		
		return new UserSet(new HashSet<User>(criteria.list()));
	}

}
