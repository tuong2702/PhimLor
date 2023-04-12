package com.phimlor.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.phimlor.model.Favorite;
import com.phimlor.model.Report;
import com.phimlor.model.Share;

public class ThongKeDAO {
		
	EntityManager em = JpaUtils.getEntityManager();
	EntityTransaction trans = em.getTransaction();
	
	public List<Report> reportFavorite(){
		String jpql = "SELECT new Report(o.video.title, count(o), "
    			+ "max(o.likeDate), min(o.likeDate)) "
    			+ "FROM Favorite o "
    			+ "GROUP BY o.video.title";
    	TypedQuery<Report> query = em.createQuery(jpql, Report.class);
    	List<Report> list = query.getResultList();
		return list;
	}
	
	public List<Favorite> reportFavoriteUser(String key) {
		String jpql = "SELECT f FROM Favorite f Where f.video.title=:id";
		TypedQuery<Favorite> query = em.createQuery(jpql, Favorite.class);
		query.setParameter("id",key);
		List<Favorite> list = query.getResultList();
		return list;
	}
	
	public List<Share> reportFavoriteShare(String key){
		String jpql = "SELECT s FROM Share s Where s.video.title=:id";
		TypedQuery<Share> query = em.createQuery(jpql, Share.class);
		query.setParameter("id",  key);
		List<Share> list = query.getResultList();
		return list;
	}
	
}
