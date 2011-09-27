package com.teracode.prototipogwt.backend.endpoint.login;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.teracode.prototipogwt.backend.providers.base.BaseProviderTest;
import com.teracode.prototipogwt.domain.usecasesdto.LoginRequest;
import com.teracode.prototipogwt.domain.usecasesdto.LoginResponse;
import com.teracode.prototipogwt.domain.util.DomainConstants;


/**
 * @author Maxi
 *
 */
public class LoginEndPointTest extends BaseProviderTest {

	/**
	 * @param isNewDevice
	 * @throws Exception
	 */
	@Test
	public void testLoginOKXml() throws Exception {
		testLoginOK(MediaType.APPLICATION_XML_TYPE);
	}
	
	@Test
	public void testLoginOKJson() throws Exception {
		testLoginOK(MediaType.APPLICATION_JSON_TYPE);
	}
	
	/**
	 * @param accepts
	 * @throws Exception
	 */
	private void testLoginOK(MediaType accepts) throws Exception {
		ClientRequest requestOne = getSecuredRequestWithoutToken(DomainConstants.LOGIN_USE_CASE_PATH,accepts);
		LoginRequest loginRequest=new LoginRequest("prototipogwt@teracode.com", "admin", "webnew");
		requestOne.body(accepts, loginRequest);
		ClientResponse<LoginResponse> responseOne = requestOne.put(LoginResponse.class);
		LoginResponse loginResponseOne = responseOne.getEntity();
		String token1=loginResponseOne.getSessionToken();
		Assert.assertEquals(loginResponseOne.getIsNewDevice(), new Boolean(true));
		Assert.assertEquals(Response.Status.OK, Response.Status.fromStatusCode(responseOne.getStatus()));
		responseOne.releaseConnection();
		
		// I verify application returns two different tokens when user logins
		ClientRequest requestTwo = getSecuredRequestWithoutToken(DomainConstants.LOGIN_USE_CASE_PATH,accepts);
		requestTwo.body(accepts, loginRequest);
		ClientResponse<LoginResponse> responseTwo = requestTwo.put(LoginResponse.class);
		LoginResponse loginResponseTwo = responseTwo.getEntity();
		String token2=loginResponseTwo.getSessionToken();
		Assert.assertEquals(loginResponseTwo.getIsNewDevice(), new Boolean(false));
		Assert.assertNotSame(token1, token2);
		Assert.assertEquals(Response.Status.OK, Response.Status.fromStatusCode(responseTwo.getStatus()));
		responseTwo.releaseConnection();
	}
	
	/**
	 * 
	 */
	@Test
	public void testUsernameNotExistsXml() throws Exception {
		testUsernameNotExists(MediaType.APPLICATION_XML_TYPE);
	}

	/**
	 * 
	 */
	@Test
	public void testUsernameNotExistsJson() throws Exception {
		testUsernameNotExists(MediaType.APPLICATION_JSON_TYPE);
	}
	
	/**
	 * @param accepts
	 * @throws Exception
	 */
	private void testUsernameNotExists(MediaType accepts) throws Exception {
		ClientRequest requestOne = getSecuredRequestWithoutToken(DomainConstants.LOGIN_USE_CASE_PATH,accepts);
		LoginRequest loginRequest=new LoginRequest("maxi.carrizo@gmail.com", "admin", "webnew");
		requestOne.body(accepts, loginRequest);
		ClientResponse<LoginResponse> responseOne = requestOne.put(LoginResponse.class);
		Assert.assertEquals(Response.Status.UNAUTHORIZED, Response.Status.fromStatusCode(responseOne.getStatus()));
		responseOne.releaseConnection();
	}

	/**
	 * 
	 */
	@Test
	public void testInvalidPasswordXML() throws Exception {
		testInvalidPassword(MediaType.APPLICATION_XML_TYPE);
	}
	
	/**
	 * 
	 */
	@Test
	public void testInvalidPasswordJson() throws Exception {
		testInvalidPassword(MediaType.APPLICATION_JSON_TYPE);
	}
	
	/**
	 * @param accepts
	 * @throws Exception
	 */
	private void testInvalidPassword(MediaType accepts) throws Exception {
		ClientRequest requestOne = getSecuredRequestWithoutToken(DomainConstants.LOGIN_USE_CASE_PATH,accepts);
		LoginRequest loginRequest=new LoginRequest("prototipogwt@teracode.com", "invalidpassword", "webnew");
		requestOne.body(MediaType.APPLICATION_JSON, loginRequest);
		ClientResponse<LoginResponse> responseOne = requestOne.put(LoginResponse.class);
		Assert.assertEquals(Response.Status.UNAUTHORIZED, Response.Status.fromStatusCode(responseOne.getStatus()));
		responseOne.releaseConnection();
	}
	
}