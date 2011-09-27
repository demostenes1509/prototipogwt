package com.teracode.prototipogwt.frontend.client.restservices;

import org.fusesource.restygwt.client.JsonEncoderDecoder;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.ResettableEventBus;
import com.google.inject.Inject;
import com.teracode.prototipogwt.domain.domainmodel.Role;
import com.teracode.prototipogwt.domain.dto.RoleSet;
import com.teracode.prototipogwt.domain.util.DomainConstants;
import com.teracode.prototipogwt.frontend.client.events.role.RoleDeletedEvent;
import com.teracode.prototipogwt.frontend.client.events.role.RoleListObtainedEvent;
import com.teracode.prototipogwt.frontend.client.events.role.RoleObtainedEvent;
import com.teracode.prototipogwt.frontend.client.events.role.RoleSavedEvent;
import com.teracode.prototipogwt.frontend.client.events.role.RoleUpdatedEvent;
import com.teracode.prototipogwt.frontend.client.support.restservices.RestDAO;

public class RoleService extends RestDAO<Role,RoleSet> {
	
	public interface RoleJED extends JsonEncoderDecoder<Role> {}
	private final RoleJED roleJED = GWT.create(RoleJED.class);
	
	public interface RoleSetJED extends JsonEncoderDecoder<RoleSet> {}
	private final RoleSetJED roleSetJED = GWT.create(RoleSetJED.class);
	
	private ResettableEventBus eventBus;
	
	@Inject
	public RoleService(ResettableEventBus eventBus) {
		this.eventBus = eventBus;
	}
	
	@Override
	public String getRestContext() {
		return DomainConstants.ROLE_PATH;
	}

	@Override
	public JsonEncoderDecoder<Role> getEntityJEDInstance() {
		// TODO Auto-generated method stub
		return roleJED;
	}

	@Override
	public JsonEncoderDecoder<RoleSet> getEntityBaseSetJEDInstance() {
		// TODO Auto-generated method stub
		return roleSetJED;
	}

	@Override
	public void onEntityObtained(Role e) {
		eventBus.fireEvent(new RoleObtainedEvent(e));
	}

	@Override
	public void onEntityUpdated(Role e) {
		eventBus.fireEvent(new RoleUpdatedEvent(e));
	}

	@Override
	public void onEntitySaved(Role e) {
		eventBus.fireEvent(new RoleSavedEvent(e));
	}

	@Override
	public void onEntityDeleted(Long id) {
		eventBus.fireEvent(new RoleDeletedEvent(id));
	}

	/* (non-Javadoc)
	 * @see com.teracode.prototipogwt.frontend.client.support.restservices.RestDAO#onEntityListObtained(com.teracode.prototipogwt.domain.dto.EntityBaseSet)
	 */
	@Override
	public void onEntityListObtained(RoleSet f) {
		eventBus.fireEvent(new RoleListObtainedEvent(f));
	}
}
