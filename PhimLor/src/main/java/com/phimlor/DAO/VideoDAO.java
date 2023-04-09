package com.phimlor.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.phimlor.model.User;
import com.phimlor.model.Video;

public class VideoDAO extends AbstractEntityDao<Video, String>{

	EntityManager em = JpaUtils.getEntityManager();
	EntityTransaction trans = em.getTransaction();
	
	@Override
	public void create(Video entity) {
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
	public void update(Video entity) {
		try {
			trans.begin();
			em.merge(entity);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();	
		} 
	}

	@Override
	public void delete(String key) {
		try {
			trans.begin();
			Video entity = em.find(Video.class, key);
			em.remove(entity);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} 
	}

	@Override
	public Video findById(String key) {
		Video entity = em.find(Video.class, key);
		return entity;
	}

	@Override
	public List<Video> FindAll() {
		String jpql = "SELECT o FROM Video o";
		TypedQuery<Video> query = em.createQuery(jpql, Video.class);
		List<Video> list = query.getResultList();
		return list;
	}

	public List<Video> findPage(int page, int index) {
		String jpql = "SELECT o FROM Video o order by o.views desc ";
		TypedQuery<Video> query = em.createQuery(jpql, Video.class);
		query.setFirstResult((page - 1)* index);
		query.setMaxResults(index);
		List<Video> list = query.getResultList();
		return list;
	}
}
