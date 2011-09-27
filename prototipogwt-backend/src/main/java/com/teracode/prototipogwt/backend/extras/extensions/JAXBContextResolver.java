package com.teracode.prototipogwt.backend.extras.extensions;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import com.sun.xml.bind.api.JAXBRIContext;

/**
 * @author Maxi
 *
 */
@Provider
@Produces(MediaType.APPLICATION_XML)
public class JAXBContextResolver implements ContextResolver<JAXBContext>
{

	/* (non-Javadoc)
	 * @see javax.ws.rs.ext.ContextResolver#getContext(java.lang.Class)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public JAXBContext getContext(Class<?> type) {
		try {
			Map<String,Boolean> properties = new HashMap<String,Boolean>();
			properties.put(JAXBRIContext.XMLACCESSORFACTORY_SUPPORT, true);
			
			Class[] classesToBeBound = { type };
			
			return JAXBContext.newInstance(classesToBeBound,properties);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}
}