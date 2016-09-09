package com.sz.fb.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.sz.fb.dao.interfaces.IHibernateDao;
import com.sz.fb.models.FbTargetPhone;

public class FbTargetPhoneService extends AbstractHibernateDao<FbTargetPhone> implements IHibernateDao<FbTargetPhone>{

	public FbTargetPhoneService(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	public FbTargetPhone getOne() {
		FbTargetPhone entity = null;
		try (Session session = sessionFactory.openSession();){
			session.beginTransaction();
			Query query = session.createQuery("from " + clazz.getName() + " where isUsed = false ");
			query.setMaxResults(1);
			entity = (FbTargetPhone) query.getSingleResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			System.err.println(e);
		}
		return entity;
	}
	
	public void update(FbTargetPhone fbTargetPhone) {
		try (Session session = sessionFactory.openSession();){
			session.beginTransaction();
			session.update(fbTargetPhone);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
