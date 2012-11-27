package com.demo.dao;

import com.demo.domain.User;

public interface UserDao {

	public void saveUser(User user);

	public User findUserByName(String name);

	public User findUserById(int id);

	public void updateUser(User user);

	public void remove(User user);

}
