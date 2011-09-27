package com.teracode.prototipogwt.backend.providers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.teracode.prototipogwt.domain.domainmodel.EntityBase;
import com.teracode.prototipogwt.domain.dto.EntityBaseSet;

/**
 * @author Maxi
 *
 * @param <E>
 * @param <F>
 */
public interface DomainProvider<E extends EntityBase, F extends EntityBaseSet<E>> {
	
	/**
	 * Get a domain resource by Id.
	 */
	E get(long id);
	
	/**
	 * @return A list of all elements of the given domain class.
	 */
	F find(UriInfo uriInfo);
	
	/**
	 * Save a domain entity.
	 * 
	 * @param entity
	 * @param uriInfo
	 * @return The server response. Possible return codes are:
	 * 		<ul>
	 * 			<li>201 Created: if the creation of a new entity was successful.</li>
	 * 			<li>???        : if there was an error.</li>
	 * 		</ul>
	 */
	Response save(E entity, UriInfo uriInfo);
	
	/**
	 * Delete a domain entity by id.
	 * 
	 * @param entity
	 * @param uriInfo
	 * @return The server response. Possible return codes are:
	 * 		<ul>
	 * 			<li>204 No Content: if the delete was successful.</li>
	 * 			<li>404 Not Found : if the entity didn't exist.</li>
	 * 		</ul>
	 */
	Response delete(long id);
	
	/**
	 * Update a domain entity.
	 * 
	 * @param id
	 * @param entity
	 * @return The server response. Possible return codes are:
	 * 		<ul>
	 * 			<li>200 OK          : if the update was successful.</li>
	 *			<li>400 Bad Request : if the request is badly formed (e.g. due to wrong entity ids).</li>
	 * 			<li>404 Not Found   : if the entity we tried to update doesn't exist.</li>
	 * 		</ul>
	 */
	Response update(long id, E entity);
	
}
