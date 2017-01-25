package com.asap.catalog.extensions.shop.web.request;

import com.asap.security.Role;
import com.asap.security.Secure;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import util.HibernateUtil;

import com.asap.catalog.extensions.shop.dao.Product;
import com.asap.catalog.extensions.shop.dao.Request;
import com.asap.catalog.extensions.shop.dao.RequestItem;
import com.asap.catalog.extensions.seminar.Term;
import com.asap.catalog.dao.User;
import com.asap.catalog.extensions.shop.dao.RequestItem.ItemType;
import com.asap.web.CatalogActionBean;

@Secure(role = Role.USER)
public class RequestManagerActionBean extends CatalogActionBean {

	private Request request;

	private List<Request> requests;

	private User user;

	private Product product;

	private Term term;

	public Resolution manager() {
		return new ForwardResolution("/request/requestManager.jsp");
	}

	public Resolution perLastWeek() {
		Date date = new Date();
		date.setTime(date.getTime() - 7 * 24 * 60 * 60 * 1000);

		List<Request> list = HibernateUtil.getSessionFactory()
				.getCurrentSession().createCriteria(Request.class).list();
		requests = new ArrayList<Request>();
		for (Request request : list) {
			if (request.getDate() != null) {
				if (date.before(request.getDate())) {
					requests.add(request);
				}
			}
		}
		return new ForwardResolution("/request/requestManagerFormed.jsp");
	}

	public Resolution managerByUser() {
		if (user != null) {
			List<Request> list = HibernateUtil.getSessionFactory()
					.getCurrentSession().createCriteria(Request.class).list();
			requests = new ArrayList<Request>();
			for (Request request : list) {
				if (request.getUser() != null) {
					if (request.getUser().getId().equals(user.getId())) {
						requests.add(request);
					}
				}
			}
		} else {
			requests = new ArrayList<Request>();
		}
		return new ForwardResolution("/request/requestManagerFormed.jsp");
	}

	public Resolution managerByProduct() {
		if (product != null) {
			List<Request> list = HibernateUtil.getSessionFactory()
					.getCurrentSession().createCriteria(Request.class).list();
			requests = new ArrayList<Request>();
			for (Request request : list) {
				findProducts(requests, request);
			}
		} else {
			requests = new ArrayList<Request>();
		}
		return new ForwardResolution("/request/requestManagerFormed.jsp");
	}

	private void findProducts(List<Request> requests, Request request) {
		for (RequestItem item : request.getItems()) {
			if (item.equalsItem((Object) product)) {
				requests.add(request);
				return;
			}
		}
	}

	public Resolution managerByTerm() {
		if (term != null) {
			List<Request> list = HibernateUtil.getSessionFactory()
					.getCurrentSession().createCriteria(Request.class).list();
			requests = new ArrayList<Request>();
			for (Request request : list) {
				findTerms(requests, request);
			}
		} else {
			requests = new ArrayList<Request>();
		}
		return new ForwardResolution("/request/requestManagerFormed.jsp");
	}

	private void findTerms(List<Request> requests, Request request) {
		for (RequestItem item : request.getItems()) {
			if (item.equalsItem((Object) term)) {
				requests.add(request);
				return;
			}
		}
	}

	public Resolution paid() {
		request.setPaid(true);
		return managerByUser();
	}

	public Resolution shipped() {
		request.setShipped(true);
		return managerByUser();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public List<Request> getRequests() {
		return requests;
	}

	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Term getTerm() {
		return term;
	}

	public void setTerm(Term term) {
		this.term = term;
	}

}
