package com.demo.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.demo.dao.HibernateUtils;
import com.demo.dao.UserDao;
import com.demo.domain.User;

public class UserDaoHibernateImpl implements UserDao {

	@Override
	public void saveUser(User user) {
		Session s = null;
		Transaction tx = null;

		try {
			s = HibernateUtils.getSession();
			tx = s.beginTransaction();
			s.save(user);
			tx.commit();

		} finally {
			if (s != null)
				s.close();
		}

	}

	@Override
	public void updateUser(User user) {
		Session s = null;
		Transaction tx = null;

		try {
			s = HibernateUtils.getSession();
			tx = s.beginTransaction();
			s.update(user);
			tx.commit();

		} finally {
			if (s != null)
				s.close();
		}

	}

	@Override
	public User findUserByName(String name) {
		Session s = null;
		try {
			s = HibernateUtils.getSession();
			Criteria c = s.createCriteria(User.class);
			c.add(Restrictions.eq("name", name));
			User user = (User) c.uniqueResult();
			return user;
		} finally {
			if (s != null)
				s.close();
		}
	}

	@Override
	public User findUserById(int id) {
		Session s = null;
		try {
			s = HibernateUtils.getSession();
			User user = (User) s.get(User.class, id);
			return user;
		} finally {
			if (s != null) {
				s.close();
			}
		}
	}

	@Override
	public void remove(User user) {
		Session s = null;
		Transaction tx = null;

		try {
			s = HibernateUtils.getSession();
			tx = s.beginTransaction();
			s.delete(user);
			tx.commit();

		} finally {
			if (s != null)
				s.close();
		}
	}

	public User findUserByName1(String name) {
		Session s = null;
		try {
			s = HibernateUtils.getSession();
			String hql = "from User as user where user.name=:name";
			Query query = s.createQuery(hql);
			query.setString("name", name);
			User user = (User) query.uniqueResult();

			return user;
		} finally {
			if (s != null)
				s.close();
		}
	}

	static void addUser(User user) {
		HibernateUtils.getThreadLocalSession().save(user);
	}
}
