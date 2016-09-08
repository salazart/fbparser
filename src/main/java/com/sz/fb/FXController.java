package com.sz.fb;

import java.io.IOException;
import java.util.Properties;

import org.w3c.dom.Document;
import org.w3c.dom.html.HTMLInputElement;

import com.sz.fb.services.ChangeListenerService;
import com.sz.fb.services.FindSaveTask;
import com.sz.fb.services.FindService;
import com.sz.fb.services.HtmlService;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;

public class FXController {
	private static final String CONFIG_PROPERTIES = "config.properties";
	@FXML
	private Button goButton;
	@FXML
	private Button findButton;
	@FXML
	private Button saveButton;

	@FXML
	private TextField urlField;

	@FXML
	private WebView webView;
	
	private Properties properties;
	
	public FXController() {
		properties = new Properties();
		try {
			properties.load(this.getClass().getClassLoader().getResourceAsStream(CONFIG_PROPERTIES));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void goButtonClick() {
		
		String url = "https://www.facebook.com/";
		if (urlField.getText().isEmpty()) {
			urlField.setText(url);
		}
		System.out.println("go");
		webView.getEngine().load(urlField.getText());
		webView.getEngine().getLoadWorker().stateProperty().addListener(
    			new ChangeListenerService(webView.getEngine(), properties));
	}

	@FXML
	private void saveButtonClick() {
		System.out.println("save");
		Document document = webView.getEngine().getDocument();
		String textDocument = HtmlService.documentToString(document);

		FindService findService = new FindService(document);
		String link = findService.getUserLink();
		System.out.println(link);
	}

	@FXML
	private void findButtonClick() {
		System.out.println("find");
		Document document = webView.getEngine().getDocument();
		
//		HTMLInputElement findElement = (HTMLInputElement) document.getElementById("q");
//		if(findElement != null){
//			findElement.setValue("380977776080");
//			findElement.focus();
//		}
		Thread thread = null;
		thread = new Thread(new FindSaveTask(thread, webView));
		thread.start();
//		FindService findService = new FindService(document);
//		String link = findService.getUserLink();
//		System.out.println(link);
	}
}
