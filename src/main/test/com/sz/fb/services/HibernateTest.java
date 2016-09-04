package com.sz.fb.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.sz.fb.models.FbUser;

public class HibernateTest {
	public static void main(String[] args) {
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure()
				.build();
		
		SessionFactory sessionFactory = null;
		try {
			sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
		} catch (Exception e) {
			StandardServiceRegistryBuilder.destroy( registry );
		}
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(new FbUser("0001", "Alex"));
		session.save(new FbUser("0002", "Alexx"));
		session.getTransaction().commit();
		session.close();
		
	}
}
