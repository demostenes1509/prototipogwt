package com.teracode.prototipogwt.frontend.client.events.role;

import com.teracode.prototipogwt.domain.domainmodel.Role;
import com.teracode.prototipogwt.frontend.client.support.events.entity.EntityUpdatedEvent;
import com.teracode.prototipogwt.frontend.client.support.events.entity.EntityUpdatedHandler;

/**
 * @author Maxi
 *
 */
public class RoleUpdatedEvent extends EntityUpdatedEvent<Role> {
	
	public static final Type<EntityUpdatedHandler<Role>> TYPE = new Type<EntityUpdatedHandler<Role>>();

	public RoleUpdatedEvent(Role role) {
		super(role);
	}

	@Override
	public Type<EntityUpdatedHandler<Role>> getAssociatedType() {
		return TYPE;
	}
}
