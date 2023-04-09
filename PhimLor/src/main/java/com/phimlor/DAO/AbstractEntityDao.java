package com.phimlor.DAO;

import java.util.List;

import com.phimlor.model.Favorite;


public abstract class AbstractEntityDao<T,K> {
	
	abstract public void create(T entity);

    abstract public void update(T entity);

    abstract public void delete(K key);

    abstract public  T findById(K key);

    abstract public List<T> FindAll();

}
