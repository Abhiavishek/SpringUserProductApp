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
public class UserDao {
	
	@Autowired
	EntityManager manager;
	
	public User saveUser(User user) {
		EntityTransaction t = manager.getTransaction();
		manager.persist(user);
		t.begin();
		t.commit();
		return user;
	}
	
	public User findUserById(int id) {
		return manager.find(User.class, id);
	}
	
	public User updateUser(User user) {
		EntityTransaction t = manager.getTransaction();
		manager.merge(user);
		t.begin();
		t.commit();
		return user;
	}
	
	public boolean deleteUser(int id) {
		User u = manager.find(User.class, id);
		if(u!=null) {
			EntityTransaction t = manager.getTransaction();
			manager.remove(u);
			t.begin();
			t.commit();
			return true;
		}
		return false;
	}
	
	public User VerifyUserByPhoneAndPassword(long phone , String password) {
		String qry = "select u from User u where u.phone=?1 and u.password=?2";
		Query q = manager.createQuery(qry);
		q.setParameter(1, phone);
		q.setParameter(2, password);
		return (User)q.getSingleResult();
	}
	public List<User> FindUserByProductName(String name){
		String qry = "select p.user from Product p where p.name=?1";
		Query q = manager.createQuery(qry);
		q.setParameter(1, name);
		return q.getResultList();
	}

}
