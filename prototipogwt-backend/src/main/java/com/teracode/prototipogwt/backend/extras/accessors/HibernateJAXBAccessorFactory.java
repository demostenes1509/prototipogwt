package com.teracode.prototipogwt.backend.extras.accessors;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;

import com.sun.xml.bind.AccessorFactory;
import com.sun.xml.bind.AccessorFactoryImpl;
import com.sun.xml.bind.v2.runtime.reflect.Accessor;

/**
 * @author Maxi
 *
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class HibernateJAXBAccessorFactory implements AccessorFactory {
	
	/**
	 * 
	 */
	private static Logger logger = Logger.getLogger(HibernateJAXBAccessorFactory.class);

    /**
     * 
     */
    private final AccessorFactory delegate;
    private Method hibernateInitializationCheck;

    /**
     * 
     */
    public HibernateJAXBAccessorFactory() {
        this(AccessorFactoryImpl.getInstance());
    }

    /**
     * @param delegate
     */
	public HibernateJAXBAccessorFactory(AccessorFactory delegate) {
        this.delegate = delegate;
        try {
            Class hibernate = Class.forName("org.hibernate.Hibernate");
            hibernateInitializationCheck = hibernate.getMethod("isInitialized", Object.class);
            logger.debug("Detected Hibernate: Enabled hiding of uninitialized lazy objects and collections during XML marshalling.");
        } catch(ClassNotFoundException e) {
            hibernateInitializationCheck = null;
            logger.warn("Hibernate was not detected: Disabled hiding of uninitialized lazy objects and collections during XML marshalling.");
        } catch(Exception e) {
            hibernateInitializationCheck = null;
            logger.warn("Detected Hibernate, but failed to enable hiding of uninitialized lazy objects and collections during XML marshalling.", e);
        }
    }

    /* (non-Javadoc)
     * @see com.sun.xml.bind.AccessorFactory#createFieldAccessor(java.lang.Class, java.lang.reflect.Field, boolean)
     */
	public Accessor createFieldAccessor(Class bean, Field field, boolean readOnly) throws JAXBException {
        Accessor accessor = delegate.createFieldAccessor(bean, field, readOnly);

        if(hibernateInitializationCheck == null) {
            return accessor;
        } else {
            return new HibernateJAXBAccessor(accessor, hibernateInitializationCheck);
        }       
    }

    /* (non-Javadoc)
     * @see com.sun.xml.bind.AccessorFactory#createPropertyAccessor(java.lang.Class, java.lang.reflect.Method, java.lang.reflect.Method)
     */
	public Accessor createPropertyAccessor(Class bean, Method getter, Method setter) throws JAXBException {
        Accessor accessor = delegate.createPropertyAccessor(bean, getter, setter);

        if(hibernateInitializationCheck == null) {
            return accessor;
        } else {
            return new HibernateJAXBAccessor(accessor, hibernateInitializationCheck);
        }
    }
}