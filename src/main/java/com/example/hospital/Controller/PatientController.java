package com.example.hospital.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.hospital.dao.*;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import com.example.hospital.POJO.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.hibernate.Session;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.hospital.POJO.Patient;

@Controller
public class PatientController {

	@GetMapping("/patientRegistration")
    public String showRegistrationForm(Model model){
		
		model.addAttribute("userPatientWrapper", new UserPatientWrapper());
		
		System.out.println("Controller invoked");
		
		return "Patient-Register";
    }
	
	@PostMapping("/patientRegistration")
	public String processRegistrationForm(@ModelAttribute("userPatientWrapper") UserPatientWrapper userPatientWrapper, Model model, UserDao userdao,PatientDao patdao) {
		
		User user = userPatientWrapper.getUser();
        Patient patient = userPatientWrapper.getPatient();
        String username=user.getUsername();
        boolean flag = userdao.uniqueUsername(username);
        System.out.println(flag);
        String pwd_pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        String gmail_pattern = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";
       
        if(flag) {
        	if(Pattern.compile(pwd_pattern).matcher(user.getPassword()).matches()) {
        		System.out.println("inside firts if");
            	if(Pattern.compile(gmail_pattern).matcher(user.getEmail()).matches())
            	{
            		System.out.println("inside if");
            		user.setRole("Patient");
            		userdao.saveUser(user);
            		
            		patient.setUser(user);
            		patdao.savePatient(patient);
            		return "user-success";
            	}
            	else {
            		System.out.println("inside firts else");
                	model.addAttribute("errorMessageEmail", "Email should be valid");
                	return "Patient-Register";
                }
        		
        	}
        	else
        	{
        		model.addAttribute("errorMessagePwd", "Password should have 1 lowercase,1 uppercase,1 number, 1 special character and 8 digits");
            	return "Patient-Register";
        	}
        	
        }
        else
        {
        	System.out.println("inside fsecond else");
        	model.addAttribute("errorMessage", "Username already exists. Please try again.");
        	return "Patient-Register";
        }
        
			
	}
	
	@GetMapping("/patientUpdate")
	public String viewProfile(@ModelAttribute("userPatientWrapper") UserPatientWrapper userPatientWrapper, Model model, UserDao userdao,PatientDao patdao,HttpSession session) {
		User presentUser= (User)session.getAttribute("user");
		
		
		Patient presentPatient=patdao.getPatientByUserId(presentUser);
		userPatientWrapper = new UserPatientWrapper();
	    userPatientWrapper.setUser(presentUser);
	    userPatientWrapper.setPatient(presentPatient);
	    model.addAttribute("userPatientWrapper", userPatientWrapper);
		System.out.print("User: " + presentPatient.getFname());
		System.out.print("User: " + presentUser.getUserId());
		
		
		return "Patient-Profile";
	}
	
	@PostMapping("/patientUpdate")
	public String updateProfile(@ModelAttribute("userPatientWrapper") UserPatientWrapper userPatientWrapper, Model model, UserDao userdao,PatientDao patdao,HttpSession session) {
		User presentUser= (User)session.getAttribute("user");
		Patient presentPatient=patdao.getPatientByUserId(presentUser);
		
		presentPatient.setFname(userPatientWrapper.getPatient().getFname());
	    presentPatient.setLname(userPatientWrapper.getPatient().getLname());
	    presentPatient.setDob(userPatientWrapper.getPatient().getDob());
	    presentPatient.setGender(userPatientWrapper.getPatient().getGender());
	    patdao.updatePatient(presentPatient);
	    
	    presentUser.setUsername(userPatientWrapper.getUser().getUsername());
	    presentUser.setPassword(userPatientWrapper.getUser().getPassword());
	    userdao.updateUser(presentUser);
		
		return "Patient-Profile-Updated";
	}
	
	@GetMapping("/viewDoctors")
	public String viewDoctors(HttpSession session,Model model, DoctorDao docdao) {
		
		List<Doctor> doclist = docdao.getAllDoctors();
		model.addAttribute("doclist",doclist);
		System.out.println("List of Doctors:");
	    for (Doctor doctor : doclist) {
	        System.out.println(doctor.getFname() + " "  
	                           + " | Department: " + doctor.getDepartment() 
	                           + " | Experience: " + doctor.getExperience());
	    }
	    Set<String> departments = new HashSet<>();
        for (Doctor doctor : doclist) {
            departments.add(doctor.getDepartment());
        }
        model.addAttribute("departments", departments);
		return "Show-Doctors";
	}
	@GetMapping("/filter-doctors")
	public String filterDoctors(@RequestParam(required = false) Integer experience,
	                            @RequestParam(required = false) String department,
	                            Model model, DoctorDao docdao) {
	   
	    List<Doctor> filteredDoctors = docdao.filterDoctors(experience, department);
	    
	    
	    model.addAttribute("doclist", filteredDoctors);
	    Set<String> departments = new HashSet<>();
        for (Doctor doctor : filteredDoctors) {
            departments.add(doctor.getDepartment());
        }
        model.addAttribute("departments", departments);
	    System.out.println("List of filtered Doctors:");
	    for (Doctor doctor : filteredDoctors) {
	        System.out.println(doctor.getFname() + " "  
	                           + " | Department: " + doctor.getDepartment() 
	                           + " | Experience: " + doctor.getExperience());
	    }
	    return "Show-Doctors"; 
	}

	 @GetMapping("/Patient-Home") 
	 public String showPatientHome() {
	        
	    return "Patient-Home"; 
	 }
	 
	 @GetMapping("/viewPatientAppointments")
	 public String viewPatientAppointments(AppointmentDao apptdao,PatientDao patdao,HttpSession session,Model model) {
		 User presentUser= (User)session.getAttribute("user");
		 List<Appointment> apptlist=apptdao.getAppointmentByPatientId((patdao.getPatientByUserId(presentUser).getPatientId()));
		 model.addAttribute("apptlist",apptlist);
		 return "Patient-Appointments";
	 }
	 
	 @GetMapping("/deletePatient")
	 public String delete(PatientDao patdao,HttpSession session,UserDao userdao, AppointmentDao apptdao) {
		 User presentUser= (User)session.getAttribute("user");
			
			
			Patient presentPatient=patdao.getPatientByUserId(presentUser);
			 apptdao.deleteAppointmentsByPatientId(presentPatient.getPatientId());
			patdao.deletePatient(presentPatient);
			
		 
		 return "patient-deleted";
	 }
	 

	 @GetMapping("/cancel-appointment")

	 public String cancelAppointment(@RequestParam("apptId") int apptId,HttpSession session,PatientDao patdao,DoctorDao docdao,AppointmentDao apptdao,Model model) {
		 
		 apptdao.deleteAppointmentById(apptId);
		 User presentUser= (User)session.getAttribute("user");
		 List<Appointment> apptlist=apptdao.getAppointmentByPatientId((patdao.getPatientByUserId(presentUser).getPatientId()));
		 model.addAttribute("apptlist",apptlist);
		 return "Patient-Appointments";
		
		 
	 }
}
