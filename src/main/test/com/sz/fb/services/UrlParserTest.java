package com.sz.fb.services;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class UrlParserTest {
	private static final String URL_EXAMPLE = "https://www.facebook.com/profile.php?id=100009549320400&ref=ts&fref=ts";
	private static final String URL_EXAMPLE2 = "https://www.facebook.com/Serg.Bogdanovskiy?ref=ts&fref=ts";

	public static void main(String[] args) {
		try {
			URL url = new URL(URL_EXAMPLE);
			
			String id = getUserId(url);
			
			System.out.println(id);
		} catch (MalformedURLException e) {
			e.printStackTrace();
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

}
