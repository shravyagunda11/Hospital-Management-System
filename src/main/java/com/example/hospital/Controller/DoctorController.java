package com.example.hospital.Controller;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.MailSender;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import jakarta.mail.Authenticator;


import com.example.hospital.POJO.*;
import com.example.hospital.dao.*;

import jakarta.servlet.http.HttpSession;

@Controller
public class DoctorController {
	 @Autowired
	    MailSender javaMailSender;
	
	
	@GetMapping("/doctorRegistration")
    public String showRegistrationForm(Model model){
		
		model.addAttribute("userDoctorWrapper", new UserDoctorWrapper());
		
		System.out.println("Controller invoked");
		return "Doctor-Register";
    }
	
	@PostMapping("/doctorRegistration")
	public String processRegistrationForm(@ModelAttribute("userDoctorWrapper") UserDoctorWrapper userDoctorWrapper, Model model, UserDao userdao,DoctorDao docdao) {
		
		User user = userDoctorWrapper.getUser();
        Doctor doctor = userDoctorWrapper.getDoctor();
        String username=user.getUsername();
        boolean flag = userdao.uniqueUsername(username);
        String pwd_pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        String gmail_pattern = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";
        if(flag)
        {
        	if(Pattern.compile(pwd_pattern).matcher(user.getPassword()).matches()) {
	        	if(Pattern.compile(gmail_pattern).matcher(user.getEmail()).matches())
	        	{
	        		user.setRole("Doctor");
		    		userdao.saveUser(user);
		    		
		    		doctor.setUser(user);
		    		docdao.saveDoctor(doctor);
		    		return "user-success";
	    		}
	        	
	        	else {
	        		model.addAttribute("errorMessageEmail", "Email should be valid");
	            	return "Doctor-Register";
	        	}
        	}
        	else {
        		model.addAttribute("errorMessagePwd", "Password should have 1 lowercase,1 uppercase,1 number and 1 special character");
            	return "Doctor-Register";
        	}
        	
        	
        }
        else
        {
        	model.addAttribute("errorMessage", "Username already exists. Please try again.");
        	return "Doctor-Register";
        }
        
        
	}
	
	@GetMapping("/viewDoctorAppointments")
	public String viewDoctorAppointments(@ModelAttribute("userDoctorWrapper") UserDoctorWrapper userDoctorWrapper, Model model, UserDao userdao,DoctorDao docdao,AppointmentDao apptdao, HttpSession session) {
		
	
		 User presentUser= (User)session.getAttribute("user");
		 System.out.println("user name "+ presentUser.getUsername());
		 List<Appointment> apptlist=apptdao.getAppointmentByDoctorId((docdao.getDoctorByUserId(presentUser).getDoctorId()));
		 System.out.println("appt list "+ apptlist);
		 model.addAttribute("apptlist",apptlist);
		 
		 return "Doctor-Appointments";  
		
	}
	
	@PostMapping("/viewDoctorAppointments")
	public String setDoctorAppointmentsStatus(
	        @RequestParam("apptId") int apptId,
	        @RequestParam("newStatus") String newStatus,
	       
	        Model model,
	        UserDao userdao,
	        DoctorDao docdao,
	        AppointmentDao apptdao,
	        HttpSession session) {

	    Appointment appt = apptdao.getAppointmentByAppointmentId(apptId);
	    appt.setStatus(newStatus);
	    String subject="Your appointment is confiremd";
	    String body = "Your appointment details are : DATE" +appt.getApptDate().toString() + " and TIME " +appt.getApptTime().toString();
	    
	    

	    apptdao.saveAppointment(appt);
	    System.out.println(appt.getStatus() + "   the valuse");
	    if(appt.getStatus().equals("confirmed")) {
	    	System.out.println("Inside if loop of email");
	    	System.out.println(appt.getPat().getUser().getEmail());
	  

	    }
	    return "redirect:/viewDoctorAppointments";
	}
	
	 @GetMapping("/Doctor-Home") 
	 public String showPatientHome() {
	        
	    return "Doctor-Home"; 
	 }
	
	 @GetMapping("/doctorUpdate")
		public String viewProfile(@ModelAttribute("userDoctorWrapper") UserDoctorWrapper userDoctorWrapper,  Model model, UserDao userdao,DoctorDao docdao,HttpSession session) {
			User presentUser= (User)session.getAttribute("user");
			
			
			Doctor presentDoctor=docdao.getDoctorByUserId(presentUser);
			userDoctorWrapper = new UserDoctorWrapper();
			userDoctorWrapper.setUser(presentUser);
			userDoctorWrapper.setDoctor(presentDoctor);
		    model.addAttribute("userDoctorWrapper", userDoctorWrapper);
			System.out.print("User: " + presentDoctor.getFname());
			System.out.print("User: " + presentUser.getUserId());
			
			
			return "Doctor-Profile";
		}
		
		@PostMapping("/doctorUpdate")
		public String updateProfile(@ModelAttribute("userDoctorWrapper") UserDoctorWrapper userDoctorWrapper, Model model, UserDao userdao,DoctorDao docdao,HttpSession session) {
			User presentUser= (User)session.getAttribute("user");
			Doctor presentDoctor=docdao.getDoctorByUserId(presentUser);
			
			presentDoctor.setFname(userDoctorWrapper.getDoctor().getFname());
			presentDoctor.setLname(userDoctorWrapper.getDoctor().getLname());
			presentDoctor.setDepartment(userDoctorWrapper.getDoctor().getDepartment());
			presentDoctor.setEducation(userDoctorWrapper.getDoctor().getEducation());
			presentDoctor.setExperience(userDoctorWrapper.getDoctor().getExperience());
		    docdao.updateDoctor(presentDoctor);
		    
		    presentUser.setUsername(userDoctorWrapper.getUser().getUsername());
		    presentUser.setPassword(userDoctorWrapper.getUser().getPassword());
		    userdao.updateUser(presentUser);
			
			return "Doctor-Profile-Updated";
		}
	
	
}
