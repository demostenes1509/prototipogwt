package com.teracode.prototipogwt.backend.extras.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.teracode.prototipogwt.domain.domainmodel.EntityBase;
import com.teracode.prototipogwt.domain.dto.EntityBaseSet;
import com.teracode.prototipogwt.domain.responses.LongResponse;

/**
 * @author Maxi
 *
 */
@Transactional
public abstract class DomainDAO<E extends EntityBase,F extends EntityBaseSet<E>> {
	
	private static Logger logger = Logger.getLogger(DomainDAO.class);
	
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * @return
	 */
	protected EntityManager getEntityManager() { return entityManager; }

	/**
	 * @return
	 */
	public abstract Class<E> getEntityClass();
	
	public abstract F getInstance();
	
	/**
	 * @return
	 */
	protected List<String> getQueryParameters() {
		return new ArrayList<String>();
	}
	
	/**
	 * Get an entity by Id.
	 * 
	 * @param id
	 * @return The entity if found; otherwise it will return the error code 404 NOT FOUND.
	 */
	@GET
	@Path("{id}")
	public E get(@PathParam("id") long id) {

		logger.info("Searching Entity " + getEntityClass() + " with ID:" + id);
		
		E e = entityManager.find(getEntityClass(), id);
		if (e == null) {
			logger.error("Entity " + getEntityClass() + " with ID:" + id + " not found");
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		
		logger.info("Loaded Entity " + e);
		
		return e;
	}
	
	@SuppressWarnings("unchecked")
	@GET 
	@Path("/")
	public F find(@Context UriInfo uriInfo) {
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(getEntityClass());

		for(String param:getQueryParameters()) {
			List<String> value = uriInfo.getQueryParameters().get(param);
			if(value!=null) {
				criteria.add( Restrictions.eq(param, value.get(0)));
			}
		}
		
		F f=getInstance();
		f.setItem(new HashSet<E>(criteria.list()));
		
		return f;
	}
	
	/**
	 * Save an entity.
	 * 
	 * @param entity
	 * @param uriInfo
	 * @return The server response.
	 */
	@POST
	public E save(E entity, @Context UriInfo uriInfo) {
		
		logger.info("Saving Entity " + getEntityClass());
		entityManager.persist(entity);	
		logger.debug("Flushing Entity " + getEntityClass());
		entityManager.flush();
					
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path((entity.getId().toString()));
		
		logger.debug("Returning ID " + entity.getId().toString() + " to Client");
		return entity;		
	}
	
	/**
	 * Save an entity.
	 * 
	 * @param id
	 * @param entity
	 * @return The server response.
	 * 		<ul>
	 * 			<li>200 OK          : if the update was successful.</li>
	 * 			<li>400 Bad Request : if the request is badly formed (e.g. due to wrong entity ids).</li>
	 * 			<li>404 Not Found   : if the entity we tried to update doesn't exist.</li>
	 * 		</ul>
	 */
	@PUT
	@Path("{id}")
	public E update(@PathParam("id") long id, E entity) {
		
		logger.info("Updating Entity " + getEntityClass() + " with id " + id);
		
		// The entity must have an id that matches the id from the URL
		if (entity.getId() == null || !entity.getId().equals(id)) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}
		
		// The entity must already exist!
		if (entityManager.find(getEntityClass(), new Long(id)) == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		
		logger.debug("Merging Entity " + getEntityClass());
		entityManager.merge(entity);	
		logger.debug("Flushing Entity " + getEntityClass());
		entityManager.flush();
					
		return entity; 
	}
	
	/**
	 * Delete an entity, retrieving it by Id first.
	 * 
	 * @param id
	 * @return Possible response codes:
	 * 		<ul>
	 * 			<li>204 No Content: if the entity exists and was successfully deleted.</li>
	 * 			<li>404 Not Found : if the entity doesn't exist.</li>
	 *      </ul>
	 */
	@DELETE
	@Path("{id}")
	public LongResponse delete(@PathParam("id") long id) {
		
		logger.debug("Retrieving Entity " + getEntityClass() + "by id " + id);
		E entity = get(id);
		
		logger.debug("Cascading deletes");
		cascadeDeletes(entity);
		
		logger.info("Deleting Entity " + getEntityClass());
		entityManager.remove(entity);	
		logger.debug("Flushing Entity " + getEntityClass());
		entityManager.flush();
					
		logger.debug("Returning response for delete operation to Client");
		return new LongResponse(id); 
	}
	
	/**
	 * Subclasses may override this method to provide special cascading behavior for subentities
	 * when deleting an Entity.
	 * 
	 * @param entity The main entity to delete.
	 */
	public void cascadeDeletes(E entity) {
		// Default implementation does nothing
	}
	
}
