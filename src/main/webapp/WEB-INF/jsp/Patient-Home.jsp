<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Patient Home</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
       		background-color: #d89292;
    
        }

        h2 {
            text-align: center;
            margin-top: 50px;
        }

        .container {
            text-align: center;
            margin-top: 30px;
        }

        .container a {
            display: block;
            margin-bottom: 10px;
            padding: 10px 20px;
            background-color: green;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }

        .container a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <h2>Welcome Patient</h2>

    <div class="container">
        <a href="<c:url value='/patientUpdate'/>">View your profile</a>
        <a href="<c:url value='/viewDoctors'/>">View doctors</a>
        <a href="<c:url value='/viewPatientAppointments'/>">View your appointments</a>

        <a href="<c:url value='/logout'/>" class="logout-btn">Logout</a>
    </div>
</body>
</html>
