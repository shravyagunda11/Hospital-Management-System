package com.example.hospital.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.hospital.POJO.*;
import com.example.hospital.dao.*;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {
	
	@GetMapping("/admin-home")
	public String adminHome() {
		return "Admin-Home";
	}
	 @GetMapping("/admin-view-patients")
	    public String viewPatients( Model model,DoctorDao docdao,HttpSession session,PatientDao patdao) {
	       
		 List<Patient> patlist = patdao.getAllPatients();
		 model.addAttribute("patList", patlist);
	        return "Admin-View-Pat"; 
	    }
	 
	 @GetMapping("/admin-view-doctors")
	    public String viewDoctors( Model model,DoctorDao docdao,HttpSession session,PatientDao patdao) {
	       
		 List<Doctor> doclist = docdao.getAllDoctors();
		 for (Doctor doctor : doclist) {
		        System.out.println(doctor.getFname() + " "  
		                           + " | Department: " + doctor.getDepartment() 
		                           + " | Experience: " + doctor.getExperience());
		    }
		 model.addAttribute("docList", doclist);
	        return "Admin-View-Doc"; 
	    }
	 
	 @GetMapping("/delete-doctor")
	    public String deleteDoctor( @RequestParam(required = false) Integer doctorId,Model model,DoctorDao docdao,HttpSession session,PatientDao patdao,AppointmentDao apptdao) {
	       Doctor doc = docdao.getDoctorByDoctorId(doctorId);
	       apptdao.deleteAppointmentsByDoctortId(doc.getDoctorId());
			docdao.deleteDoctor(doc);
			List<Doctor> doclist = docdao.getAllDoctors();
			model.addAttribute("docList", doclist);
	        return "Admin-View-Doc"; 
	    }
	 
	 @GetMapping("/delete-patient")
	 public String delete(@RequestParam(required = false) Integer patientId,Model model,PatientDao patdao,HttpSession session,UserDao userdao, AppointmentDao apptdao) {
		
			
			
			Patient presentPatient=patdao.getPatientByPatientId(patientId);
			 apptdao.deleteAppointmentsByPatientId(presentPatient.getPatientId());
			patdao.deletePatient(presentPatient);
			List<Patient> patlist = patdao.getAllPatients();
			 model.addAttribute("patList", patlist);
		 
		 return "Admin-View-Pat";
	 }
}
