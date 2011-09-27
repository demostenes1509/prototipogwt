package com.teracode.prototipogwt.frontend.client.support.restservices;

import org.fusesource.restygwt.client.JsonEncoderDecoder;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.Resource;

import com.google.gwt.core.client.GWT;
import com.teracode.prototipogwt.domain.responses.LongResponse;

/**
 * @author Maxi
 *
 */
public abstract class RestServices {
	
	private final static int TIMEOUT = 20000;
	
	public interface LongResponseJED extends JsonEncoderDecoder<LongResponse> {}
	private final LongResponseJED longResponseJED = GWT.create(LongResponseJED.class);
	
	/**
	 * @return the booleanJED
	 */
	public LongResponseJED getLongResponseJED() {
		return longResponseJED;
	}

	/**
	 * GET REST resource.
	 * 
	 * @param sessionToken The session token. Needed for authenticated requests.
	 */
	protected Resource getRestResource(String url) {
		String completeUrl = GWT.getModuleBaseURL() + "rest" + url;
		return new CustomResource( completeUrl );
	}

	/**
	 * Configure a Method with a timeout and <b>without</b> a session token.
	 * 
	 * @param method
	 * @param sessionToken The session token. Needed for authenticated requests.
	 */
	public Method getConfiguredMethod(Method method) {

		method = method.timeout(TIMEOUT);
		return method;
	}

	/**
	 * Configure a Method with a session token and a timeout. If the session token is null or empty,
	 * it won't include it in the header.
	 * 
	 * @param method
	 * @param sessionToken The session token. Needed for authenticated requests.
	 */
//	private Method getConfiguredMethod(Method method, String sessionToken) {
//		
//		if (sessionToken != null && !"".equals(sessionToken)) {
//			method = method.header(Constants.CITI_TOKEN, sessionToken).timeout(TIMEOUT);
//		} else {
//			method = method.timeout(TIMEOUT);
//		}
//		
//		return method;
//	}
	
	
}
