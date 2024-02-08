package com.jsf.dao;

import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.jsf.entities.Order;
import com.jsf.entities.User;

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
//
	public void create(User user) {
		em.persist(user);
	}
//
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
//		String join = "join User u ";
//		String on = "on p.idUser = u.idUser ";
//		String where = "where u.idUser like 7 ";
		String where = "where p.status like 1 ";
		String orderby = "order by p.date";

		
		Query query = em.createQuery(select + from +  where + orderby);

		list = query.getResultList();
		return list;
	}

}