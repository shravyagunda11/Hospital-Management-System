<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
           
     background-color: #d89292;
     
        }
        .container {
            width: 50%;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        .form-group {
            margin-bottom: 20px;
        }
        label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }
        input[type="text"],
        input[type="password"],
        input[type="date"] {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            border-radius: 4px;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }
        select {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            border-radius: 4px;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }
        input[type="submit"],
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
    input[type="submit"]:hover,
    .link-btn:hover {
        background-color: #45a049;
    }
    .logout-btn {
        background-color: #f44336;
    }
    .logout-btn:hover {
        background-color: #d32f2f;
    }
     h2 {
        text-align: center;
    }
    </style>
</head>
<body>
<div class="container">
        <h2>Update Profile</h2>
        <form:form modelAttribute="userDoctorWrapper" method="post" action="/doctorUpdate">
            <form:errors path="*"></form:errors>
            <div class="form-group">
                <label for="username">User Name:</label>
                <form:input path="user.username" id="username" value="" />
                <form:errors path="user.username" />
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <form:input path="user.password" id="password" />
                <form:errors path="user.password" />
            </div>
            <div class="form-group">
                <label for="firstName">First Name:</label>
                <form:input path="doctor.fname" id="firstName" value="${presentDoctor.fname}" />
                <form:errors path="doctor.fname" />
            </div>
            <div class="form-group">
                <label for="lastName">Last Name:</label>
                <form:input path="doctor.lname" id="lastName" value="${presentDoctor.lname}" />
                <form:errors path="doctor.lname" />
            </div>
            <div class="form-group">
                <label for="department">Department:</label>
                <form:input path="doctor.department"  id="department" value="${presentDoctor.department}" />
                <form:errors path="doctor.department" />
            </div>
            <div class="form-group">
                <label for="education">Education:</label>
        
                <form:input path="doctor.education" id="education" value="${presentDoctor.education}" />
                <form:errors path="doctor.education" />
            </div>
            <div class="form-group">
                <label for="experience">Experience:</label>
        
                <form:input path="doctor.experience"  id="experience" value="${presentDoctor.experience}" />
                <form:errors path="doctor.experience" />
            </div>
            <input type="submit" value="Update" class="link-btn" /><br/>
        </form:form>
        <a href="/Doctor-Home" class="link-btn">Go To HomePage</a>
         <a href="<c:url value='/logout'/>" class="link-btn logout-btn">Logout</a>
    </div>
</body>
</html>