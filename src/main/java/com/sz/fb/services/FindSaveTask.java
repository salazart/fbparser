package com.sz.fb.services;

import java.util.TimerTask;

import org.w3c.dom.Document;
import org.w3c.dom.html.HTMLElement;
import org.w3c.dom.html.HTMLInputElement;

import com.sz.fb.dao.impl.FbTargetPhoneService;
import com.sz.fb.dao.impl.FbUserService;
import com.sz.fb.models.FbTargetPhone;
import com.sz.fb.utils.HibernateUtil;

import javafx.application.Platform;
import javafx.scene.web.WebView;

public class FindSaveTask extends TimerTask{
	private static final String FB_URL = "https://www.facebook.com/";
	private WebView webView;
	private FbTargetPhone fbTargetPhone;
	
	private static boolean insertedPhone = false;
	
	public FindSaveTask(WebView webView) {
		super();
		this.webView = webView;
	}

	@Override
	public void run() {
		Platform.runLater(new Runnable() {
			public void run() {
				if (insertedPhone) {
					saveFbUser();
				} else {
					insertPhone();
				}
			}
		});
	}
	private void insertPhone() {
		Document document = webView.getEngine().getDocument();
		if(document != null){
			HTMLInputElement findElement = (HTMLInputElement) document.getElementById("q");
			if(findElement != null){
				FbTargetPhoneService fbTargetPhoneService = new FbTargetPhoneService(HibernateUtil.getInstance().getSessionFactory());
				fbTargetPhone = fbTargetPhoneService.getOne();
				findElement.setValue(fbTargetPhone.getId());
				findElement.focus();
				insertedPhone = true;
			}
		}
	}
	
	private void saveFbUser() {
		Document document = webView.getEngine().getDocument();
		
		FindService findService = new FindService(document);
		String textUrl = findService.getUserLink();
		System.out.println(textUrl);
		if (!textUrl.isEmpty()) {
			FbUserService fbUserService = new FbUserService(HibernateUtil.getInstance().getSessionFactory());
			fbUserService.saveFbUser(textUrl, fbTargetPhone.getId());
		}
		insertedPhone = false;
		//webView.getEngine().load(FB_URL);
		HTMLInputElement findElement = (HTMLInputElement) document.getElementById("js_0");
		if(findElement != null){
			findElement.focus();
		}
		
		fbTargetPhone.setUsed(true);
		FbTargetPhoneService fbTargetPhoneService = new FbTargetPhoneService(HibernateUtil.getInstance().getSessionFactory());
		fbTargetPhoneService.update(fbTargetPhone);
	}
	
}
