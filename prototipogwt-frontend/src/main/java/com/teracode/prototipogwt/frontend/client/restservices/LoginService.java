package com.teracode.prototipogwt.frontend.client.restservices;

import org.fusesource.restygwt.client.JsonEncoderDecoder;
import org.fusesource.restygwt.client.Resource;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.ResettableEventBus;
import com.google.gwt.json.client.JSONValue;
import com.google.inject.Inject;
import com.teracode.prototipogwt.domain.usecasesdto.LoginRequest;
import com.teracode.prototipogwt.domain.usecasesdto.LoginResponse;
import com.teracode.prototipogwt.domain.util.DomainConstants;
import com.teracode.prototipogwt.frontend.client.events.login.LoginResponseEvent;
import com.teracode.prototipogwt.frontend.client.support.callback.CustomJsonCallback;
import com.teracode.prototipogwt.frontend.client.support.restservices.RestServices;

/**
 * @author Maxi
 */
public class LoginService extends RestServices {
	
	public interface LoginRequestJED extends JsonEncoderDecoder<LoginRequest> {}
	public interface LoginResponseJED extends JsonEncoderDecoder<LoginResponse> {}
	private final LoginRequestJED loginRequestJED	= GWT.create(LoginRequestJED.class);
	private final LoginResponseJED loginResponseJED	= GWT.create(LoginResponseJED.class);
	
	private ResettableEventBus eventBus;
	
	@Inject
	public LoginService(ResettableEventBus eventBus) {
		this.eventBus = eventBus;
	}
	
	public void login(LoginRequest loginRequest) {
		Resource resource = getRestResource(DomainConstants.LOGIN_USE_CASE_PATH);
		
		JSONValue request = loginRequestJED.encode(loginRequest);
		
		getConfiguredMethod(resource.put()).json(request).send(new CustomJsonCallback() {
			@Override
			public void run(JSONValue response) {
				LoginResponse loginResponse = loginResponseJED.decode(response);
				eventBus.fireEvent(new LoginResponseEvent(loginResponse));
			}
		});		
	}
}
