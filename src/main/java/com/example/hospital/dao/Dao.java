package com.example.hospital.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Dao {

	
	Session session;
	Transaction tx;
	
	private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	
	public Dao() {
		
		session = sessionFactory.openSession();
		tx = null;
	}

	public void beginTransaction() {
		
		tx = session.beginTransaction();
	}

	public Session getSession() {
		return this.session;
	}

	protected void commitTransaction() {
		tx.commit();
	}

	public void closeSession() {
		session.close();
	}
	
	
	
}
