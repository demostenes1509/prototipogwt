package com.teracode.prototipogwt.backend.providers.domaintests;

import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.teracode.prototipogwt.backend.extras.util.BackendConstants;
import com.teracode.prototipogwt.backend.providers.base.BaseEntitiesProviderTest;
import com.teracode.prototipogwt.domain.domainmodel.Role;
import com.teracode.prototipogwt.domain.domainmodel.User;
import com.teracode.prototipogwt.domain.domainmodel.UserSession;
import com.teracode.prototipogwt.domain.dto.UserSet;
import com.teracode.prototipogwt.domain.util.DomainConstants;

/**
 * @author Maxi
 *
 */
public class UserProviderTest extends BaseEntitiesProviderTest<User,UserSet> {
	
	@Override
	public String getExistingURL() {
		return DomainConstants.USER_PATH + "/1";
	}

	@Override
	public String getNotExistingURL() {
		return DomainConstants.USER_PATH + "/" + getNonexistentEntityId();
	}
	
	@Override
	public String getDeleteableURL() {
		return DomainConstants.USER_PATH + "/2";
	}
	
	@Override
	public String getUpdateableURL() {
		return getDeleteableURL();
	}

	@Override
	public String getListURL() {
		return DomainConstants.USER_PATH;
	}

	@Override
	public void verifyEntity(User user, boolean isNew) {
		
		if (isNew) {
			
			Assert.assertEquals(user.getId(), new Long(DomainConstants.FIRST_AVAILABLE_ID));
			Assert.assertEquals(user.getEmail(), "carrizito@hotmail.com");
			
		} else {  
			
			Assert.assertEquals(user.getId(), new Long(1));
			Assert.assertEquals(user.getEmail(), BackendConstants.TEST_EMAIL);
			
			Assert.assertNotNull(user.getRole());
			Assert.assertEquals(user.getRole().getUser().size(),0);

			Assert.assertNotNull(user.getUserSession());
			for (UserSession session : user.getUserSession()) {
				Assert.assertNull(session.getUser(), "jackson should not serialize this backlink");
			}
		}
	}

	@Override
	protected Class<User> getEntityClass() {
		return User.class;
	}

	@Override
	protected Class<UserSet> getListClass() {
		return UserSet.class;
	}
	
	@Override
	public void verifyList(UserSet f) {
		Assert.assertEquals(f.getItem().size(), 2);
	}
	
	@Override
	protected User getEntityToSave() throws Exception {
	
		User user = new User();
		user.setEmail("carrizito@hotmail.com");
		user.setRole(getRoleFromServer(1L,MediaType.APPLICATION_XML_TYPE));
//		
//		UserSession userSession = new UserSession(user, "MyDevice", "MyToken", "MyOauthToken");
//		user.addUserSession(userSession);
		
		return user;
	}

	/**
	 * @return
	 */
	private Role getRoleFromServer(long roleId,MediaType accepts) throws Exception {
		
		ClientRequest request = getUnsecuredRequestWithToken(DomainConstants.ROLE_PATH + "/" + roleId,accepts);
		ClientResponse<Role> response = request.get(Role.class);
		Role role = response.getEntity();
		response.releaseConnection();
		return role;
	}

	@Override
	protected void changeEntityToUpdate(User entity) {

		entity.setEmail("modified@gmail.com");
	}

	@Override
	protected void verifyModifiedEntity(User e) {
		
		Assert.assertEquals(e.getId(), new Long(2));
		Assert.assertEquals(e.getEmail(), "modified@gmail.com");
	}
	
	@Override
	protected long getNonexistentEntityId() { return 3L; }
	
	@Test
	@Override
	public void testSaveURLJson() throws Exception {
		// Test disabled for User
	}
	
	@Test
	@Override
	public void testSaveURLXml() throws Exception {
		// Test disabled for User
	}
	
	@Test
	@Override
	public void testUpdateObjectNotFoundJson() throws Exception {
		// Test disabled for User 
	}

	@Test
	@Override
	public void testUpdateObjectNotFoundXml() throws Exception {
		// Test disabled for User 
	}
	
	@Test
	@Override
	public void testUpdateObjectOKJson() throws Exception {
		// Test disabled for User 
	}
	
	@Test
	@Override
	public void testUpdateObjectOKXml() throws Exception {
		// Test disabled for User 
	}

	@Test
	@Override
	public void testUpdateObjectWrongIdJson() throws Exception {
		// Test disabled for User 
	}
	
	@Test
	@Override
	public void testUpdateObjectWrongIdXml() throws Exception {
		// Test disabled for User 
	}
	
	@Test
	@Override
	public void testDeleteObjectNotFoundJson() throws Exception {
		// Test disabled for User 
	}
	
	@Test
	@Override
	public void testDeleteObjectNotFoundXml() throws Exception {
		// Test disabled for User 
	}

	@Test
	@Override
	public void testDeleteObjectOKJson() throws Exception {
		// Test disabled for User 
	}
	
	@Test
	@Override
	public void testDeleteObjectOKXml() throws Exception {
		// Test disabled for User 
	}

}