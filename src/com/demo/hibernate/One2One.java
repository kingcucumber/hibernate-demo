package com.demo.hibernate;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.demo.dao.HibernateUtils;
import com.demo.domain.IdCard;
import com.demo.domain.Person;

public class One2One {
	public static void main(String[] args) {
		add();
		//query(1);
		query2(1);
	}

	static Person add() {

		Session s = null;
		Transaction tx = null;
		try {
			s = HibernateUtils.getSession();
			IdCard idCard = new IdCard();
			idCard.setUsefulLife(new Date());
			Person p = new Person();
			p.setName("p1");
			p.setIdCard(idCard);

			idCard.setPerson(p);

			tx = s.beginTransaction();
			s.save(p);
			s.save(idCard);
			tx.commit();
			return p;
		} finally {
			if (s != null) {
				s.close();
			}
		}
	}

	static Person query(int id) {

		Session s = null;
		Transaction tx = null;
		try {
			s = HibernateUtils.getSession();
			tx = s.beginTransaction();
			Person p = (Person) s.get(Person.class, id);
			System.out.println(p.getIdCard().getUsefulLife());
			tx.commit();
			return p;
		} finally {
			if (s != null) {
				s.close();
			}
		}
	}

	static Person query2(int id) {

		Session s = null;
		Transaction tx = null;
		try {
			s = HibernateUtils.getSession();
			tx = s.beginTransaction();
			IdCard idCard = (IdCard) s.get(IdCard.class, id);
			System.out.println(idCard.getPerson().getName());
			tx.commit();
			return null;
		} finally {
			if (s != null) {
				s.close();
			}
		}
	}
}
