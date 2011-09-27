package com.teracode.prototipogwt.frontend.client.pages.template;

import static com.google.gwt.query.client.GQuery.$;

import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

/**
 * @author Maxi 
 */
public abstract class BasePage extends Composite { 
	 
	private static IndexPagePanelUiBinder uiBinder = GWT.create(IndexPagePanelUiBinder.class);
	interface IndexPagePanelUiBinder extends UiBinder<HTMLPanel, BasePage> {}
	
	@UiField DivElement northDiv;
	@UiField DivElement southDiv;
	@UiField DivElement eastDiv;
	@UiField DivElement westDiv;
	@UiField DivElement centerDiv;
	
	private Map<String,String> params;
	
	public BasePage() {
		HTMLPanel htmlPanel = uiBinder.createAndBindUi(this);
		initWidget(htmlPanel);
	}
	
	protected void setNorth(Element element) {
		addElement(northDiv, element);
	}
	
	protected void setSouth(Element element) {
		addElement(southDiv, element);
	}
	
	protected void setEast(Element element) {
		addElement(eastDiv, element);
	}
	
	protected void setWest(Element element) {
		addElement(westDiv, element);
	}
	
	public void setCenter(Element element) {
		addElement(centerDiv, element);
	}
	
	private void addElement(Element parent, Element child) {
		$(parent).replaceWith(child);
	}
	
	public void setParameters(Map<String,String> params) {
		this.params = params;
	}
	
}
