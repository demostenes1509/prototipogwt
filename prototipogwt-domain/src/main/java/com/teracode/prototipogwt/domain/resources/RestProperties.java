package com.teracode.prototipogwt.domain.resources;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author Maxi
 *
 */
public class RestProperties extends Properties { 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2330379864843549854L;
	
	private static RestProperties restProperties = null;
	
	/**
	 * @return
	 * @throws Exception
	 */
	public static RestProperties getRestProperties() throws Exception {
		if(restProperties==null) {
			restProperties=new RestProperties("/com/teracode/prototipogwt/sharedresources/rest.properties");
		}
		return restProperties;
	}
	
	/**
	 * @throws Exception
	 */
	private RestProperties(String propertiesFile) throws Exception {
		InputStream inputStream=this.getClass().getResourceAsStream(propertiesFile);
		if(inputStream==null) {
			throw new RuntimeException("Can't open "+propertiesFile);
		}
		super.load(inputStream);
		inputStream.close();
		if(super.isEmpty()) {
			throw new RuntimeException("Test couldn't load rest properties");
		}
	}
	

	/**
	 * @return
	 */
	public String getBackendContext() {
		return getProperty("pro.gwt.backend.context");
	}
	
	/**
	 * @return
	 */
	public String getJettyServer() {
		return getProperty("pro.gwt.jetty.server");
	}
	
	/**
	 * @return
	 */
	public int getJettyHttpPort() {
		return Integer.parseInt(getProperty("pro.gwt.jetty.http.port"));
	}
	
	/**
	 * @return
	 */
	public int getJettyHttpsPort() {
		return Integer.parseInt(getProperty("pro.gwt.jetty.https.port"));
	}
	
	/**
	 * @return
	 */
	public String getRestServer() {
		return getProperty("pro.gwt.rest.server");
	}
	
	/**
	 * @return
	 */
	public int getRestHttpPort() {
		return Integer.parseInt(getProperty("pro.gwt.rest.http.port"));
	}
	
	/**
	 * @return
	 */
	public int getRestHttpsPort() {
		return Integer.parseInt(getProperty("pro.gwt.rest.https.port"));
	}

	/**
	 * @return
	 */
	public String getWebContext() {
		return getProperty("pro.gwt.web.server");
	}
	
	/**
	 * @return
	 */
	public int getWebHttpPort() {
		return Integer.parseInt(getProperty("pro.gwt.web.http.port"));
	}
	
	/**
	 * @return
	 */
	public int getWebHttpsPort() {
		return Integer.parseInt(getProperty("pro.gwt.web.https.port"));
	}
	
	/**
	 * @return
	 */
	public String getJettyHttpContext() {
		return "http://"+getJettyServer()+":"+getJettyHttpPort()+"/"+getBackendContext();
	}
	
	/**
	 * @return
	 */
	public String getJettyHttpsContext() {
		return "https://"+getJettyServer()+":"+getJettyHttpsPort()+"/"+getBackendContext();
	}
	
	/**
	 * @return
	 */
	public String getRestHttpContext() {
		return "http://"+getRestServer()+":"+getRestHttpPort()+"/"+getBackendContext();
	}
	
	/**
	 * @return
	 */
	public String getRestHttpsContext() {
		return "https://"+getRestServer()+":"+getRestHttpsPort()+"/"+getBackendContext();
	}
	
	/**
	 * @return
	 */
//	public String getWebHttpContext() {
//		return "http://"+getWebServer()+":"+getWebHttpPort()+"/"+getBackendContext();
//	}
	
//	/**
//	 * @return
//	 */
//	public String getWebHttpsContext() {
//		return "https://"+getRestServer()+":"+getWebHttpsPort()+"/citibeneficios-server";
//	}
//	
//	/**
//	 * @return
//	 */
//	public String getWebHttpServer() {
//		return "http://"+getRestServer()+":"+getWebHttpPort()+getWebContext();
//	}
	

}
