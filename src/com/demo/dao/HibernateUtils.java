package com.demo.dao;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public final class HibernateUtils {
	private static SessionFactory sessionFactory;

	private HibernateUtils() {
	}

	static {
		Configuration cfg = new Configuration();
		cfg.configure();// cfg.configure("filename");
		sessionFactory = cfg.buildSessionFactory();
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static Session getSession() {
		return sessionFactory.openSession();
	}

	public static void add(Object entity) {
		Session s = null;
		Transaction tx = null;

		try {
			s = HibernateUtils.getSession();
			tx = s.beginTransaction();
			s.save(entity);
			tx.commit();
		} finally {
			if (s != null) {
				s.close();
			}
		}
	}

	public static void update(Object entity) {
		Session s = null;
		Transaction tx = null;

		try {
			s = HibernateUtils.getSession();
			tx = s.beginTransaction();
			s.update(entity);
			tx.commit();
		} finally {
			if (s != null) {
				s.close();
			}
		}
	}

	public static void delete(Object entity) {
		Session s = null;
		Transaction tx = null;

		try {
			s = HibernateUtils.getSession();
			tx = s.beginTransaction();
			s.delete(entity);
			tx.commit();
		} finally {
			if (s != null) {
				s.close();
			}
		}
	}

	public static Object get(Class clazz, Serializable id) {
		Session s = null;
		Transaction tx = null;

		try {
			s = HibernateUtils.getSession();
			Object obj = s.get(clazz, id);
			return obj;
		} finally {
			if (s != null) {
				s.close();
			}
		}
	}
}
