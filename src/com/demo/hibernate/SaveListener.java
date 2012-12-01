package com.demo.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.event.SaveOrUpdateEvent;
import org.hibernate.event.SaveOrUpdateEventListener;

import com.demo.domain.User;

public class SaveListener implements SaveOrUpdateEventListener {

	@Override
	public void onSaveOrUpdate(SaveOrUpdateEvent event)
			throws HibernateException {
		// TODO Auto-generated method stub

		if (event.getObject() instanceof User) {
			User user = (User) event.getObject();
			System.out.println("---" + user.getName().getFirstName() + ":"
					+ user.getName().getLastName());
		}
	}

}
