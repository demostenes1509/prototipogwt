package com.teracode.prototipogwt.backend.extras.accessors;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;

import com.sun.xml.bind.api.AccessorException;
import com.sun.xml.bind.v2.runtime.JAXBContextImpl;
import com.sun.xml.bind.v2.runtime.reflect.Accessor;

/**
 * @author Maxi
 * 
 * @param <B>
 * @param <V>
 */
public class HibernateJAXBAccessor<B, V> extends Accessor<B, V> {
	
	/**
	 * 
	 */
	private static Logger logger = Logger.getLogger(HibernateJAXBAccessor.class);

	/**
	 * 
	 */
	private Accessor<B, V> delegate;
	private final Method hibernateInitializationCheck;

	/**
	 * @param delegate
	 * @param hibernateInitializationCheck
	 */
	protected HibernateJAXBAccessor(Accessor<B, V> delegate, Method hibernateInitializationCheck) {
		super(delegate.getValueType());

		if (hibernateInitializationCheck == null) {
			throw new IllegalArgumentException("hibernateInitializationCheck must not be null");
		}

		this.delegate = delegate;
		this.hibernateInitializationCheck = hibernateInitializationCheck;
	}

	/* (non-Javadoc)
	 * @see com.sun.xml.bind.v2.runtime.reflect.Accessor#optimize(com.sun.xml.bind.v2.runtime.JAXBContextImpl)
	 */
	@Override
	public Accessor<B, V> optimize(JAXBContextImpl context) {
		delegate = delegate.optimize(context);
		return this;
	}

	/* (non-Javadoc)
	 * @see com.sun.xml.bind.v2.runtime.reflect.Accessor#get(java.lang.Object)
	 */
	@Override
	public V get(B bean) throws AccessorException {
		return hideLazy(delegate.get(bean));
	}

	/* (non-Javadoc)
	 * @see com.sun.xml.bind.v2.runtime.reflect.Accessor#set(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void set(B bean, V value) throws AccessorException {
		delegate.set(bean, value);
	}

	/**
	 * @param value
	 * @return
	 */
	protected V hideLazy(V value) {
		try {
			boolean isInitialized = (Boolean) hibernateInitializationCheck
					.invoke(null, new Object[] { value });
			if (isInitialized) {
				return value;
			} else {
				return null;
			}
		} catch (Exception e) {
			logger.error("Failed to determine state of Hibernate object or collection, assuming " + value + " is initialized", e);
			return null;
		}
	}
}