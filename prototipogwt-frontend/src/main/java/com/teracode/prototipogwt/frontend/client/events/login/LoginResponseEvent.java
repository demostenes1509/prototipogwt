package com.teracode.prototipogwt.frontend.client.events.login;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.teracode.prototipogwt.domain.domainmodel.User;
import com.teracode.prototipogwt.domain.usecasesdto.LoginResponse;

/**
 * This event is triggered as the <b>last step</b> of a {@link User} logging <b>IN</b>.
 * 
 * @author andran
 */
public class LoginResponseEvent extends GwtEvent<LoginResponseEvent.LoginResponseEventHandler> {

	public static Type<LoginResponseEvent.LoginResponseEventHandler> TYPE = new Type<LoginResponseEvent.LoginResponseEventHandler>();
	
	private LoginResponse loginResponse;

	public interface LoginResponseEventHandler extends EventHandler {
		void onAfterLoginResponse(LoginResponseEvent event);
	}

	public LoginResponseEvent(LoginResponse loginResponse) {
		this.loginResponse = loginResponse;
	}

	@Override
	public Type<LoginResponseEvent.LoginResponseEventHandler> getAssociatedType() { return TYPE; }

	protected void dispatch(LoginResponseEventHandler handler) {
		handler.onAfterLoginResponse(this);
	}

	public LoginResponse getLoginResponse() {
		return loginResponse;
	}

	

}
