package com.asap.catalog.extensions.seminar;

import com.asap.catalog.extensions.shop.dao.*;
import com.asap.catalog.extensions.seminar.Term;
import com.asap.catalog.dao.*;
import com.asap.catalog.extensions.shop.dao.Product;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.IndexColumn;

import com.asap.core.number.MutableInteger;


public class Storage {
	@OneToMany(cascade = { CascadeType.ALL })
	@IndexColumn(name = "id")
	private Map<Term, MutableInteger> terms = new HashMap<Term, MutableInteger>();

	@OneToMany(cascade = { CascadeType.ALL })
	@IndexColumn(name = "id")
	private Map<Product, MutableInteger> products = new HashMap<Product, MutableInteger>();

	public Map<Product, MutableInteger> getProducts() {
		return products;
	}

	public void setProducts(Map<Product, MutableInteger> products) {
		this.products = products;
	}

	public Map<Term, MutableInteger> getTerms() {
		return terms;
	}

	public void setTerms(Map<Term, MutableInteger> terms) {
		this.terms = terms;
	}

	public void addProduct(Product product) {
		MutableInteger integer = products.get(product);
		if (integer == null) {
			integer = new MutableInteger();
			products.put(product, integer);
		}
		integer.increment();
	}

	public void addProduct(Product product, int count) {
		MutableInteger integer = products.get(product);
		if (integer == null) {
			integer = new MutableInteger();
			products.put(product, integer);
		}
		integer.add(count);
	}

	public void removeProduct(Product product) {
		MutableInteger integer = products.get(product);
		if (integer == null) {
			return;
		}
		integer.decrement();
	}

	public void removeProduct(Product product, int count) {
		MutableInteger integer = products.get(product);
		if (integer == null) {
			return;
		}
		integer.subtract(count);
	}

	public long getCountProduct(Product product) {
		MutableInteger integer = products.get(product);
		if (integer == null) {
			return 0;
		}
		return integer.getValue();
	}

	public void addTerm(Term term) {
		MutableInteger integer = terms.get(term);
		if (integer == null) {
			integer = new MutableInteger();
			terms.put(term, integer);
		}
		integer.increment();
	}

	public void addTerm(Term term, int count) {
		MutableInteger integer = terms.get(term);
		if (integer == null) {
			integer = new MutableInteger();
			terms.put(term, integer);
		}
		integer.add(count);
	}

	public void removeTerm(Term term) {
		MutableInteger integer = terms.get(term);
		if (integer == null) {
			return;
		}
		integer.decrement();
	}

	public void removeTerm(Term term, int count) {
		MutableInteger integer = terms.get(term);
		if (integer == null) {
			return;
		}
		integer.subtract(count);
	}

	public long getCountTerm(Term term) {
		MutableInteger integer = terms.get(term);
		if (integer == null) {
			return 0;
		}
		return integer.getValue();
	}

}
