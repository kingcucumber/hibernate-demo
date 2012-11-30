package com.demo.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.demo.dao.HibernateUtils;
import com.demo.domain.Name;
import com.demo.domain.User;

public class QueryTest {

	public static void main(String[] args) {

		User user = new User();
		user.setBirthday(new Date());
		Name n = new Name();
		n.setFirstName("first name");
		n.setLastName("last name");
		user.setName(n);
		// user.setName("name");
		HibernateUtils.add(user);
		query(user.getName().getFirstName());
	}

	static void query(String name) {
		Session s = null;
		try {
			s = HibernateUtils.getSession();
			String hql = "from User as user where user.name=? ";
			// from Object;
			Query query = s.createQuery(hql);
			query.setString(0, name);
			List<User> list = query.list();// executeQuery();
			User u = (User) query.uniqueResult();
			System.out.println(u.getName());
			for (User user : list) {
				System.out.println(user.getName());
			}
		} finally {
			if (s != null) {
				s.close();
			}
		}
	}

	static void addUser(User user) {
		Session s = null;
		Transaction tx = null;
		try {
			s = HibernateUtils.getSession();
			Name name = new Name();
			name.setFirstName("first name");
			name.setLastName("last name");

			User u = new User();
			u.setName(name);
			tx = s.beginTransaction();
			s.save(u);
			tx.commit();

		} finally {
			if (s != null) {
				s.close();
			}
		}
	}
}
