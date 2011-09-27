package com.teracode.prototipogwt.frontend.client.events.role;

import com.teracode.prototipogwt.domain.domainmodel.Role;
import com.teracode.prototipogwt.frontend.client.support.events.entity.EntitySavedEvent;
import com.teracode.prototipogwt.frontend.client.support.events.entity.EntitySavedHandler;

/**
 * @author Maxi
 *
 */
public class RoleSavedEvent extends EntitySavedEvent<Role> {
	
	public static final Type<EntitySavedHandler<Role>> TYPE = new Type<EntitySavedHandler<Role>>();

	public RoleSavedEvent(Role role) {
		super(role);
	}

	@Override
	public Type<EntitySavedHandler<Role>> getAssociatedType() {
		return TYPE;
	}
}
