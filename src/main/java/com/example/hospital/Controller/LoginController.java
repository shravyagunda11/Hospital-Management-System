package com.example.hospital.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.hospital.POJO.*;
import com.example.hospital.dao.*;

import jakarta.servlet.http.HttpSession;


@Controller
public class LoginController {
	
	@GetMapping("/login")
    public String login(Model model){
		
		System.out.println("login Controller invoked");
		User user= new User();
		
		model.addAttribute("user", user);
		return "Login";
    }
	
	@PostMapping("/login")
    public String success(@ModelAttribute("user") User user, DoctorDao docdao,PatientDao patdao, UserDao userdao,HttpSession session, Model model){
		
		System.out.println("Controller success invoked");
		
		String username = user.getUsername();
		String pass = user.getPassword();
		if(username.equals("admin")&&pass.equals("admin")) {
			
			return "Admin-Home";
		}
		else {
		User userExist = userdao.getUserByUsername(username);
		 if(userExist!=null&&userExist.getPassword().equals(pass)&&userExist.getUsername().equals(username)) {
			 
			 session.setAttribute("user", userExist);
		 }
		 else {
			 model.addAttribute("errorMessage", "Invalid username or password. Please try again.");
	            return "Login";
		 }
		 
		 session.setAttribute("user", userExist);
		 
		 if(userExist.getRole().equalsIgnoreCase("patient")) {
			 Patient pat = patdao.getPatientByUserId(userExist);
			 model.addAttribute("patient", pat);
			 return "Patient-Home";
		 }
		 else {
			 Doctor doc = docdao.getDoctorByUserId(userExist);
			 model.addAttribute("doctor",doc);
			 return "Doctor-Home";
			 
		 }		 
		}
    }
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/login";
	}
}
