package com.sz.fb.services;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Properties;

import org.junit.Test;

import com.sz.fb.models.FbUser;

public class GetUserTest {
	private static final String CONFIG_PROPERTIES = "config.properties";
	private static final String USER_ID = "100009549320400";
	private static final String USER_ID2 = "Serg.Bogdanovskiy";
	private static final String USER_ID3 = "1052295964822172";
	@Test
	public void testGetFbUser() throws IOException {
		FbUser fbUser = new FbUser(USER_ID3);
		
		Properties properties = getProperties();
		
		GetUserService getUserService = new GetUserService(fbUser, properties);
		fbUser = getUserService.getFbUser();
		
		assertNotNull(fbUser.getName());
		assertFalse(fbUser.getName().isEmpty());
	}
	
	private Properties getProperties() throws IOException{
		Properties properties = new Properties();
		properties.load(this.getClass().getClassLoader().getResourceAsStream(CONFIG_PROPERTIES));
		return properties;
	}

}
