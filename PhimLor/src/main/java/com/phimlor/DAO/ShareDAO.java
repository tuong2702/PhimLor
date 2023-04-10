package com.phimlor.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.phimlor.model.Favorite;
import com.phimlor.model.Share;
import com.phimlor.model.User;

public class ShareDAO extends AbstractEntityDao<Share, String>{

	EntityManager em = JpaUtils.getEntityManager();
	EntityTransaction trans = em.getTransaction();
	
	@Override
	public void create(Share entity) {
		try {
			trans.begin();
			em.persist(entity);
			trans.commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
	}

	@Override
	public void update(Share entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Share findById(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Share> FindAll() {
		String jpql = "SELECT s FROM Share s";
		TypedQuery<Share> query = em.createQuery(jpql, Share.class);
		List<Share> list = query.getResultList();
		return list;
	}
		
}
