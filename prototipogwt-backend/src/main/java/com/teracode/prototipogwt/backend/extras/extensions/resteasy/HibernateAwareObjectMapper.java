package com.teracode.prototipogwt.backend.extras.extensions.resteasy;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.SerializationConfig.Feature;

/**
 * @author Maxi
 *
 */
public class HibernateAwareObjectMapper extends ObjectMapper {
	
	/**
	 * 
	 */
	public HibernateAwareObjectMapper() {
		HibernateModule hm = new HibernateModule();
		registerModule(hm);
		configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
	}
	
	/**
	 * @param prettyPrint
	 */
	public void setPrettyPrint(boolean prettyPrint) {
		configure(Feature.INDENT_OUTPUT, prettyPrint);
	}
}
