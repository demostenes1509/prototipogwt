package com.teracode.prototipogwt.backend.extras.extensions;

import java.lang.annotation.Annotation;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;
import org.jboss.resteasy.annotations.interception.SecurityPrecedence;
import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.core.ResourceMethod;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.UnauthorizedException;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.teracode.prototipogwt.backend.extras.annotations.security.Restrict;
import com.teracode.prototipogwt.backend.extras.util.BackendConstants;
import com.teracode.prototipogwt.backend.services.security.SecurityService;

/**
 * Intercepts access at the method or class level, and verifies whether the user has a token with the correct permissions.
 * @author Maxi
 */
@Provider
@ServerInterceptor
@SecurityPrecedence
public class MethodInterceptor implements PreProcessInterceptor, ApplicationContextAware {
	
	/**
	 * 
	 */
	private static Logger logger = Logger.getLogger(MethodInterceptor.class);	

	/**
	 * 
	 */
	private static ApplicationContext springContext;

	/* (non-Javadoc)
	 * @see org.jboss.resteasy.spi.interception.PreProcessInterceptor#preProcess(org.jboss.resteasy.spi.HttpRequest, org.jboss.resteasy.core.ResourceMethod)
	 */
	@Override
	public ServerResponse preProcess(HttpRequest request, ResourceMethod method) throws Failure, WebApplicationException {
		
		logger.info("Verifying if method '" + method.getMethod().getName() + "' is annotated with @Restrict");
		Annotation restrictAnnotation = method.getMethod().getAnnotation(Restrict.class);
		
		// Log request content for debugging purposes 
		// WARNING - if enabled, this breaks the application!
		//logContent(request);

		if (restrictAnnotation == null) {
			logger.debug("Verifying if class '" + method.getResourceClass().getSimpleName() + "' is annotated with @Restrict");
			restrictAnnotation = method.getResourceClass().getAnnotation(Restrict.class);
		}
		
		if (restrictAnnotation != null) {
			
			logger.info("Method or class is annotated with Restrict, so I will verify security on it.");
			logger.debug("Seeking token in HTTP Header.");
			List<String> tokens = request.getHttpHeaders().getRequestHeader(BackendConstants.REST_TOKEN);
			if (tokens == null || tokens.size() > 1) {
				throw new UnauthorizedException("Authentication token is missing");
			}

			String token = tokens.get(0);
			SecurityService securityService = springContext.getBean(SecurityService.class);
			logger.info("HASHHASH :"+securityService.hashCode());
			if (!securityService.hasPermissions(token, method)) {
				throw new UnauthorizedException("User has no permissions");
			}

			logger.info("Permissions OK !!");
		} 
		
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		springContext	= applicationContext; 		
	}
	
	/**
	 * Log request content for debugging purposes.
	 * 
	 * XXX Warning: using this will break the application. Only for debugging purposes!
	 */
	/*
	private void logContent(HttpRequest request) {
	
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		StringBuilder data = new StringBuilder();
		String line;
		try {
			
			while ((line = reader.readLine()) != null) {
				data.append(line);
			}
			
		} catch (IOException e) {
			// Nothing
		}
		
		logger.debug("Request content = " + data);
	}
	*/

}
