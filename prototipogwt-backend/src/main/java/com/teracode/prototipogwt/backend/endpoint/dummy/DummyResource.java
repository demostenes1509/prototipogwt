package com.teracode.prototipogwt.backend.endpoint.dummy;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teracode.prototipogwt.backend.extras.annotations.security.Restrict;
import com.teracode.prototipogwt.domain.usecasesdto.DummyRequest;
import com.teracode.prototipogwt.domain.util.DomainConstants;

/**
 * @author Maxi
 *
 */
@Path(DomainConstants.DUMMY_PATH)
@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
@Restrict
@Service
@Scope(value=BeanDefinition.SCOPE_PROTOTYPE)
@Transactional
public class DummyResource  {
	
	/* (non-Javadoc)
	 * @see com.citibank.citibeneficios.rest.newresources.dummyresources.DummyResource#dummy(com.citibank.citibeneficios.dummy.DummyRequest, javax.ws.rs.core.UriInfo)
	 */
	@POST
	public void dummy(DummyRequest dummyRequest,@Context UriInfo uriInfo) {
		
		// This line should NEVER run. If test reach this point, security is not working
		throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
	}
}
