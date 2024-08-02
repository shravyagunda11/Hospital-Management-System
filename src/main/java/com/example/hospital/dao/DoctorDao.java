package com.example.hospital.dao;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.query.Query;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import jakarta.persistence.criteria.*;
import java.util.stream.Collectors;

import com.example.hospital.POJO.Doctor;
import com.example.hospital.POJO.Patient;
import com.example.hospital.POJO.User;

import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;


public class DoctorDao extends Dao {

	public DoctorDao() {
		
	}
	
	public void saveDoctor(Doctor doctor) {
		try{
			beginTransaction();
	        getSession().save(doctor);
		    commitTransaction();
		} catch (Exception e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        throw e;
		} 
	}
	

	public Doctor getDoctorByUserId(User user)
	{
		   Query<Doctor> query = getSession().createQuery("FROM Doctor doc WHERE doc.user.userId = :userId", Doctor.class);
	        query.setParameter("userId", user.getUserId());
	        
	        try {
	            return query.getSingleResult();
	            
	        } catch (NoResultException ex) {
	            return null;
	        }
	 }
	
	
	public List<Doctor> getAllDoctors(){
		
		Query query = getSession().createQuery("From Doctor");
		List<Doctor> doclist = query.list();
		return doclist;
	}
	
	
	public Doctor getDoctorByDoctorId(int doctorId) {
		
		Query<Doctor> query = getSession().createQuery("FROM Doctor  WHERE doctorId = :doctorId", Doctor.class);
        query.setParameter("doctorId", doctorId);
        
        try {
            return query.getSingleResult();
            
        } catch (NoResultException ex) {
            return null;
        }
	}
	
	public List<Doctor> filterDoctors(Integer experience, String department) {
		
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<Doctor> criteriaQuery = builder.createQuery(Doctor.class);  
		
		 Root<Doctor> root = criteriaQuery.from(Doctor.class);

	        List<Predicate> predicates = new ArrayList<>();

	       
	       
	        if (experience != null) {
	            predicates.add(builder.ge(root.get("experience"), experience));
	        }
	        if (department != null && !department.isEmpty()) {
	            predicates.add(builder.equal(root.get("department"), department));
	        }

	        criteriaQuery.select(root).where(predicates.toArray(new Predicate[0]));

	        
	        List<Doctor> filteredDoctors = getSession().createQuery(criteriaQuery).getResultList();

	        return filteredDoctors;

    }
	
	  public void updateDoctor(Doctor doc) 
		{
		        try {
		            beginTransaction();
		            getSession().update(doc); 
		            commitTransaction();
		        } catch (Exception e) {
		            if (tx != null && tx.isActive()) {
		                tx.rollback();
		            }
		            throw e;
		        }
		 }
	  
	  public void deleteDoctor(Doctor doc) {
			try{
				 beginTransaction();
		        getSession().delete(doc);
		        commitTransaction();
			} catch (Exception e) {
		        if (tx != null) {
		            tx.rollback();
		        }
		        throw e;
			} 
		}
	
	
}
