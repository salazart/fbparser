package com.sz.fb.dao.impl;

import org.hibernate.Session;

import com.sz.fb.dao.interfaces.IHibernateDao;
import com.sz.fb.models.FbUser;

public class FbUserService extends AbstractHibernateDao<FbUser> implements IHibernateDao<FbUser>{

	public FbUserService(Session session) {
		super(session);
	}
	
}
