package com.sz.fb.dao.impl;

import org.hibernate.SessionFactory;

import com.sz.fb.dao.interfaces.IHibernateDao;
import com.sz.fb.models.FbUser;

public class FbUserService extends AbstractHibernateDao<FbUser> implements IHibernateDao<FbUser>{

	public FbUserService(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
}
