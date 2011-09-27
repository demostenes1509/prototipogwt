package com.teracode.prototipogwt.frontend.client.support.restservices;

import org.fusesource.restygwt.client.Resource;

import com.google.gwt.http.client.URL;
import com.teracode.prototipogwt.frontend.client.support.util.Utils;

/**
 * @author Maxi
 *
 */
public class CustomResource extends Resource {

	/**
	 * @param uri
	 * @param query
	 */
	public CustomResource(String uri, String query) {
		super(uri, query);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param uri
	 */
	public CustomResource(String uri) {
		super(uri);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.fusesource.restygwt.client.Resource#addQueryParam(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("deprecation")
	public CustomResource addNullableQueryParam(String key, Object value) {
		if(Utils.isEmpty(value)==true) {
			String q	= getQuery() == null ? "" : getQuery();
			return new CustomResource(getPath(),q);
		}
		
		key			= URL.encodeComponent(key);
		value		= URL.encodeComponent(value.toString());
		String q	= getQuery() == null ? "" : getQuery() + "&";
        return new CustomResource(getPath(), q + key + "=" + value);
	}
	
	
}
