package com.sz.fb.services;

import java.util.List;

import org.hibernate.Session;

import com.sz.fb.models.FbUser;
import com.sz.fb.utils.HibernateUtil;

public class HibernateTest {
	public static void main(String[] args) {
//		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
//				.configure()
//				.build();
		
		
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();
		session.save(new FbUser("0001", "Alex"));
		session.save(new FbUser("0002", "Alexx"));
		session.getTransaction().commit();
		session.close();
		
		session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();
		List<FbUser> fbUsers = (List<FbUser>) session.createQuery( "from FbUser" ).getResultList();
		fbUsers.forEach(System.out::println);
		session.close();
		
		HibernateUtil.release();
		
	}
}
