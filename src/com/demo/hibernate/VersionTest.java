package com.demo.hibernate;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.demo.dao.HibernateUtils;
import com.demo.domain.Name;
import com.demo.domain.User;

public class VersionTest {

	public static void main(String[] args) {

		Session s = HibernateUtils.getSession();

		Transaction tx = s.beginTransaction();
		User user = new User();
		user.setBirthday(new Date());
		Name n = new Name();
		n.setFirstName("first name");
		n.setLastName("last name");
		user.setName(n);

		addUser(user);

		// User u = getUser(user.getId());
		update(user.getId());
		System.out.println("end");
	}

	static void update(int id) {
		Session s1 = null;
		try {
			s1 = HibernateUtils.getSession();
			Transaction tx1 = s1.beginTransaction();
			User user1 = (User) s1.get(User.class, id);

			Session s2 = HibernateUtils.getSession();
			Transaction tx2 = s2.beginTransaction();
			User user2 = (User) s2.get(User.class, id);

			user1.getName().setFirstName("new1 firstname");
			user2.getName().setFirstName("new2 firstname");

			tx2.commit();
			tx1.commit();

			s1.close();
			s2.close();
		} finally {

		}
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
