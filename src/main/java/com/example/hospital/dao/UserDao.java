package com.example.hospital.dao;

import java.util.List;

import org.hibernate.query.Query;

import com.example.hospital.POJO.*;
import java.lang.*;

import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;


public class UserDao extends Dao{

	public UserDao() {

	}
	@Transactional
	public List<User> getUsers() {
		Query query = getSession().createQuery("FROM User");
		List<User> list = query.list();
		return list;
	}
	
	@Transactional
	public void saveUser(User user) {
		try{
			beginTransaction();
	        getSession().save(user);
		    commitTransaction();
		} catch (Exception e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        throw e;
		} 
	}
	
	//for valid user
	@Transactional
	public User getUserByUsername(String username)
	{
		   Query<User> query = getSession().createQuery("FROM User WHERE username = :username", User.class);
	        query.setParameter("username", username);
	        
	        try {
	            return query.getSingleResult();
	        } catch (NoResultException ex) {
	            return null;
	        }
	 }
	
	@Transactional
	public void updateUser(User user) {
        try {
            beginTransaction();
            getSession().update(user); 
            commitTransaction();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }
	
	public boolean uniqueUsername(String username) {
		
        try {
        	Query<User> query = getSession().createQuery("FROM User WHERE username = :username", User.class);
            query.setParameter("username", username);
            User user=query.getSingleResult();
            	return false;
           
        } catch (NoResultException ex) {
            return true;
        }
		
		
	}
	
	public void deleteUser(User user) {
		try{
			 beginTransaction();
	        getSession().delete(user);
	        commitTransaction();
		} catch (Exception e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        throw e;
		} 
	}
		
	
}
