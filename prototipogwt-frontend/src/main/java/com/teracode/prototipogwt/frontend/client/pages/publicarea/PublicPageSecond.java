package com.teracode.prototipogwt.frontend.client.pages.publicarea;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.SpanElement;
import com.google.inject.Inject;

/**
 * @author Maxi 
 */
public class PublicPageSecond extends PublicPage {
	
	@Inject
	public PublicPageSecond() {
		
		SpanElement southSpan = Document.get().createSpanElement();
		southSpan.setInnerText("Public South");
		super.setSouth(southSpan);
	}
}
