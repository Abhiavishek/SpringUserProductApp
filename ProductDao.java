package org.jsp.springuserprodapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.jsp.springuserprodapp.dto.Product;
import org.jsp.springuserprodapp.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
	
	@Autowired
	EntityManager m;
	
	public Product SaveProduct(Product p, int uid) {
		User u = m.find(User.class, uid);
		if(u!=null) {
			u.getProducts().add(p);
			p.setUser(u);
			EntityTransaction t = m.getTransaction();
			m.persist(p);
			t.begin();
			t.commit();
			return p;
		}
		
		return null;
	}
	
	
	
	public Product updateProductById(int id) {
		Product p = m.find(Product.class, id);
		if(p!=null) {
			EntityTransaction t = m.getTransaction();
			m.merge(p);
			t.begin();
			t.commit();
			return p;
		}
		return null;
	}
	
	public Product findById(int id) {
		return m.find(Product.class, id);
	}
	
	public boolean deleteproduct(int id) {
		Product p = m.find(Product.class, id);
		if(p!=null) {
			EntityTransaction t = m.getTransaction();
			m.remove(p);
			t.begin();
			t.commit();
			return true;
			
		}
		return false;
	}
	
	public List<Product> fetchProductByUserId(int id) {
		String qry = "select u.products from User u where u.id=?1";
		Query q = m.createQuery(qry);
		q.setParameter(1, id);
		return q.getResultList();
	}
	
	public List<Product> fetchProductByCategory(String category){
		String qry = "select p from Product p where p.category=?1";
		Query q = m.createQuery(qry);
		q.setParameter(1, category);
		return q.getResultList();
	}
	
	public List<Product> fetchProductByBrand(String brand){
		String qry = "select p from Product p where p.brand=?1";
		Query q = m.createQuery(qry);
		q.setParameter(1, brand);
		return q.getResultList();
	}

	public List<Product> fetchProductByPrice(double price){
		String qry = "select p from Product p where p.price=?1";
		Query q = m.createQuery(qry);
		q.setParameter(1, price);
		return q.getResultList();
	}
	
	public Product fetchProductByCategoryAndBrand(String category, String brand) {
		String qry = "select p from Product p where p.category=?1 and p.brand=?2";
		Query q = m.createQuery(qry);
		q.setParameter(1, category);
		q.setParameter(2, brand);
		return (Product)q.getSingleResult();
	}
	
	public List<Product> fetchProductByPhoneAndPassword(long phone, String password){
		String qry = "select u.products from User u where u.phone=?1 and u.password=?2";
		Query q = m.createQuery(qry);
		q.setParameter(1, phone);
		q.setParameter(2, password);
		return q.getResultList();
	}
	
	
	
	
}
