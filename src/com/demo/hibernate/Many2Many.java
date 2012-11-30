package com.demo.hibernate;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.demo.dao.HibernateUtils;
import com.demo.domain.Student;
import com.demo.domain.Teacher;

public class Many2Many {
	public static void main(String[] args) {
		add();
		query(1);
	}

	static void add() {
		Session s = null;
		Transaction tx = null;
		try {

			Set<Teacher> ts = new HashSet<Teacher>();
			Teacher t1 = new Teacher();
			t1.setName("t1 name");

			Teacher t2 = new Teacher();
			t2.setName("t2 name");

			ts.add(t1);
			ts.add(t2);

			Set<Student> ss = new HashSet<Student>();

			Student s1 = new Student();
			s1.setName("s1 name");

			Student s2 = new Student();
			s2.setName("s2 name");

			ss.add(s1);
			ss.add(s2);

			t1.setStudents(ss);
			t2.setStudents(ss);

			s = HibernateUtils.getSession();
			tx = s.beginTransaction();
			s.save(t1);
			s.save(t2);
			s.save(s1);
			s.save(s2);
			tx.commit();

		} finally {
			if (s != null) {
				s.close();
			}
		}
	}

	static void query(int id) {

		Session s = null;
		Transaction tx = null;
		try {
			s = HibernateUtils.getSession();
			tx = s.beginTransaction();
			Teacher t = (Teacher) s.get(Teacher.class, id);
			System.out.println("students :" + t.getStudents());

			tx.commit();
		} finally {
			if (s != null) {
				s.close();
			}
		}
	}
}
