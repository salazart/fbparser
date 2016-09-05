package com.sz.fb.services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.sz.fb.models.FbUser;

public class FbUserService {
	private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
			.configure()
			.build();
	
	private SessionFactory sessionFactory = null;
	
	private SessionFactory getSessionFactory(){
		if (sessionFactory == null){
			try {
				sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
			} catch (Exception e) {
				StandardServiceRegistryBuilder.destroy( registry );
			}
		}
		return sessionFactory;
	}
	
	public List<FbUser> getAll(){
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		List<FbUser> fbUsers = (List<FbUser>) session.createQuery( "from FbUser" ).getResultList();
		session.getTransaction().commit();
		session.close();
		return fbUsers;
	}
	
	public void save(FbUser fbUser){
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		session.save(fbUser);
		session.getTransaction().commit();
		session.close();
	}
	
	public void destoy(){
		if(sessionFactory != null){
			sessionFactory.close();
		}
	}
	
}
