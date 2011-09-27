package com.teracode.prototipogwt.frontend.client.events.role;

import com.teracode.prototipogwt.domain.domainmodel.Role;
import com.teracode.prototipogwt.frontend.client.support.events.entity.EntityObtainedEvent;
import com.teracode.prototipogwt.frontend.client.support.events.entity.EntityObtainedHandler;

/**
 * @author Maxi
 *
 */
public class RoleObtainedEvent extends EntityObtainedEvent<Role> {
	
	public static final Type<EntityObtainedHandler<Role>> TYPE = new Type<EntityObtainedHandler<Role>>();

	public RoleObtainedEvent(Role role) {
		super(role);
	}

	@Override
	public Type<EntityObtainedHandler<Role>> getAssociatedType() {
		return TYPE;
	}
}
