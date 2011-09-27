package com.teracode.prototipogwt.frontend.client.events.role;

import com.teracode.prototipogwt.domain.domainmodel.Role;
import com.teracode.prototipogwt.frontend.client.support.events.entity.EntityDeletedEvent;
import com.teracode.prototipogwt.frontend.client.support.events.entity.EntityDeletedHandler;

/**
 * @author Maxi
 *
 */
public class RoleDeletedEvent extends EntityDeletedEvent<Role> {
	
	public static final Type<EntityDeletedHandler<Role>> TYPE = new Type<EntityDeletedHandler<Role>>();

	public RoleDeletedEvent(Long id) {
		super(id);
	}

	@Override
	public Type<EntityDeletedHandler<Role>> getAssociatedType() {
		return TYPE;
	}
}
