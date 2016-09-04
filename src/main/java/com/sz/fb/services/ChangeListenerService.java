package com.sz.fb.services;

import java.util.Properties;

import org.w3c.dom.Document;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.scene.web.WebEngine;

public class ChangeListenerService implements ChangeListener<State>{
	private WebEngine webEngine;
	private Properties properties;

	public ChangeListenerService(WebEngine webEngine, Properties properties) {
		super();
		this.webEngine = webEngine;
		this.properties = properties;
	}
	
	@Override
	public void changed(ObservableValue<? extends State> arg0, State arg1, State arg2) {
		if(arg2 == State.SUCCEEDED){
			Document doc = webEngine.getDocument();
			
			AuthService authService = new AuthService(doc, properties);
			authService.auth();

			System.out.println("OK");

		}
	}
}
