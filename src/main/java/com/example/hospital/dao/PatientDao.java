package com.example.hospital.dao;
import java.util.List;

import org.hibernate.query.Query;

import com.example.hospital.POJO.Doctor;
import com.example.hospital.POJO.Patient;
import com.example.hospital.POJO.User;

import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;


public class PatientDao extends Dao {

	public PatientDao() {
		
		
	}
	
	@Transactional
	public void savePatient(Patient pat) {
		try{
			beginTransaction();
	        getSession().save(pat);
		    commitTransaction();
		} catch (Exception e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        throw e;
		} 
	
	}
	
	@Transactional
	public Patient getPatientByUserId(User user)
	{
		   Query<Patient> query = getSession().createQuery("FROM Patient pat WHERE pat.user.userId = :userId", Patient.class);
	        query.setParameter("userId", user.getUserId());
	        
	        try {
	            return query.getSingleResult();
	            
	        } catch (NoResultException ex) {
	            return null;
	        }
	 }
	
	@Transactional
	  public void updatePatient(Patient patient) 
	{
	        try {
	            beginTransaction();
	            getSession().update(patient);
	            commitTransaction();
	        } catch (Exception e) {
	            if (tx != null && tx.isActive()) {
	                tx.rollback();
	            }
	            throw e;
	        }
	 }
	
	public void deletePatient(Patient patient) {
		try{
			 beginTransaction();
	        getSession().delete(patient);
	        commitTransaction();
		} catch (Exception e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        throw e;
		} 
	}
	
	public List<Patient> getAllPatients(){
		
		Query query = getSession().createQuery("From Patient");
		List<Patient> doclist = query.list();
		return doclist;
	}
	
public Patient getPatientByPatientId(int patientId) {
		
		Query<Patient> query = getSession().createQuery("FROM Patient  WHERE patientId = :patientId", Patient.class);
        query.setParameter("patientId", patientId);
        
        try {
            return query.getSingleResult();
            
        } catch (NoResultException ex) {
            return null;
        }
	}
}
