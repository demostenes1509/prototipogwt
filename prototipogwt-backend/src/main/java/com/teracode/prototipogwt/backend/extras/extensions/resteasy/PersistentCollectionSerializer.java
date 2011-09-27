package com.teracode.prototipogwt.backend.extras.extensions.resteasy;

import java.io.IOException;

import org.hibernate.collection.PersistentCollection;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ResolvableSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.type.JavaType;

/**
 * Wrapper serializer used to handle aspects of lazy loading that can be used
 * for Hibernate collection datatypes.
 */
public class PersistentCollectionSerializer extends JsonSerializer<PersistentCollection> implements ResolvableSerializer {
    
	protected final boolean _forceLazyLoading;
    
    /**
     * This is the nominal type used to locate actual serializer to use
     * for contents, if this collection is to be serialized.
     */
    protected final JavaType _serializationType;

    /**
     * Serializer to which we delegate if serialization is not blocked.
     */
    protected JsonSerializer<Object> _serializer;

    /*
    /**********************************************************
    /* Life cycle
    /**********************************************************
     */
    
    public PersistentCollectionSerializer(JavaType serializationType, boolean forceLazyLoading) {
        _serializationType = serializationType;
        _forceLazyLoading = forceLazyLoading;
    }

    /**
     * We need to resolve actual serializer after the fact since Serializers API
     * does not allow callbacks (to avoid infinite loops)
     */
    @SuppressWarnings("deprecation")
	public void resolve(SerializerProvider provider) throws JsonMappingException {
        _serializer = provider.findValueSerializer(_serializationType);
    }
    
    /*
    /**********************************************************
    /* JsonSerializer impl
    /**********************************************************
     */
    
    @Override
    public void serialize(PersistentCollection value, JsonGenerator jgen, SerializerProvider provider)
        throws IOException, JsonProcessingException
    {
        // If lazy-loaded, not yet loaded, may serialize as null?
        if (!_forceLazyLoading && !value.wasInitialized()) {
            jgen.writeNull();
            return;
        }
        _serializer.serialize(value, jgen, provider);
    }

    public void serializeWithType(PersistentCollection value, JsonGenerator jgen, SerializerProvider provider,
            TypeSerializer typeSer)
        throws IOException, JsonProcessingException
    {
        if (!_forceLazyLoading && !value.wasInitialized()) {
            jgen.writeNull();
            return;
        }
        _serializer.serializeWithType(value, jgen, provider, typeSer);
    }

}
