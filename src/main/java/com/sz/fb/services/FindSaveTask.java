package com.sz.fb.services;

import org.w3c.dom.html.HTMLInputElement;

import javafx.scene.web.WebView;

public class FindSaveTask implements Runnable{

	private WebView webView;
	private Thread thread;
	
	private boolean insertedPhone = false;
	
	public FindSaveTask(Thread thread, WebView webView) {
		super();
		this.webView = webView;
		this.thread = thread;
	}

	@Override
	public void run() {
		System.out.println("Start thread");
		while(true){
			System.out.println("Start loop");
			if(!insertedPhone){
				insertPhone();
				insertedPhone = true;
			}
			
			try {
				thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			FindService findService = new FindService(webView.getEngine().getDocument());
			String link = findService.getUserLink();
			System.out.println(link);
			System.out.println("End loop");
		}
	}

	private void insertPhone() {
		HTMLInputElement findElement = (HTMLInputElement) webView.getEngine().getDocument().getElementById("q");
		if(findElement != null){
			findElement.setValue("380977776080");
			findElement.focus();
		}
	}

}
