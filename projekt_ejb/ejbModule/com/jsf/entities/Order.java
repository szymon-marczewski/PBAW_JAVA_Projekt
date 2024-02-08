package com.jsf.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;
import java.util.Date;

/**
 * The persistent class for the orders database table.
 * 
 */
@Entity
@Table(name="orders")
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idOrder;

	@Temporal(TemporalType.DATE)
	private Date date;

	private String description;

	private int status;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="idUser")
	private User user;

	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="order")
	private List<Transaction> transactions;

	public Order() {
	}

	public int getIdOrder() {
		return this.idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public User getUser() {
	//	int iduser = user.getIdUser();
		
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	//
	public int getIdUser() {
		int iduser = user.getIdUser();
		return iduser;
	}
	
	//public void setIdUser(int IdUser) {
	//	this.IdUser = getIdUser();
	//}
	//
	
	public List<Transaction> getTransactions() {
		return this.transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Transaction addTransaction(Transaction transaction) {
		getTransactions().add(transaction);
		transaction.setOrder(this);

		return transaction;
	}

	public Transaction removeTransaction(Transaction transaction) {
		getTransactions().remove(transaction);
		transaction.setOrder(null);

		return transaction;
	}

}