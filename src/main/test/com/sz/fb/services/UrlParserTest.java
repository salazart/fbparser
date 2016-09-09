package com.sz.fb.services;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class UrlParserTest {
	private static final String URL_EXAMPLE = "https://www.facebook.com/profile.php?id=100009549320400&ref=ts&fref=ts";
	private static final String URL_EXAMPLE2 = "https://www.facebook.com/Serg.Bogdanovskiy?ref=ts&fref=ts";

	public static void main(String[] args) {
		try {
			URL url = new URL(URL_EXAMPLE2);
			String query = url.getQuery();
			String idParam = Arrays.asList(query.split("&"))
					.stream()
					.filter(s -> s.contains("id"))
					.findFirst()
					.get();
			
			String id = idParam.replaceAll("id=", "");
			System.out.println(id);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
