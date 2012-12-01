package com.demo.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

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
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		String name = request.getParameter("name");
		if(name != null)
		dc.add(Restrictions.eq("name", name));
		int age = request.getParameter("age");
		if(age>0)
			dc.add(Restrictions.eq("age", age))};
			List users = dd(dc);
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

	static void update() {
		Session s = null;
		try {
			s = HibernateUtils.getSession();
			Transaction tx = s.beginTransaction();
			Query q = s.createQuery("from User");
			List<User> users = q.list();
			for (User u : users) {
				u.setBirthday(new Date());
			}
			// ////////////////////////////\
			Query q1 = s
					.createQuery("update u set birthday =:bd from User as u");
			q1.executeUpdate();
			// ////////////////////////////////////////////////// 
			tx.commit();
		} finally {
			if (s != null) {
				s.close();
			}
		}
	}
	
	
	static List dc(DetachedCriteria dc){
		Session s = HibernateUtils.getSession();
		Criteria c = dc.getExecutableCriteria(s);
		
		List rs = c.list();
		s.close();
		return rs;
	}
	
	static List sql(){
		Session s = HibernateUtils.getSession();
		Query q = s.createSQLQuery("select * from user").addEntity(User.class);
		List<User> rs = q.list();
		for(User u: rs)
		System.out.println(u.getName());
		s.close();
		return rs;
	}
	
	static List namedQuery(){
		Session s = HibernateUtils.getSession();
		Query q = s.getNamedQuery("");
		q.setXXXX(x, arg1);
		return q.list();
	}
}
