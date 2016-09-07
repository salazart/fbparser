package com.sz.fb.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;

import com.sz.fb.dao.interfaces.IHibernateDao;
import com.sz.fb.models.IEntity;
import com.sz.fb.utils.HibernateUtil;

public abstract class AbstractHibernateDao<T extends IEntity> implements IHibernateDao<T>{

	protected Session session;
	protected Class<T> clazz;
	
	public AbstractHibernateDao(Session session) {
		this.session = session;
		this.clazz = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	private final Session getSession() {
		return session;
	}
	
	@Override
	public T save(T entity) {
		try {
			getSession().beginTransaction();
			getSession().save(entity);
			getSession().getTransaction().commit();
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			getSession().close();
		}
		return entity;
	}

	@Override
	public List<T> getAll() {
		List<T> entity = null;
		try {
			getSession().beginTransaction();
			entity = (List<T>) getSession().createQuery( "from " + clazz.getName()).getResultList();
			getSession().getTransaction().commit();
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			getSession().close();
		}
		return entity;
	}

}
