package com.sz.fb.dao.interfaces;

import java.util.List;

import com.sz.fb.models.IEntity;

public interface IHibernateDao<T extends IEntity>{
	public T save(T entity);
	
	public List<T> getAll();
}
