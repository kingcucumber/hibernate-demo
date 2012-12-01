package com.demo.hibernate;

import java.util.Date;

import org.hibernate.Query;
import org.hibernate.classic.Session;

import com.demo.dao.HibernateUtils;
import com.demo.domain.Name;
import com.demo.domain.User;

public class CacheTest {
	public static void main(String[] args) {
		User user = addUser();
		System.out.println("-------------");
		getUser(user.getId());
	}

	private static User getUser(int id) {
		// TODO Auto-generated method stub
		Session s = null;
		try {
			s = (Session) HibernateUtils.getSession();
			Class userClass = User.class;
			User user = (User) s.get(userClass, id);
			System.out.println(user.getClass());
			
//			s.evict(user);
			user = (User) s.get(userClass, id);
			// user = (User) s.load(userClass, id);
//			Query q = s.createQuery("from User where id = " + id);
//			user  = (User) q.uniqueResult();
			
			
			System.out.println(user.getName());
			return user;
		} finally {
			if (s != null) {
				s.close();
			}
		}
	}

	public static User addUser() {
		User user = new User();
		user.setBirthday(new Date());
		Name n = new Name();
		n.setFirstName("first name");
		n.setLastName("last name");
		user.setName(n);
		HibernateUtils.add(user);
		return user;
	}
}
