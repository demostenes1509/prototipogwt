package com.teracode.prototipogwt.frontend.client.support.events.entity;

import com.google.gwt.event.shared.EventHandler;
import com.teracode.prototipogwt.domain.domainmodel.EntityBase;

/**
 * @author Maxi
 *
 * @param <E>
 */
public interface EntitySavedHandler<E extends EntityBase> extends EventHandler {
	public void onEntitySaved(E e);
}
