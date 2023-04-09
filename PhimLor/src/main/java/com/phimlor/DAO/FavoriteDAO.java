package com.phimlor.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.phimlor.model.Favorite;
import com.phimlor.model.User;
import com.phimlor.model.Video;

public class FavoriteDAO{

	EntityManager em = JpaUtils.getEntityManager();
	EntityTransaction trans = em.getTransaction();
	
	public Favorite create(Favorite entity) {
		try {
			trans.begin();
			em.persist(entity);
			trans.commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			return null;
		}
		return entity;
	}
	
	public Favorite remove( String id, String VideoId){
		String jpql = "SELECT v FROM Favorite v WHERE v.video.id = ?0 and v.user.id=?1";
		TypedQuery<Favorite> query = em.createQuery(jpql, Favorite.class);
		query.setParameter(0, VideoId);
		query.setParameter(1, id);
		Favorite entity = query.getSingleResult();
		try {
			trans.begin();
			em.remove(entity);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
		}
		return entity;
	}
	
	public boolean checkLike( String id, String VideoId){	
		
		String jpql = "SELECT v FROM Favorite v WHERE v.video.id = ?0 and v.user.id=?1";
		TypedQuery<Favorite> query = em.createQuery(jpql, Favorite.class);
		query.setParameter(0, VideoId);
		query.setParameter(1, id);
		List<Favorite> list =  query.getResultList();
		
		if(list.size()==0) {
			return false;
		}else {
			return true;
		}
		
}
	
	public List<Favorite> ListFavoriteUser(String key) {
		String jpql = "SELECT f FROM Favorite f Where f.user.id =:id";
		TypedQuery<Favorite> query = em.createQuery(jpql, Favorite.class);
		query.setParameter("id", key);
		List<Favorite> list = query.getResultList();
		return list;
	}

	public Favorite findById(String key) {
		String jpql = "SELECT f FROM Favorite f Where f.video.id =:id";
		TypedQuery<Favorite> query = em.createQuery(jpql, Favorite.class);
		query.setParameter("id", key);
		Favorite list = query.getSingleResult();
		return list;
	}
}
