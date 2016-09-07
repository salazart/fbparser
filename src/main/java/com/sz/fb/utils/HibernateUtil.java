package com.sz.fb.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
	private static Session session;

	private synchronized static void init() {
		if (sessionFactory == null) {
			try {
				sessionFactory = new Configuration().configure().buildSessionFactory();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public static Session openSession() {
		init();
		if(session == null || session.isConnected()){
			session = sessionFactory.openSession();
		}
		return session;
	}

	public static void release() {
		sessionFactory.close();
	}
}
