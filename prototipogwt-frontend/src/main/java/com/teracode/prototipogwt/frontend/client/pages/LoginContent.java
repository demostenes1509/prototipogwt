package com.teracode.prototipogwt.frontend.client.pages;


import static com.google.gwt.query.client.GQuery.$;

import java.util.Map;

import javax.validation.ValidatorFactory;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.shared.ResettableEventBus;
import com.google.gwt.query.client.Function;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.inject.Inject;
import com.teracode.prototipogwt.domain.usecasesdto.LoginRequest;
import com.teracode.prototipogwt.frontend.client.events.login.LoginResponseEvent;
import com.teracode.prototipogwt.frontend.client.pages.publicarea.PublicPageSecond;
import com.teracode.prototipogwt.frontend.client.restservices.LoginService;
import com.teracode.prototipogwt.frontend.client.support.composite.Content;
import com.teracode.prototipogwt.frontend.client.support.composite.InvalidParamsException;
import com.teracode.prototipogwt.frontend.client.support.util.GlobalMessages;
import com.teracode.prototipogwt.frontend.client.support.util.SpecificField;
import com.teracode.prototipogwt.frontend.client.support.util.ValidatorUtils;

/**
 * @author Maxi
 */
public class LoginContent extends Content implements Editor<LoginRequest> {
	 
	private static IndexPagePanelUiBinder uiBinder = GWT.create(IndexPagePanelUiBinder.class);

	interface IndexPagePanelUiBinder extends UiBinder<HTMLPanel, LoginContent> {}
	
	@UiField TextBox email;
	@UiField PasswordTextBox password;
	@UiField InputElement submitField;
	
	interface Driver extends SimpleBeanEditorDriver<LoginRequest, LoginContent> {}	
	Driver driver = GWT.create(Driver.class);
	
	@Inject
	public LoginContent(final ResettableEventBus eventBus,
						final ValidatorFactory validatorFactory,
						final LoginService loginService,
						final PublicPageSecond publicPage) { 
		
		super(publicPage);
		
		HTMLPanel htmlPanel = uiBinder.createAndBindUi(this);
		initWidget(htmlPanel);
		driver.initialize(this);
		driver.edit(new LoginRequest());
		
		initializeWidgets(validatorFactory,loginService);
		addHandlers(eventBus);
	}

	public void initializeWidgets(final ValidatorFactory validatorFactory,final LoginService loginService) {
		$(submitField).click(new Function() {
			@Override
			public void f(Element e) {
				LoginRequest loginRequest = driver.flush();
//				ValidatorUtils validator = new ValidatorUtils(GlobalMessages.getInstance());
				ValidatorUtils validator = new ValidatorUtils(SpecificField.getInstance());
				if(validator.validate(validatorFactory, loginRequest)) {
					loginService.login(loginRequest);
				}
			}
		});
	}
	
	private void addHandlers(ResettableEventBus eventBus) {
		eventBus.addHandler(LoginResponseEvent.TYPE,new LoginResponseEvent.LoginResponseEventHandler() {
			@Override
			public void onAfterLoginResponse(LoginResponseEvent event) {
//				LoginResponse loginResponse = event.getLoginResponse();
			}
		});
	}

	@Override
	public void setParameters(Map<String, String> params) throws InvalidParamsException {
	}

}
