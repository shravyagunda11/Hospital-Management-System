package com.example.hospital.Controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.hospital.POJO.*;
import com.example.hospital.dao.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class PrescriptionController {

	@GetMapping("/viewPatientHistory")
	public String viewPatientHistory(Model model, UserDao userdao,HttpSession session,PatientDao patdao,@RequestParam("apptId") int apptId,@RequestParam("patUserName") String patUserName) {
		User presentUser= (User)session.getAttribute("user");
		
		model.addAttribute("apptId", apptId);
	
		model.addAttribute("patUserName", patUserName);
		model.addAttribute("prescription", new Prescription());
		return "Prescription";
	}
	
	 @PostMapping("/savePrescription")
	    public String savePrescription(@ModelAttribute Prescription prescription ,@RequestParam("apptId") int apptId,@RequestParam("patUserName") String patUserName,AppointmentDao apptdao, Model model,HttpSession session) {
		 System.out.println("Appt ID: " + apptId);
		  
		 String directoryPath = "/Users/shravyagunda/Desktop/test";
		 User presentUser= (User)session.getAttribute("user");
		 Appointment appt = apptdao.getAppointmentByAppointmentId(apptId);
		
		 
        String fileName = patUserName +"_" + Integer.toString(apptId) +"_prescription.pdf";
        appt.setFileName(fileName);
        apptdao.updateAppointment(appt);
        try (FileOutputStream fos = new FileOutputStream(new File(directoryPath, fileName))) {
            Document document = new Document();
            PdfWriter.getInstance(document, fos);
            document.open();

            List<Tablet> tabletEntries = prescription.getTablets();

            for (Tablet tabletEntry : tabletEntries) {
                document.add(new Paragraph("Tablet Name: " + tabletEntry.getTabletName()));
                document.add(new Paragraph("Number of Days: " + tabletEntry.getDays()));
                document.add(new Paragraph("Breakfast: " + (tabletEntry.isBreakfast() ? "Yes" : "No")));
                document.add(new Paragraph("Lunch: " + (tabletEntry.isLunch() ? "Yes" : "No")));
                document.add(new Paragraph("Dinner: " + (tabletEntry.isDinner() ? "Yes" : "No")));
                document.add(new Paragraph("")); // Add empty line between entries
            }

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
       return "Saved-Prescription";
	    }
	 
//	 @GetMapping("/downloadPrescription")
//	 public String dowloadPrescription(@RequestParam("apptId") int apptId,AppointmentDao apptdao, HttpServletResponse response) {
//		  try {
//		 Appointment appt = apptdao.getAppointmentByAppointmentId(apptId);
//         String fileName = appt.getFileName();
//         
//         response.setContentType("application/pdf");
//         response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
//         String directoryPath = "/Users/shravyagunda/Desktop/test";
//         File file = new File(directoryPath, fileName);
//         try (FileInputStream inputStream = new FileInputStream(file)) {
//             // Copy the input stream to the response output stream
//             int nRead;
//             while ((nRead = inputStream.read()) != -1) {
//                 response.getOutputStream().write(nRead);
//             }
//             response.getOutputStream().flush();
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     } catch (Exception e) {
//         e.printStackTrace();
//         // Handle any exceptions (e.g., appointment not found)
//         // You might redirect to an error page or handle it as needed
//     }
//		 return "succ";
//	 }
	 @GetMapping("/downloadPrescription")
	    public void viewPrescription(@RequestParam("apptId") int apptId, AppointmentDao apptdao, HttpServletResponse response) {
	        try {
	            Appointment appt = apptdao.getAppointmentByAppointmentId(apptId);
	            String fileName = appt.getFileName();
	            String directoryPath = "/Users/shravyagunda/Desktop/test";
	            File file = new File(directoryPath, fileName);

	            if (file.exists()) {
	                
	                response.setContentType("application/pdf");
	                response.setHeader("Content-Disposition", "inline; filename=\"" + fileName + "\"");

	                
	                try (FileInputStream inputStream = new FileInputStream(file);
	                     OutputStream outputStream = response.getOutputStream()) {
	                    byte[] buffer = new byte[1024];
	                    int bytesRead;
	                    while ((bytesRead = inputStream.read(buffer)) != -1) {
	                        outputStream.write(buffer, 0, bytesRead);
	                    }
	                } catch (IOException e) {
	                    e.printStackTrace();
	                   
	                }
	            } else {
	               
	                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            
	            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        }
	    }
	 
	
}
	 
