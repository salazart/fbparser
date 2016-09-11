package com.sz.fb.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import org.codehaus.jackson.map.ObjectMapper;

import com.sz.fb.models.FbUser;

public class GetUserService {
	private static final String FB_ACCESS_TOKEN = "fbAccessToken";
	private static final String USER_AGENT = "Mozilla/5.0";
	private static final String URL = "https://graph.facebook.com/";
	
	private FbUser fbUser;
	private Properties properties;
	
	public GetUserService(FbUser fbUser, Properties properties) {
		super();
		this.fbUser = fbUser;
		this.properties = properties;
	}
	
	private Map<String, String> getParameters(){
		Map<String, String> parameters = new HashMap<>();
		parameters.put("fields", "id,name");
		parameters.put("access_token", properties.getProperty(FB_ACCESS_TOKEN));
		return parameters;
	}
	
	public FbUser getFbUser(){
		Map<String, String> parameters = getParameters();
		
		try {
			HttpURLConnection con = getUrlConnection(parameters);
			
			String result = new BufferedReader(new InputStreamReader(con.getInputStream())).lines()
										.collect(Collectors.joining("\n"));
			System.out.println(result);
			ObjectMapper mapper = new ObjectMapper();
			fbUser = mapper.readValue(result, FbUser.class);
			
		} catch (IOException e) {
			System.err.println(e);
		}
		
		return fbUser;
	}

	private HttpURLConnection getUrlConnection(Map<String, String> parameters) throws IOException {
		String textParameters = parameters.entrySet()
				.stream()
				.map(entry -> entry.getKey() + "=" + entry.getValue())
				.collect(Collectors.joining("&"));

		HttpURLConnection con = null;
		try {
			URL obj = new URL(URL + fbUser.getId() + "?" + textParameters);
			con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", USER_AGENT);
			con.getResponseCode();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		return con;
	}
}
