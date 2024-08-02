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
      body {
        
        background-color: #d89292;
    }
</style>
</head>
<body>
<h2>Your Appointments</h2>
    <table border="1">
        <tr>
            <th>Appointment Date</th>
            <th>Appointment Time</th>
            <th>Doctor Name</th>
            <th>Doctor Department</th>
            <th>Appointment Status</th>
            <th>Cancel Appointment</th>
            <th>Download Prescription</th>
        </tr>
        <c:forEach var="appointment" items="${apptlist}">
            <tr>
                <td>${appointment.apptDate}</td>
                <td>${appointment.apptTime}</td>
                <td>${appointment.doc.fname} ${appointment.doc.lname}</td>
                <td>${appointment.doc.department}</td>
                <td>${appointment.status}</td>
                <td>
                    <form action="/cancel-appointment" method="get">
                        <input type="hidden" name="apptId" value="${appointment.apptId}">
                        <button type="submit">Cancel appointment</button>
                    </form>
                </td>
                 <td>
                    <form action="/downloadPrescription" method="get">
                        <input type="hidden" name="apptId" value="${appointment.apptId}">
                        <button type="submit">Download Prescription</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
         <a href="/Patient-Home" class="link-btn">Go To Homepage</a>
         <a href="<c:url value='/logout'/>" class="link-btn logout-btn">Logout</a>
</body>
</html>