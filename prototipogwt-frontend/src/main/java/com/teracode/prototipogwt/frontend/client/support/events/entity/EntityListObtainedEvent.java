package com.teracode.prototipogwt.frontend.client.support.events.entity;

import com.google.gwt.event.shared.GwtEvent;
import com.teracode.prototipogwt.domain.domainmodel.EntityBase;
import com.teracode.prototipogwt.domain.dto.EntityBaseSet;

public abstract class EntityListObtainedEvent<E extends EntityBase,F extends EntityBaseSet<E>> extends GwtEvent<EntityListObtainedHandler<E,F>> {
	
	private final F list;
	
	public EntityListObtainedEvent(F list) {
		this.list	= list;
	}

	@Override
	protected void dispatch(EntityListObtainedHandler<E,F> handler) {
		handler.onEntityListObtained(list);
	}
}
