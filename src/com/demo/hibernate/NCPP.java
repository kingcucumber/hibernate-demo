package com.demo.hibernate;

import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;

import com.demo.dao.HibernateUtils;
import com.demo.domain.User;

public class NCPP {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	static void iterator() {
		Session s = HibernateUtils.getSession();
		Query q = s.createQuery("from User");

		Iterator<User> users = q.iterate();
		while (users.hasNext()) {
			System.out.println(users.next().getName().getFirstName());
		}
	}

}
