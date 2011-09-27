package com.teracode.prototipogwt.frontend.client.pages.publicarea;

import static com.google.gwt.query.client.GQuery.$;

import java.util.Map;

import org.fusesource.restygwt.client.Resource;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.OptionElement;
import com.google.gwt.dom.client.SelectElement;
import com.google.gwt.query.client.Function;
import com.google.gwt.user.client.History;
import com.google.inject.Inject;
import com.teracode.prototipogwt.frontend.client.PrototipoGWT;
import com.teracode.prototipogwt.frontend.client.pages.template.BasePage;
import com.teracode.prototipogwt.frontend.client.support.restservices.CustomResource;

/**
 * @author Maxi 
 */
public class PublicPage extends BasePage {
	
	final SelectElement localeSelect = Document.get().createSelectElement();
	
	@Inject
	public PublicPage() {
	
		localeSelect.appendChild(createLocale("en","English"));
		localeSelect.appendChild(createLocale("es","Espa\u00F1ol"));
		
		// Fire when option is selected
		$(localeSelect).change(new Function() {
			@Override
			public void f(Element e) {
				int selectedIndex = localeSelect.getSelectedIndex();
				String option=(selectedIndex==0)?"en":"es";
				
				Resource resource = new CustomResource(PrototipoGWT.LOGIN_PAGE);
				resource = resource.addQueryParam("locale",option);
				
				History.newItem( resource.getUri() );
			}
		});
		super.setNorth(localeSelect);
	}
	
	private OptionElement createLocale(String value,String label) {
		OptionElement option = Document.get().createOptionElement();
		option.setId(value);
		option.setAttribute("name", value);
		option.setInnerText(label);
		return option;
	}
	
	@Override
	public void setParameters(Map<String, String> params) {
		super.setParameters(params);
		String locale=params.get("locale");
		if("en".equals(locale)) {
			localeSelect.setSelectedIndex(0);
		}
		if("es".equals(locale)) {
			localeSelect.setSelectedIndex(1);
		}
	}
	
	
}

