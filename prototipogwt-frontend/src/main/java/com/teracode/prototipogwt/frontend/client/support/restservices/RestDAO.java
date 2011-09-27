package com.teracode.prototipogwt.frontend.client.support.restservices;

import java.util.HashMap;
import java.util.Map;

import org.fusesource.restygwt.client.JsonEncoderDecoder;
import org.fusesource.restygwt.client.Resource;

import com.google.gwt.json.client.JSONValue;
import com.teracode.prototipogwt.domain.domainmodel.EntityBase;
import com.teracode.prototipogwt.domain.dto.EntityBaseSet;
import com.teracode.prototipogwt.domain.responses.LongResponse;
import com.teracode.prototipogwt.frontend.client.support.callback.CustomJsonCallback;

/**
 * @author Maxi
 *
 */
public abstract class RestDAO<E extends EntityBase,F extends EntityBaseSet<E>> extends RestServices{
	
	/**
	 * Get an entity by Id.
	 * 
	 * @param id
	 * @return The entity if found; otherwise it will return the error code 404 NOT FOUND.
	 */
	public void get(long id) {
		Resource resource = getRestResource(getRestContext()+"/"+id);
		
		getConfiguredMethod(resource.get()).send(new CustomJsonCallback() {
			@Override
			public void run(JSONValue response) {
				E e = getEntityJEDInstance().decode(response);
				onEntityObtained(e);
			}
		});		
	}
	
	/**
	 * @param e
	 */
	public void save(E e) {
		Resource resource = getRestResource(getRestContext());
		JSONValue request = getEntityJEDInstance().encode(e);
		
		getConfiguredMethod(resource.post()).json(request).send(new CustomJsonCallback() {
			@Override
			public void run(JSONValue response) {
				E e = getEntityJEDInstance().decode(response);
				onEntitySaved(e);
			}
		});		
	}
	
	/**
	 * @param e
	 */
	public void update(E e) {
		Resource resource = getRestResource(getRestContext()+"/"+e.getId());
		JSONValue request = getEntityJEDInstance().encode(e);
		
		getConfiguredMethod(resource.put()).json(request).send(new CustomJsonCallback() {
			@Override
			public void run(JSONValue response) {
				E e = getEntityJEDInstance().decode(response);
				onEntityUpdated(e);
			}
		});		
	}
	
	/**
	 * @param e
	 */
	public void delete(long id) {
		Resource resource = getRestResource(getRestContext()+"/"+id);
		
		getConfiguredMethod(resource.delete()).send(new CustomJsonCallback() {
			@Override
			public void run(JSONValue response) {
				LongResponse longResponse=getLongResponseJED().decode(response);
				onEntityDeleted(longResponse.getValue());
			}
		});		
	}
	
	/**
	 * 
	 */
	public void find() {
		Map<String,String> map=new HashMap<String,String>();
		find(map);
	}
	
	/**
	 * @param param
	 * @param value
	 */
	public void find(String param,String value) {
		Map<String,String> map=new HashMap<String,String>();
		map.put(param, value);
		find(map);
	}
	
	/**
	 * @param params
	 */
	public void find(Map<String,String> params) {
		Resource resource = getRestResource(getRestContext());
		for(String param:params.keySet()) {
			resource = resource.addQueryParam(param, params.get(param));			
		}
		
		getConfiguredMethod(resource.get()).send(new CustomJsonCallback() {
			@Override
			public void run(JSONValue response) {
				F f = getEntityBaseSetJEDInstance().decode(response);
				onEntityListObtained(f);
			}
		});				
	}
	
	public abstract String getRestContext();
	
	public abstract JsonEncoderDecoder<E> getEntityJEDInstance();
	
	public abstract JsonEncoderDecoder<F> getEntityBaseSetJEDInstance();
	
	public abstract void onEntityObtained(E e);
	public abstract void onEntityUpdated(E e);
	public abstract void onEntitySaved(E e);
	public abstract void onEntityDeleted(Long id);
	public abstract void onEntityListObtained(F f);
	
}
