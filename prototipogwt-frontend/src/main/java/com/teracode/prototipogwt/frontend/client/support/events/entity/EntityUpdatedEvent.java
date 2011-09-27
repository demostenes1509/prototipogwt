package com.teracode.prototipogwt.frontend.client.support.events.entity;

import com.google.gwt.event.shared.GwtEvent;
import com.teracode.prototipogwt.domain.domainmodel.EntityBase;

public abstract class EntityUpdatedEvent<E extends EntityBase> extends GwtEvent<EntityUpdatedHandler<E>> {
	
	private final E entity;
	
	public EntityUpdatedEvent(E entity) {
		this.entity	= entity;
	}

	@Override
	protected void dispatch(EntityUpdatedHandler<E> handler) {
		handler.onEntityUpdated(entity);
	}
}
