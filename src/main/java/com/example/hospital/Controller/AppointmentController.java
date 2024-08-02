package com.example.hospital.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.hospital.POJO.*;
import com.example.hospital.dao.*;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Controller
public class AppointmentController {

	 @GetMapping("/book-appointment")
	    public String bookAppointment(@RequestParam("doctorId") int doctorId, Model model,DoctorDao docdao,HttpSession session) {
	       
		    
		    Appointment appointment = new Appointment();
		    model.addAttribute("appointment", appointment);
	        model.addAttribute("doctorId", doctorId);
	        session.setAttribute("doctorID", doctorId);
	        System.out.println("dpctpr id is inside get " +model.getAttribute("doctorId"));
	        return "Book-Appointment"; 
	    }
	 
	 
	 @PostMapping("/book-appointment")

	 public String confirmAppointment(@ModelAttribute("appointment") Appointment appointment,HttpSession session,PatientDao patdao,DoctorDao docdao,AppointmentDao apptdao,Model model) {
		 
		 User user = (User)session.getAttribute("user");
		 int docid =(Integer)session.getAttribute("doctorID");
		 
		 Doctor doctor = docdao.getDoctorByDoctorId(docid);
		 
		 Patient pat = patdao.getPatientByUserId(user);
		 appointment.setDoc(doctor);
		 appointment.setPat(pat);
		 appointment.setStatus("Pending");
		 appointment.setFileName("temp");
		 apptdao.saveAppointment(appointment);
		 
		 return "Confirmed-Appointment";
		 
	 }
	 

}
