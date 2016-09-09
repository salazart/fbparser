package com.sz.fb.services;

import java.util.List;

import com.sz.fb.dao.impl.FbTargetPhoneService;
import com.sz.fb.models.FbTargetPhone;
import com.sz.fb.utils.HibernateUtil;

public class FbTargetPhoneTest {
	
	public static void main(String[] args) {
		FbTargetPhone fbTargetPhone = new FbTargetPhone("380935165187", false);
		
		FbTargetPhoneService fbTargetPhoneService = new FbTargetPhoneService(HibernateUtil.getInstance().getSessionFactory());
		fbTargetPhoneService.save(fbTargetPhone);
		
		FbTargetPhone fbTargetPhone2 = fbTargetPhoneService.getOne();
		System.out.println(fbTargetPhone2);
		
		fbTargetPhone2.setUsed(true);
		fbTargetPhoneService.update(fbTargetPhone2);
		
		List<FbTargetPhone> fbTargetPhones = fbTargetPhoneService.getAll();
		System.out.println(fbTargetPhones);
		
		HibernateUtil.release();
	}
}
