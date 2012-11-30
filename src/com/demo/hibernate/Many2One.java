package com.demo.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.demo.dao.HibernateUtils;
import com.demo.domain.Department;
import com.demo.domain.Employee;
import com.demo.domain.Sales;
import com.demo.domain.Skiller;

public class Many2One {

	public static void main(String[] args) {
		/*
		 * Department depart = add(); Employee emp = query(1);
		 */

		Department depart = add();
		Employee empl = query(2);

	}

	static Department add() {
		Session s = null;
		Transaction tx = null;

		try {
			Department depart = new Department();
			depart.setName("depart name");

			Employee emp = new Employee();
			emp.setDepart(depart); // Object model: create the association
									// between two object
			emp.setName("emp name");
			
			Skiller emp2 = new Skiller();
			emp2.setDepart(depart); // Object model: create the association
			// between two object
			emp2.setName("emp name2");
			emp2.setSkill("skill");
			
			Sales emp3 = new Sales();
			emp3.setDepart(depart);
			emp3.setName("emp name3");
			emp3.setSell(1);

			s = HibernateUtils.getSession();
			tx = s.beginTransaction();
			s.save(depart);
			s.save(emp);
			s.save(emp2);
			s.save(emp3);
			tx.commit();
			return depart;
		} finally {
			if (s != null) {
				s.close();
			}
		}
	}

	static Employee query(int empId) {
		Session s = null;
		Transaction tx = null;
		try {
			s = HibernateUtils.getSession();
			tx = s.beginTransaction();
			Employee emp = (Employee) s.get(Employee.class, empId);
			System.out.println("dempart name:" + emp.getDepart().getName());
			tx.commit();
			return emp;
		} finally {
			if (s != null) {
				s.close();
			}
		}
	}

	static Department queryDepart(int departId) {
		Session s = null;
		Transaction tx = null;
		try {
			s = HibernateUtils.getSession();
			tx = s.beginTransaction();
			Department depart = (Department) s.get(Department.class, departId);
			System.out.println("dempart name:" + depart.getName());
			tx.commit();
			return depart;
		} finally {
			if (s != null) {
				s.close();
			}
		}
	}
}
