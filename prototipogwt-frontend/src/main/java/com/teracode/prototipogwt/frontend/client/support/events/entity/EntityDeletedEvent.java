package com.teracode.prototipogwt.frontend.client.support.events.entity;

import com.google.gwt.event.shared.GwtEvent;
import com.teracode.prototipogwt.domain.domainmodel.EntityBase;

public abstract class EntityDeletedEvent<E extends EntityBase> extends GwtEvent<EntityDeletedHandler<E>> {
	
	private final Long id;
	
	public EntityDeletedEvent(Long id) {
		this.id	= id;
	}

	@Override
	protected void dispatch(EntityDeletedHandler<E> handler) {
		handler.onEntityDeleted(id);
	}
}
