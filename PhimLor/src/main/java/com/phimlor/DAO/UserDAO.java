package com.phimlor.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import com.phimlor.model.Favorite;
import com.phimlor.model.User;
import com.phimlor.model.Video;
import com.phimlor.DAO.JpaUtils;

public class UserDAO extends AbstractEntityDao<User, String>{

	EntityManager em = JpaUtils.getEntityManager();
	EntityTransaction trans = em.getTransaction();
	
	@Override
	public void create(User entity) {
		try {
			trans.begin();
			em.persist(entity);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} 
	}

	@Override
	public void update(User entity) {
		try {
			trans.begin();
			em.merge(entity);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} 
	}

	@Override
	public void delete(String key) {
		try {
			trans.begin();
			User entity = em.find(User.class, key);
			em.remove(entity);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}
	}

	@Override
	public User findById(String key) {
		User entity = em.find(User.class, key);
		return entity;
	}

	@Override
	public List<User> FindAll() {
		String jpql = "SELECT o FROM User o";
		TypedQuery<User> query = em.createQuery(jpql, User.class);
		List<User> list = query.getResultList();
		return list;
	}
 
	public List<User> findPage(int page, int index) {
		String jpql = "SELECT o FROM User o ";
		TypedQuery<User> query = em.createQuery(jpql, User.class);
		query.setFirstResult((page - 1)* index);
		query.setMaxResults(index);
		List<User> list = query.getResultList();
		return list;
	}
	
	
	public Boolean findByEmail(String key) {
		String jpql = "SELECT o FROM User o Where o.email = ?0";
		TypedQuery<User> query = em.createQuery(jpql, User.class);
		query.setParameter(0, key);
		List<User> list =  query.getResultList();
		if(list.size()==0) {
			return true;
		}else {
			return false;
		}
	}
}
