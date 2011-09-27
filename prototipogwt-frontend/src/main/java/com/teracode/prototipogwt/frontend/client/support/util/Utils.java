package com.teracode.prototipogwt.frontend.client.support.util;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.ScriptElement;
import com.google.gwt.http.client.URL;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.Window;

/**
 * Misc string and formatting utilities.
 * 
 * @author Maxi
 * @author andran
 */
public class Utils {

	/**
	 * TODO javadoc needed!
	 * @param url
	 * @return
	 */
	public static Map<String, String> getParams(String url) {
		
		Map<String, String> map = new HashMap<String, String>();
		if (url != null && !url.isEmpty()) {
			
			url = getURLSection(url,false);
			if (!url.isEmpty()) {
				
				String[] args = url.split("&"); 
				for (String element : args) {
					int equalIndex = element.indexOf("="); 
					if (equalIndex == -1) {
						map.put(element, ""); 
					} else {
						map.put(element.substring(0, equalIndex), element.substring(equalIndex + 1));
					}
				}
			}
		}
		
		return map;
	}
	
	/**
	 * @param url
	 * @return
	 */
	public static String getToken(String url) {
		if (url != null && !url.isEmpty()) {
			url = getURLSection(url, true);
		}
		return url;
	}
	
	/**
	 * TODO javadoc needed
	 * @param url
	 * @param isToken
	 */
	private static String getURLSection(String url, boolean isToken) {
		
		url 		= URL.decode(url);
		int indexOf	= url.indexOf("access_token");
		int offset	= 0;
		
		if (indexOf != 0) {

			indexOf	= url.indexOf('?');
			if (indexOf >= 0) {
				offset = 1;
			}
		}
		
		if (indexOf < 0) {
			
			return isToken ? url : "";
		
		} else {
		
			if (isToken) {
				return url.substring(0,indexOf);
			} else {
				return url.substring(indexOf+offset,url.length());
			}
		}
	}

	/**
	 * Given an url (e.g. "www.myurl.com"), normalize its link into an array of two parts:<br>
	 * <ul>
	 * 		<li>A full href, such as http://www.myurl.com</li>
	 * 		<li>A display-friendly value, such as www.myurl.com</li>
	 * </url>
	 * The original url may have the prefix "http://", "https://" or none at all.
	 * 
	 * @param link The original link. With or without an "http://" or "https://" prefix.
	 * @return A String[] of two elements: the first is the href, and the second the display value.
	 * @throws IllegalArgumentException If the original link is null or the empty String or malformed such as "http://".
	 */
	public static String[] normalizeLink(String link) {
		
		if (link == null || "".equals(link))
			throw new IllegalArgumentException(Utils.class + "link argument cannot be empty or null: " + link);

		// TODO a regexp here would filter malformed urls in a more concise way
		
		// Normalize the link's href and title
		String linkHref  = link.toLowerCase();
		String linkTitle = linkHref;
		if (!(linkHref.startsWith("http://") || linkHref.startsWith("https://"))) {

			linkHref = "http://" + linkHref;
			
		} else {
			
			String[] tokens = linkHref.split("://");
			
			if (tokens.length != 2)
				throw new IllegalArgumentException(Utils.class + "malformed url: " + link);
			
			linkTitle = tokens[1];
				
		}
		
		// Remove trailing "/" from the title, if present
		if (linkTitle.endsWith("/")) {
		
			if (linkTitle.length() <= 1)
				throw new IllegalArgumentException(Utils.class + "malformed url: " + linkTitle);
			
			linkTitle = linkTitle.substring(0, linkTitle.length() - 1);
		}
		
		return new String[] { linkHref, linkTitle };
	}
	
	/**
	 * Enumerate all the properties of a javascript object in separate modal windows.
	 * 
	 * @param jso The javascript object.
	 */
	public static void enumerateJSProperties(JavaScriptObject jso) {
		
		if (jso != null) {
			JSONObject jsonObject = new JSONObject(jso);
			for (String key : jsonObject.keySet()) {
				
				Window.alert("key:" + key + " value:" + jsonObject.get(key));
				
			}
		}
	}

	/**
	 * @return Whether a given String is null or zero-length.
	 */
	public static boolean isEmpty(Object str) {
		return str == null || str.toString().length() == 0;
	}
	
	/**
	 * Tries to parse a String representing a Double value. 
	 * If the str is null or empty, return 0.
	 * 
	 * @param str The string to parse as a Double value.
	 * 
	 * @return The parsed value.
	 * @throws NumberFormatException If the String cannot be parsed as a Double.
	 */
	public static Double getDoubleOrZero(String str) {
		
		return isEmpty(str) ? 0.0d : Double.parseDouble(str); 
	}
	
	/**
	 * Tries to parse a String representing an Integer value. 
	 * If the str is null or empty, return 0.
	 * 
	 * @param str The string to parse as an Integer value.
	 * 
	 * @return The parsed value.
	 * @throws NumberFormatException If the String cannot be parsed as an Integer.
	 */
	public static Integer getIntegerOrZero(String str) {

		return isEmpty(str) ? 0 : Integer.parseInt(str); 
	}
	
	/**
	 * @param message
	 */
	public static void log(Object logger,String message) {
		log(logger,message,null);
	}
	
	/**
	 * @param message
	 * @param throwable
	 */
	public static void log(Object logger,String message,Throwable throwable) {
		String className=logger.getClass().getName();
		message=className+": "+message;
		GWT.log(message,throwable);		
	}
	
	/**
	 * @param div
	 * @param src
	 */
	public static void addScript(Element div, String src) {
		
		ScriptElement script = Document.get().createScriptElement();
		script.setSrc(src);
		div.appendChild(script);
	}
	
} 

