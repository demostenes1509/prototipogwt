package com.teracode.prototipogwt.frontend.client.support.callback;

import org.fusesource.restygwt.client.JsonCallback;
import org.fusesource.restygwt.client.Method;

import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;

/**
 * @author Maxi
 *
 * @param <T>
 */
public abstract class CustomJsonCallback implements JsonCallback {
	
	public CustomJsonCallback() {
	}
	
	/* (non-Javadoc)
	 * @see org.fusesource.restygwt.client.MethodCallback#onFailure(org.fusesource.restygwt.client.Method, java.lang.Throwable)
	 */
	@Override
	public void onFailure(Method method, Throwable throwable) {
		Window.alert(throwable.getMessage());
	}
	
	/* (non-Javadoc)
	 * @see org.fusesource.restygwt.client.MethodCallback#onSuccess(org.fusesource.restygwt.client.Method, java.lang.Object)
	 */
	@Override
	public void onSuccess(Method method, JSONValue response) {
		run(response);
	}
	
	/**
	 * @param response
	 */
	public abstract void run(JSONValue response);
	
}
