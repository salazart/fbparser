package com.sz.fb.services;

import java.util.Properties;

import org.w3c.dom.Document;
import org.w3c.dom.html.HTMLFormElement;
import org.w3c.dom.html.HTMLInputElement;

public class AuthService {
	private static final String FB_PASS = "fbPass";
	private static final String FB_LOGIN = "fbLogin";
	
	private Document doc;
	private Properties properties;

	public AuthService(Document doc, Properties properties) {
		super();
		this.doc = doc;
		this.properties = properties;
	}
	
	public void auth(){
		HTMLInputElement email = (HTMLInputElement) doc.getElementById("email");
		HTMLInputElement pass = (HTMLInputElement) doc.getElementById("pass");
		HTMLFormElement form = (HTMLFormElement) doc.getElementById("login_form");
		
		if(email != null && pass != null && form != null){
			email.setValue(properties.getProperty(FB_LOGIN));
			pass.setValue(properties.getProperty(FB_PASS));
			form.submit();
		}
	}
}
