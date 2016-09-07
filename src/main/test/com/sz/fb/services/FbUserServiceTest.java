package com.sz.fb.services;

import java.util.List;

import com.sz.fb.dao.impl.FbUserService;
import com.sz.fb.dao.interfaces.IHibernateDao;
import com.sz.fb.models.FbUser;
import com.sz.fb.utils.HibernateUtil;

public class FbUserServiceTest {

	public static void main(String[] args) {
		FbUser fbUser = new FbUser("001", "Alex", "380971234567");
		
		IHibernateDao<FbUser> fbUserService = new FbUserService(HibernateUtil.openSession());
		fbUser = fbUserService.save(fbUser);
		
		List<FbUser> fbUsers = fbUserService.getAll();
		System.out.println(fbUsers.size());
		fbUsers.forEach(System.out::println);
		
		HibernateUtil.release();
	}

}
