package com.teracode.prototipogwt.backend.extras.extensions.resteasy;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.BeanDescription;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.Serializers;
import org.codehaus.jackson.type.JavaType;
import org.hibernate.collection.PersistentCollection;
import org.hibernate.proxy.HibernateProxy;

public class HibernateSerializers implements Serializers {
	
	private static Logger logger = Logger.getLogger(HibernateSerializers.class);
	
    protected final int _moduleFeatures;
    
    public HibernateSerializers(int features) {
        _moduleFeatures = features;
    }

    public JsonSerializer<?> findSerializer(
            SerializationConfig config, JavaType type,
            BeanDescription beanDesc, BeanProperty beanProperty )
    {
        Class<?> raw = type.getRawClass();
        /* All Hibernate collection types (including maps!) implement this interface; but
         * it is not much more than a tag interface. So we will have at least 3 kinds
         * of subtypes to consider... Maps, Collections (List, Set); other, where last
         * type can still be serialized using iterator.
         */
        
        logger.debug("CLASE:" + raw.getName() + " -- PROPERTY:" + beanProperty);
        
        if (PersistentCollection.class.isAssignableFrom(raw)) {
        	
        	logger.debug("PROCESADA COMO COLLECTION");
            if (Map.class.isAssignableFrom(raw)) {
                // Let's just cast back to Map<K,V>, using whatever parametrization we have (if any)
                return new PersistentCollectionSerializer(type.widenBy(Map.class),
                        isEnabled(HibernateModule.Feature.FORCE_LAZY_LOADING));
            }

            if (Collection.class.isAssignableFrom(raw)) {
                // Lists are slightly more efficient to serialize, so:
                if (List.class.isAssignableFrom(raw)) {
                    type = type.widenBy(List.class);
                } else {
                    type = type.widenBy(Collection.class);
                }
                return new PersistentCollectionSerializer(type, isEnabled(HibernateModule.Feature.FORCE_LAZY_LOADING));
            }
            /* Other types could be supported in future, but for now this'll have
             * to do.
             */
        }

        if (HibernateProxy.class.isAssignableFrom(raw)) {
        	logger.debug("NO PROCESADA COMO PROXY");
            return new HibernateProxySerializer(false);
//            return new PersistentCollectionSerializer(type, isEnabled(Feature.FORCE_LAZY_LOADING));
        }
        return null;
    }

    public final boolean isEnabled(HibernateModule.Feature f) {
        return (_moduleFeatures & f.getMask()) != 0;
    }
}
