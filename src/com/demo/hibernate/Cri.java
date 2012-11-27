package com.demo.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.demo.dao.HibernateUtils;
import com.demo.domain.User;

public class Cri {

	public static void main(String[] args) {
		cri("name");
	}

	static void cri(String name) {
		Session s = null;
		try {
			s = HibernateUtils.getSession();
			Criteria c = (Criteria) s.createCriteria(User.class);
			c.add(Restrictions.eq("name", name));
			c.add(Restrictions.lt("birthday", new Date()));

			c.setFirstResult(0);
			c.setMaxResults(10);

			List<User> list = c.list();// executeQuery();
			User u = (User) c.uniqueResult();
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
