package com.teracode.prototipogwt.frontend.client.support.events.entity;

import com.google.gwt.event.shared.EventHandler;
import com.teracode.prototipogwt.domain.domainmodel.EntityBase;
import com.teracode.prototipogwt.domain.dto.EntityBaseSet;

/**
 * @author Maxi
 *
 * @param <E>
 */
public interface EntityListObtainedHandler<E extends EntityBase,F extends EntityBaseSet<E>> extends EventHandler {
	public void onEntityListObtained(F f);
}
