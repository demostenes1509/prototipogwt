package com.teracode.prototipogwt.frontend.server.redirector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.log4j.Logger;

import com.teracode.prototipogwt.domain.resources.RestProperties;

/**
 * @author Maxi
 */
public class ServletRedirector extends HttpServlet {
    
	private static final long serialVersionUID = -9135272315891861802L;
	
	private static Logger logger = Logger.getLogger(ServletRedirector.class);
	
	/*
	 * Content types we do not want to cache.
	 */
	private static final String CONTENT_TYPE_JSON	 	= "application/json";
	private static final String CONTENT_TYPE_XML	 	= "application/xml";
	/*
	 * Cache headers. 
	 */
	private static final String CACHE_CONTROL_PARAM		= "Cache-Control";
	private static final String CACHE_CONTROL_NOCACHE	= "no-cache";
	private static final Set<String> NON_CACHEABLE_CONTENT_TYPES;
	static {
		
		String[] types = { CONTENT_TYPE_JSON, CONTENT_TYPE_XML };
		NON_CACHEABLE_CONTENT_TYPES = new HashSet<String>(Arrays.asList(types));
		
	}
	
	/**
	 * 
	 */
	private static RestProperties restTestProperties	= null;

	private static final String CHARSET = HTTP.DEFAULT_CONTENT_CHARSET;
	
	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 */
	public void init(ServletConfig config)throws ServletException{
        
		super.init(config);
        try {
        
        	logger.info("Initiating ServletRedirector Servlet ( ONLY DEVELOPMENT MODE )");
			restTestProperties = RestProperties.getRestProperties();
		
        } catch (Exception e) {
        	throw new ServletException("Error initiating ServletRedirector:",e);
		}
    }
	
    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
    	
    	HttpGet httpGet	= new HttpGet();
    	doHTTPOperation(request, response, httpGet);
    }

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
    	
    	HttpDelete httpDelete = new HttpDelete();
    	doHTTPOperation(request, response, httpDelete);
    }

    /* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPut(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpPut httpPut	= new HttpPut();
    	String requestContent = getStreamContent(request.getInputStream());
    	if (!requestContent.isEmpty()) {
    		logger.debug("Put Content:"+requestContent);
    		httpPut.setEntity(new StringEntity(requestContent, CHARSET));
    	}
    	doHTTPOperation(request, response, httpPut);
	}
	
    /* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPut(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpPost httpPost = new HttpPost();
    	String requestContent = getStreamContent(request.getInputStream());
    	if (!requestContent.isEmpty()) {
    		logger.debug("Post Content:"+requestContent);
    		httpPost.setEntity(new StringEntity(requestContent, CHARSET));
    	}
    	doHTTPOperation(request, response, httpPost);
	}
    
    /**
     * @param request
     * @param response
     * @param requestBase
     * @throws ServletException
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
	private void doHTTPOperation(HttpServletRequest request, HttpServletResponse response, HttpRequestBase requestBase) throws ServletException,IOException {
    	
    	String newURL = getURL(request);
    	
    	String params = getParams(request);
    	if (params.length() > 0) {
    		params = "?" + params;
    	}
    	String finalUrl = newURL + params;
    	
    	logger.info("Sending "+requestBase.getMethod()+" request to :" + finalUrl);
    	requestBase.setURI(URI.create(finalUrl));
    	
    	for (Enumeration<String> e = request.getHeaderNames() ; e.hasMoreElements() ;) {
    		String headerName	= e.nextElement().toString().toLowerCase();
    		String headerValue	= request.getHeader(headerName);
    		if(getValidHeaderParameters().contains(headerName)) {
    			addHeaderParameter(requestBase, headerName, headerValue);
    		}
    	}
    	
    	HttpClient httpclient		= new DefaultHttpClient();
    	HttpResponse httpResponse	= httpclient.execute(requestBase);
    	HttpEntity httpEntity		= httpResponse.getEntity();
    	
    	if (httpEntity != null) {
    		
    		String contentType = httpEntity.getContentType().getValue();
    		response.setContentType(contentType);
    		
    		// Avoid caching the response for some content types
    		if (NON_CACHEABLE_CONTENT_TYPES.contains(contentType)) {
    			response.setHeader(CACHE_CONTROL_PARAM, CACHE_CONTROL_NOCACHE);
    		}
    		
	        String responseContent		= getStreamContent(httpEntity.getContent());
	        PrintWriter out				= response.getWriter();
	        logger.debug("Message:"+responseContent);
	        out.println(responseContent);
	        out.close();
	        logger.info("Getting "+requestBase.getMethod()+" response to :"+newURL);
    	
    	} else {
    		logger.error("Could not open remote connection to:"+newURL);    		
    	}
    }
    
    /**
     * @param request
     * @return
     * @throws UnsupportedEncodingException 
     */
    @SuppressWarnings("unchecked")
    private String getParams(HttpServletRequest request) throws UnsupportedEncodingException {
    	
    	String params = "";
		int index = 0;
    	for (Object entry : request.getParameterMap().entrySet()) {
    		
			Map.Entry<String, Object> en = (Entry<String, Object>) entry;
			
			String encKey	=getISO(en.getKey());
			String key		=URLUtils.encodeQueryNameOrValue(encKey);
			String encValue	=getISO(((String[])en.getValue())[0]);
			String value	=URLUtils.encodeQueryNameOrValue(encValue);
			
    		logger.info("Encoded Key	:"+encKey+" Decoded Key:"+key);
    		logger.info("Encoded Value	:"+encValue+" Decoded Key:"+value);
    		if (index > 0) {
    			params += "&";
    		}
    		params += key + "=" + value;
    		index++;
    	}
    	
    	return params;
    }
    
    private String getISO(String str) throws UnsupportedEncodingException {
    	return new String(str.getBytes(HTTP.DEFAULT_CONTENT_CHARSET),HTTP.UTF_8);
    }
    
    /**
     * @param inputStream
     * @return
     * @throws IOException 
     */
    private String getStreamContent(InputStream inputStream) throws IOException {

    	BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, CHARSET));
        String content	  = "";

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
        	content += inputLine;
        }
        in.close();
        
        return content;
    }
    
	/**
     * @param request
     * @return
     */
    private String getURL(HttpServletRequest request) throws ServletException {
    	
    	String url=request.getRequestURI();
    	int indexOf=url.indexOf("/rest/");
    	url=url.substring(indexOf+6,url.length());
    	url=restTestProperties.getRestHttpContext() + "/" + url;
    	
    	return url;
    }
    
    /**
     * @param requestBase
     * @param name
     * @param value
     */
    private void addHeaderParameter(HttpRequestBase requestBase, String name, String value) {
    	if (value != null) {
    		requestBase.setHeader(name, value);
    	} else {
    		logger.warn("Request has no HTTP Header '" + name + "' parameter present !!!");
    	}
    }
    
    /**
     * @return
     */
    private List<String> getValidHeaderParameters() {
    	List<String> validHeaderParams=new ArrayList<String>();
    	
//		validHeaderParams.add("Host");
//		validHeaderParams.add("User-Agent");
		validHeaderParams.add("accept");
//		validHeaderParams.add("Accept-Language");
//		validHeaderParams.add("Accept-Encoding");
//		validHeaderParams.add("Accept-Charset");
//		validHeaderParams.add("Connection");
//		validHeaderParams.add("Referer");
//		validHeaderParams.add("Cache-Control");
//		validHeaderParams.add("X-HTTP-Method-Override");
		validHeaderParams.add("content-type");
//		validHeaderParams.add("Content-Length");
//		validHeaderParams.add("Pragma");
    	
    	return validHeaderParams;
    }
}

