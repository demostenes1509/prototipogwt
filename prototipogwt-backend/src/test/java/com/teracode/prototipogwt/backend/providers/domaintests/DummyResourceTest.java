package com.teracode.prototipogwt.backend.providers.domaintests;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.teracode.prototipogwt.backend.extras.util.BackendConstants;
import com.teracode.prototipogwt.backend.providers.base.BaseProviderTest;
import com.teracode.prototipogwt.domain.usecasesdto.DummyRequest;
import com.teracode.prototipogwt.domain.util.DomainConstants;


/**
 * @author Maxi
 *
 */
@SuppressWarnings({ "rawtypes" })
public class DummyResourceTest extends BaseProviderTest {

	@Test
	public void testAuthenticationTokenIsMissingJson() throws Exception {
		testAuthenticationTokenIsMissing(MediaType.APPLICATION_JSON_TYPE);
	}
	
	@Test
	public void testAuthenticationTokenIsMissingXML() throws Exception {
		testAuthenticationTokenIsMissing(MediaType.APPLICATION_XML_TYPE);
	}
	
	/**
	 * @throws Exception
	 */
	private void testAuthenticationTokenIsMissing(MediaType mediaType) throws Exception {
		ClientRequest request = createRequest(getJettyHttpContext()+DomainConstants.DUMMY_PATH,mediaType);
		
		DummyRequest dummyRequest=new DummyRequest();
		request.body(mediaType, dummyRequest);
		ClientResponse responseOne = request.post();
		Assert.assertEquals(Response.Status.UNAUTHORIZED, Response.Status.fromStatusCode(responseOne.getStatus()));
		responseOne.releaseConnection();
	}
	
	@Test
	public void testAuthenticationTokenIsInvalidXML() throws Exception {
		testAuthenticationTokenIsInvalid(MediaType.APPLICATION_XML_TYPE);
	}
	
	@Test
	public void testAuthenticationTokenIsInvalidJSon() throws Exception {
		testAuthenticationTokenIsInvalid(MediaType.APPLICATION_JSON_TYPE);
	}
	
	/**
	 * @throws Exception
	 */
	private void testAuthenticationTokenIsInvalid(MediaType mediaType) throws Exception {
		ClientRequest request = createRequest(getJettyHttpContext()+DomainConstants.DUMMY_PATH,mediaType);
		
		DummyRequest dummyRequest=new DummyRequest();
		request.header(BackendConstants.REST_TOKEN, "dummy-token");
		request.body(mediaType, dummyRequest);
		ClientResponse responseOne = request.post();
		Assert.assertEquals(Response.Status.UNAUTHORIZED, Response.Status.fromStatusCode(responseOne.getStatus()));
		responseOne.releaseConnection();
	}
}