package com.demo.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.demo.dao.HibernateUtils;
import com.demo.domain.User;

public class QueryTest {

	public static void main(String[] args) {

		User user = new User();
		user.setBirthday(new Date());
		user.setName("name");
		HibernateUtils.add(user);
		query(user.getName());
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
}
