package com.example.hospital.dao;

import java.util.List;

import org.hibernate.query.Query;

import com.example.hospital.POJO.Appointment;
import com.example.hospital.POJO.Doctor;
import com.example.hospital.POJO.User;

import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;


public class AppointmentDao extends Dao{

	@Transactional
	public void saveAppointment(Appointment appt) {
		try{
			beginTransaction();
	        getSession().save(appt);
		    commitTransaction();
		} catch (Exception e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        throw e;
		} 
	
	}
	
	@Transactional
	public List<Appointment> getAllAppointment(){
		
		Query query = getSession().createQuery("From Appointment");
		List<Appointment> apptlist = query.list();
		return apptlist;
	}
	
	public Appointment getAppointmentByAppointmentId(int apptId) {
		Query<Appointment> query = getSession().createQuery("FROM Appointment WHERE apptId = :apptId", Appointment.class);
        query.setParameter("apptId", apptId);
        
        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
		
	}
	
	public List<Appointment> getAppointmentByPatientId(int patientId){
		
		Query<Appointment> query = getSession().createQuery("From Appointment appt Where appt.pat.patientId = :patientId", Appointment.class);
		query.setParameter("patientId", patientId);
		List<Appointment> apptlist = query.list();
		return apptlist;
	}
	
	public List<Appointment> getAppointmentByDoctorId(int doctorId){
		
		Query<Appointment> query = getSession().createQuery("From Appointment appt Where appt.doc.doctorId = :doctorId", Appointment.class);
		query.setParameter("doctorId", doctorId);
		List<Appointment> apptlist = query.list();
		return apptlist;
	}
	
	@Transactional
	public void updateAppointment(Appointment appt) {
        try {
            beginTransaction();
            getSession().update(appt); 
            commitTransaction();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }
	
	public void deleteAppointmentsByPatientId(int patientId) {
	    try {
	        beginTransaction();
	        Query query = getSession().createQuery("DELETE FROM Appointment appt WHERE appt.pat.patientId = :patientId");
	        query.setParameter("patientId", patientId);
	        int rowCount = query.executeUpdate();
	        commitTransaction();
	        System.out.println("Deleted " + rowCount + " appointments for patient with ID: " + patientId);
	    } catch (Exception e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        e.printStackTrace();
	    }
	}
	
	public void deleteAppointmentsByDoctortId(int doctorId ) {
	    try {
	        beginTransaction();
	        Query query = getSession().createQuery("DELETE FROM Appointment appt WHERE appt.doc.doctorId = :doctorId ");
	        query.setParameter("doctorId", doctorId );
	        int rowCount = query.executeUpdate();
	        commitTransaction();
	        System.out.println("Deleted " + rowCount + " appointments for patient with ID: " + doctorId );
	    } catch (Exception e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        e.printStackTrace();
	    }
	}
	
	public void deleteAppointmentById(int apptId) {
	    try {
	        beginTransaction();
	        Query query = getSession().createQuery("DELETE FROM Appointment appt WHERE appt.apptId = :apptId");
	        query.setParameter("apptId", apptId);
	        int rowCount = query.executeUpdate();
	        commitTransaction();
	       
	    } catch (Exception e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        e.printStackTrace();
	    }
	}

	
}
