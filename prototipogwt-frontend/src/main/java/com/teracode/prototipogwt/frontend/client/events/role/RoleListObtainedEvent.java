package com.teracode.prototipogwt.frontend.client.events.role;

import com.teracode.prototipogwt.domain.domainmodel.Role;
import com.teracode.prototipogwt.domain.dto.RoleSet;
import com.teracode.prototipogwt.frontend.client.support.events.entity.EntityListObtainedEvent;
import com.teracode.prototipogwt.frontend.client.support.events.entity.EntityListObtainedHandler;

/**
 * @author Maxi
 *
 */
public class RoleListObtainedEvent extends EntityListObtainedEvent<Role,RoleSet> {
	
	public static final Type<EntityListObtainedHandler<Role,RoleSet>> TYPE = new Type<EntityListObtainedHandler<Role,RoleSet>>();

	public RoleListObtainedEvent(RoleSet roleSet) {
		super(roleSet);
	}

	@Override
	public Type<EntityListObtainedHandler<Role,RoleSet>> getAssociatedType() {
		return TYPE;
	}
}
