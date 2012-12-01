package com.demo.hibernate;

import java.util.HashMap;
import java.util.Map;

import com.demo.domain.User;

public class CacheDemo {

	static Map cache = new HashMap();

	public static void main(String[] args) {
		User u = getUser(1);

		User u1 = getUser(1);
	}

	public static void update(User user) {
		updateDB(user);
		String key = User.class.getName() + user.getId();
		cache.remove(key);
	}

	public static User getUser(int id) {
		String key = User.class.getName() + id;
		User user = (User) cache.get(key);
		if (user != null) {
			return user;
		}
		user = getFromDB();
		cache.put(key, user);
		return user;
	}

	private static User getFromDB() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void updateDB(User user) {

	}
}
