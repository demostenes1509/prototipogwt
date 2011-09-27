package com.teracode.prototipogwt.frontend.client.domain;

import com.teracode.prototipogwt.frontend.client.PrototipoGWTTestGWT;
import com.teracode.prototipogwt.frontend.client.pages.LoginContent;

public class RoleTestGWT extends PrototipoGWTTestGWT {
	public void testGet() {
		LoginContent loginContent = getInjector().getLoginContent();
	}
}
