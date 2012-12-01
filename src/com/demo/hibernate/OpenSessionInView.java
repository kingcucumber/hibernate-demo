package com.demo.hibernate;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.demo.dao.HibernateUtils;

public class OpenSessionInView implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.getThreadLocalSession();
			tx = session.beginTransaction();
			arg2.doFilter(arg0, arg1);
		} catch (Exception e) {
			// handle exception
			if (tx != null) {
				tx.rollback();
				throw new RuntimeException(e.getMessage(), e);
			}
		} finally {
			HibernateUtils.closeSession();
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
