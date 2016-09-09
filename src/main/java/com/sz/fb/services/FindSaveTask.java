package com.sz.fb.services;

import java.util.TimerTask;

import org.w3c.dom.Document;
import org.w3c.dom.html.HTMLInputElement;

import com.sz.fb.dao.impl.FbTargetPhoneService;
import com.sz.fb.dao.impl.FbUserService;
import com.sz.fb.models.FbTargetPhone;
import com.sz.fb.utils.HibernateUtil;

import javafx.application.Platform;
import javafx.scene.web.WebView;

public class FindSaveTask extends TimerTask{
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
		FindService findService = new FindService(webView.getEngine().getDocument());
		String textUrl = findService.getUserLink();
		System.out.println(textUrl);
		if (!textUrl.isEmpty()) {
			insertedPhone = false;
			FbUserService fbUserService = new FbUserService(HibernateUtil.getInstance().getSessionFactory());
			fbUserService.saveFbUser(textUrl, "");
			
			fbTargetPhone.setUsed(true);
		}
	}

}
