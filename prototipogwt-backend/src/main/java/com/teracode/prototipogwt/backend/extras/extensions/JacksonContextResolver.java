package com.teracode.prototipogwt.backend.extras.extensions;

import java.text.SimpleDateFormat;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import com.teracode.prototipogwt.backend.extras.extensions.resteasy.HibernateAwareObjectMapper;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class JacksonContextResolver implements ContextResolver<ObjectMapper> {
	
	// The date format must be exactly this, as described in http://groups.google.com/group/restygwt/browse_thread/thread/fa9436a901665d4a/e4cdce28def2c0d4?lnk=raot
	private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZZZ";
	
    private ObjectMapper objectMapper;

    public JacksonContextResolver() throws Exception {
        
    	this.objectMapper = new HibernateAwareObjectMapper();
 
        /*
         * The following code was taken from http://stackoverflow.com/questions/4428109/jersey-jackson-json-date-serialization-format-problem-how-to-change-the-forma/5234682#5234682
         * It's necessary to fix the serialization of Dates. 
         */
    	SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        SerializationConfig serConfig = this.objectMapper.getSerializationConfig();
        serConfig.setDateFormat(sdf);
        DeserializationConfig deserializationConfig = this.objectMapper.getDeserializationConfig();
        deserializationConfig.setDateFormat(sdf);
        this.objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
    }
    
    public ObjectMapper getContext(Class<?> objectType) { return objectMapper; }
    
}