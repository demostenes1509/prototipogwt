package com.teracode.prototipogwt.backend.providers.base;

import org.apache.log4j.Logger;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Settings;
import org.hibernate.ejb.Ejb3Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.security.SslSocketConnector;
import org.mortbay.jetty.webapp.WebAppContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.teracode.prototipogwt.domain.resources.RestProperties;

/**
 * @author Maxi
 *
 */
public class RestTest {

	/**
	 * 
	 */
	private static Logger logger = Logger.getLogger(RestTest.class);
	
	/**
	 * 
	 */
	private static RestProperties restProperties;

	/**
	 * 
	 */
	private Server server = new Server();
	
	/**
	 * @throws Exception
	 */
	@BeforeMethod
	public void beforeMethod() throws Exception {
		
		logger.info("Dropping and Creating Database");
		Ejb3Configuration cfg = new Ejb3Configuration().configure("testdb",null);
		
		Configuration hbmcfg = cfg.getHibernateConfiguration();
		
		Settings settings = hbmcfg.buildSettings();
		SchemaExport schemaExport = new SchemaExport(hbmcfg,settings);

		if(settings.isAutoCreateSchema()) {
			schemaExport.drop(false, true);
		}
		if(settings.isAutoDropSchema()) {
			schemaExport.create(false, true);
		}
	}
	
	/**
	 * @throws Exception
	 */
	@BeforeTest
	public void beforeTest() throws Exception {
		
		// Only for interactive debugging 
		if(shouldStartJetty()==false) {
			return;
		}

		logger.info("Starting Jetty Servlet Container");
		Connector connector = new SelectChannelConnector();
		connector.setHost(restProperties.getJettyServer()); 
		connector.setPort(restProperties.getJettyHttpPort());
		server.addConnector(connector);
		logger.info("Adding HTTP connector in:"+restProperties.getJettyServer()+":"+restProperties.getJettyHttpPort());

		SslSocketConnector  securedConnector = new SslSocketConnector();
		securedConnector.setHost(restProperties.getJettyServer());
		securedConnector.setPort(restProperties.getJettyHttpsPort());
		securedConnector.setPassword("changeit");
		securedConnector.setKeyPassword("changeit"); 
		securedConnector.setTrustPassword("changeit");
		server.addConnector(securedConnector);
		logger.info("Adding HTTPS connector in:"+restProperties.getJettyServer()+":"+restProperties.getJettyHttpsPort());
		
		WebAppContext wac = new WebAppContext();
		wac.setContextPath("/"+restProperties.getBackendContext());
		wac.setWar("src/main/webapp");
		server.setHandler(wac);
		server.setStopAtShutdown(true);

		server.start();
		logger.info("===========================================================");
		logger.info("Jetty Started Successfully !");
		logger.info("===========================================================");
		 
	}
	
	/**
	 * @return
	 */
	public boolean shouldStartJetty() {
		return true;
	}
	
	/**
	 * @throws Exception 
	 */
	@BeforeSuite
	public void beforeSuite() throws Exception {
		
		logger.info("Changing ClassLoader for persistence.xml");
		final Thread currentThread = Thread.currentThread();
		final ClassLoader saveClassLoader = currentThread.getContextClassLoader();
		currentThread.setContextClassLoader(new ClassLoaderProxy(saveClassLoader));
		
		logger.info("Loading Testing variables");
		restProperties = RestProperties.getRestProperties(); 
	}
	
	/**
	 * @throws Exception
	 */
	@AfterTest
	public void afterTest() throws Exception {
		server.stop();
	}

	/**
	 * @return
	 */
	public String getJettyHttpContext() {
		return restProperties.getJettyHttpContext();
	}
	
	/**
	 * @return
	 */
	public String getJettyHttpsContext() {
		return restProperties.getJettyHttpsContext();
	}
	
	
}

