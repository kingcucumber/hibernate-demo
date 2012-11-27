package com.demo.hibernate;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.demo.dao.HibernateUtils;
import com.demo.domain.User;

public class Base {

	public static void main(String[] args) {

		Session s = HibernateUtils.getSession();

		Transaction tx = s.beginTransaction();
		User user = new User();
		user.setBirthday(new Date());
		user.setName("name");

		addUser(user);
		
		User u = getUser(user.getId());

		System.out.println("end");
	}

	static void addUser(User user) {
		Session s = null;
		Transaction tx = null;

		try {
			s = HibernateUtils.getSession();
			tx = s.beginTransaction();
			s.save(user);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			if (s != null) {
				s.close();
			}
		}
	}

	static User getUser(int id) {
		Session s = null;
		// Transaction tx = null;
		try {
			s = HibernateUtils.getSession();
			// tx = s.beginTransaction();
			Class userClass = User.class;
			User user = (User) s.get(User.class, id);
			User user1 = (User) s.load(User.class, id);
			return user;
			// tx.commit();
		} finally {
			if (s != null) {
				s.close();
			}
		}
	}
}
