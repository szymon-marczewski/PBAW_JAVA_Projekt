package com.jsfcourse.product;

import java.io.IOException;
import java.io.Serializable;

import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import com.jsf.dao.ProductDAO;
import com.jsf.entities.Product;

@Named
@ViewScoped
public class ProductEditGETBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_PERSON_LIST = "productList?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private Product product = new Product();
	private Product loaded = null;

	@Inject
	FacesContext context;

	@EJB
	ProductDAO productDAO;

	public Product getProduct() {
		return product;
	}

	public void onLoad() throws IOException {
		if (!context.isPostback()) {
			if (!context.isValidationFailed() && product.getIdproduct() != null) {
				loaded = productDAO.find(product.getIdproduct());
			}
			if (loaded != null) {
				product = loaded;
			} else {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));
				// if (!context.isPostback()) { // possible redirect
				// context.getExternalContext().redirect("productList.xhtml");
				// context.responseComplete();
				// }
			}
		}

	}

	public String saveData() {
		// no Product object passed
		if (loaded == null) {
			return PAGE_STAY_AT_THE_SAME;
		}

		try {
			if (product.getIdproduct() == null) {
				// new record
				productDAO.create(product);
			} else {
				// existing record
				productDAO.merge(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "wystąpił błąd podczas zapisu", null));
			return PAGE_STAY_AT_THE_SAME;
		}

		return PAGE_PERSON_LIST;
	}
}
