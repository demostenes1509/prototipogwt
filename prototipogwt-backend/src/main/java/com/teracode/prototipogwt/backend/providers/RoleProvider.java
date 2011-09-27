package com.teracode.prototipogwt.backend.providers;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.teracode.prototipogwt.backend.extras.annotations.security.Restrict;
import com.teracode.prototipogwt.backend.extras.dao.DomainDAO;
import com.teracode.prototipogwt.domain.domainmodel.Role;
import com.teracode.prototipogwt.domain.dto.RoleSet;
import com.teracode.prototipogwt.domain.util.DomainConstants;

/**
 * @author Maxi
 *
 */
@Service
@Scope(value=BeanDefinition.SCOPE_PROTOTYPE)
@Path(DomainConstants.ROLE_PATH)
@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
@Restrict
public class RoleProvider extends DomainDAO<Role,RoleSet> {
	
	@Override
	public Class<Role> getEntityClass() {
		return Role.class;
	}
	
	@Override
	public RoleSet getInstance() {
		return new RoleSet();
	}

	@Override
	public List<String> getQueryParameters() {
		List<String> list=new ArrayList<String>();
		list.add("name");
		return list;
	}
}
