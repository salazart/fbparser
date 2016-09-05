package com.sz.fb.services;

import java.util.List;

import com.sz.fb.models.FbUser;

public class FbUserServiceTest {

	public static void main(String[] args) {
		FbUser fbUser = new FbUser("001", "Alex", "380971234567");
		
		FbUserService fbUserService = new FbUserService();
		fbUserService.save(fbUser);
		
		List<FbUser> fbUsers = fbUserService.getAll();
		fbUsers.forEach(System.out::println);
	}

}
