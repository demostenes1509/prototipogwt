package com.teracode.prototipogwt.backend.providers.base;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author maxi
 *
 */
class ClassLoaderProxy extends ClassLoader {
	
	private Map<String,String> map=new HashMap<String,String>();

	/**
	 * @param parent
	 */
	public ClassLoaderProxy(final ClassLoader parent) {
		super();
		map.put("META-INF/persistence.xml", "META-INF/persisttest.xml");
	}

	/* (non-Javadoc)
	 * @see java.lang.ClassLoader#getResources(java.lang.String)
	 */
	@Override
	public Enumeration<URL> getResources(final String key) throws IOException {
		String value = map.get(key);
		if(value==null) {
			return super.getResources(key);
		}
		else {
			return super.getResources(value);
		}
		
	}
}

