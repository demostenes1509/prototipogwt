package com.teracode.prototipogwt.backend.providers.domaintests;

import org.testng.Assert;

import com.teracode.prototipogwt.backend.providers.base.BaseEntitiesProviderTest;
import com.teracode.prototipogwt.domain.domainmodel.Role;
import com.teracode.prototipogwt.domain.dto.RoleSet;
import com.teracode.prototipogwt.domain.util.DomainConstants;

/**
 * @author Maxi
 *
 */
public class RoleProviderTest extends BaseEntitiesProviderTest<Role,RoleSet> {

	// TODO: Andran el algun momento
	private static final String SPECIAL_CHARS_STRING="";
	
	@Override
	public String getExistingURL() {
		return DomainConstants.ROLE_PATH + "/2";   
	}

	@Override
	public String getNotExistingURL() {
		return DomainConstants.ROLE_PATH + "/" + getNonexistentEntityId();
	}
	
	@Override
	public String getDeleteableURL() {
		return DomainConstants.ROLE_PATH + "/2";
	}
	
	@Override
	public String getUpdateableURL() {
		return getDeleteableURL();
	}

	@Override
	public String getListURL() {
		return DomainConstants.ROLE_PATH + "?name=admin";
	}

	@Override
	public void verifyEntity(Role e, boolean isNew) {
		
		if (isNew) {
			
			Assert.assertEquals(e.getId(), new Long(DomainConstants.FIRST_AVAILABLE_ID));
			Assert.assertEquals(e.getName(), "mynewrole" + SPECIAL_CHARS_STRING);

		
		} else { 
			
			Assert.assertEquals(e.getId(), new Long(2));
			Assert.assertEquals(e.getName(), "dummy"); 
		
		}
	}

	@Override
	protected Class<Role> getEntityClass() {
		return Role.class;
	}

	@Override
	protected Class<RoleSet> getListClass() {
		return RoleSet.class;
	}
	
	@Override
	public void verifyList(RoleSet f) {
		Assert.assertEquals(f.getItem().size(), 1);
		Assert.assertEquals(f.getItem().iterator().next().getName(),"admin");
	}
	
	@Override
	protected Role getEntityToSave() throws Exception {
		
		Role role = new Role();
		role.setName("mynewrole" + SPECIAL_CHARS_STRING);
		return role;
	}

	@Override
	protected void changeEntityToUpdate(Role entity) {
		
		entity.setName("Modified name");
	}

	@Override
	protected void verifyModifiedEntity(Role e) {
		
		Assert.assertEquals(e.getId(), new Long(2));
		Assert.assertEquals(e.getName(), "Modified name");
	}

	@Override
	protected long getNonexistentEntityId() { return 4L; }
	
}