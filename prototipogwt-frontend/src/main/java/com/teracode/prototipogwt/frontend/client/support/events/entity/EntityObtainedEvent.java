package com.teracode.prototipogwt.frontend.client.support.events.entity;

import com.google.gwt.event.shared.GwtEvent;
import com.teracode.prototipogwt.domain.domainmodel.EntityBase;

public abstract class EntityObtainedEvent<E extends EntityBase> extends GwtEvent<EntityObtainedHandler<E>> {
	
	private final E entity;
	
	public EntityObtainedEvent(E entity) {
		this.entity	= entity;
	}

	@Override
	protected void dispatch(EntityObtainedHandler<E> handler) {
		handler.onEntityObtained(entity);
	}
}
