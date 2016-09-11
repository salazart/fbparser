package com.sz.fb.services;

import java.util.List;

import com.sz.fb.dao.impl.FbTargetPhoneService;
import com.sz.fb.models.FbTargetPhone;
import com.sz.fb.utils.HibernateUtil;

public class FbTargetPhoneTest {
	
	public static void main(String[] args) {
		
		FbTargetPhoneService fbTargetPhoneService = new FbTargetPhoneService(HibernateUtil.getInstance().getSessionFactory());
		
		
		for (int i = 0; i < 1000; i++) {
			String phone = String.format("%07d", i);
			String fullPhone = "38067" + phone;
			
			FbTargetPhone fbTargetPhone = new FbTargetPhone(fullPhone, false);
			
			fbTargetPhoneService.save(fbTargetPhone);
		}
		
		
//		fbTargetPhone2.setUsed(true);
//		fbTargetPhoneService.update(fbTargetPhone2);
		
		List<FbTargetPhone> fbTargetPhones = fbTargetPhoneService.getAll();
		System.out.println(fbTargetPhones);
		
		HibernateUtil.release();
	}

}
