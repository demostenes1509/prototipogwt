package com.teracode.prototipogwt.frontend.client.support.events.entity;

import com.google.gwt.event.shared.GwtEvent;
import com.teracode.prototipogwt.domain.domainmodel.EntityBase;

public abstract class EntitySavedEvent<E extends EntityBase> extends GwtEvent<EntitySavedHandler<E>> {
	
	private final E entity;
	
	public EntitySavedEvent(E entity) {
		this.entity	= entity;
	}

	@Override
	protected void dispatch(EntitySavedHandler<E> handler) {
		handler.onEntitySaved(entity);
	}
}
