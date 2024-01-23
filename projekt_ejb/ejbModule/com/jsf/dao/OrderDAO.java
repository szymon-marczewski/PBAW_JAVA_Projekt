package com.jsf.dao;

import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.jsf.entities.Order;

@Stateless
public class OrderDAO {
	private final static String UNIT_NAME = "jsfcourse-projektPU";

	// Dependency injection (no setter method is needed)
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(Order order) {
		em.persist(order);
	}

	public Order merge(Order order) {
		return em.merge(order);
	}

	public void remove(Order order) {
		em.remove(em.merge(order));
	}

	public Order find(Object id) {
		return em.find(Order.class, id);
	}

	
	public List<Order> getFullList() {
		List<Order> list = null;

		Query query = em.createQuery("SELECT p FROM Order p");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public List<Order> getList(Map<String, Object> searchParams) {
		List<Order> list = null;
		// 1. Build query string with parameters
		String select = "select p ";
		String from = "from Order p ";
//		String where = "where p.user = 7 ";
		String orderby = "order by p.date";

		
		Query query = em.createQuery(select + from  + orderby);

		list = query.getResultList();
		return list;
	}

}