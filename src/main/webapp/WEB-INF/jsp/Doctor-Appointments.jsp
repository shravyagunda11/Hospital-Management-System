<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
h2 {
            text-align: center;
            margin-top: 50px;
        }
    .link-btn {
        background-color: #4CAF50;
        color: white;
        padding: 10px 20px;
        font-size: 16px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        text-decoration: none;
        display: inline-block;
        margin-right: 10px;
    }
  
    .link-btn:hover {
        background-color: #45a049;
    }
    .logout-btn {
        background-color: #f44336;
    }
    .logout-btn:hover {
        background-color: #d32f2f;
    }
     body{
     background-color: #d89292;
     }
 </style>

</head>
<body>
<h2>Doctor Appointments</h2>
    <table border="1">
        <tr>
            <th>Appointment Date</th>
            <th>Appointment Time</th>
            <th>Patient Name</th>
            <th>Current status</th>
            <th>Add Prescription</th>
            
        </tr>
        <c:forEach var="appointment" items="${apptlist}" varStatus="loop">
            <tr>
                <td>${appointment.apptDate}</td>
                <td>${appointment.apptTime}</td>
                <td>${appointment.pat.fname} ${appointment.pat.lname}</td>
              
                <td>
                    <form action="/viewDoctorAppointments" method="post">
                        <input type="hidden" name="apptId" value="${appointment.apptId}">
                        <select name="newStatus">
                            <option value="pending" ${appointment.status eq 'pending' ? 'selected' : ''}>Pending</option>
                            <option value="confirmed" ${appointment.status eq 'confirmed' ? 'selected' : ''}>Confirmed</option>
                            <option value="cancelled" ${appointment.status eq 'cancelled' ? 'selected' : ''}>Cancelled</option>
                        </select>
                        <button type="submit">Confirm</button>
                    </form>
                </td>
               <td>
		          <form:form action="/viewPatientHistory" method="get">
		          <input type="hidden" name="apptId" value="${appointment.apptId}">
		          <input type="hidden" name="patUserName" value="${appointment.pat.user.username}">
		                <button type="submit">Add Prescription</button>
		          </form:form>
          		</td>
                
            </tr>
        </c:forEach>
        
    </table>
    <a href="/Doctor-Home" class="link-btn">Go To Homepage</a>
         <a href="<c:url value='/logout'/>" class="link-btn logout-btn">Logout</a>
</body>
</html>