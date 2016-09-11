package com.sz.fb.dao.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Properties;

import org.hibernate.SessionFactory;

import com.sz.fb.dao.interfaces.IHibernateDao;
import com.sz.fb.models.FbUser;
import com.sz.fb.services.GetUserService;

public class FbUserService extends AbstractHibernateDao<FbUser> implements IHibernateDao<FbUser>{
	
	private static final String CONFIG_PROPERTIES = "config.properties";
	
	public FbUserService(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	public void saveFbUser(String textUrl, String phone){
		String id = getIdOfUrl(textUrl);
		
		FbUser fbUser = loadFbUser(id);
		
		fbUser.setPhone(phone);
		
		save(fbUser);
		System.out.println("FbUser saved: " + fbUser);
	}
	
	private String getIdOfUrl(String textUrl){
		try {
			URL url = new URL(textUrl);
			
			return getUserId(url);
		} catch (MalformedURLException e) {
			System.out.println(e);
			return "";
		}
	}
	
	private static String getUserId(URL url) {
		String id = null;
		if(url.getQuery().contains("id=")){
			String idParam = Arrays.asList(url.getQuery().split("&"))
					.stream()
					.filter(s -> s.contains("id"))
					.findFirst()
					.get();
			id = idParam.replaceAll("id=", "");
		} else {
			id = url.getPath().replaceAll("/", "");
		}
		return id;
	}

	private FbUser loadFbUser(String userId) {
		FbUser fbUser = new FbUser(userId);
		Properties properties = getProperties();
		GetUserService getUserService = new GetUserService(fbUser, properties);
		return getUserService.getFbUser();
	}
	
	private Properties getProperties(){
		Properties properties = new Properties();
		try {
			properties.load(this.getClass().getClassLoader().getResourceAsStream(CONFIG_PROPERTIES));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
}
