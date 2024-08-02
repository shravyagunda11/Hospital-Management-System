<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Profile</title>
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
    </style>
</head>
<body>
    <div class="container">
        <h2>Update Profile</h2>
        <form:form modelAttribute="userPatientWrapper" method="post" action="/patientUpdate">
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
                <form:input path="patient.fname" id="firstName" value="${presentPatient.fname}" />
                <form:errors path="patient.fname" />
            </div>
            <div class="form-group">
                <label for="lastName">Last Name:</label>
                <form:input path="patient.lname" id="lastName" value="${presentPatient.lname}" />
                <form:errors path="patient.lname" />
            </div>
            <div class="form-group">
                <label for="dob">Date of Birth:</label>
                <form:input path="patient.dob" type="date" id="dob" value="${presentPatient.dob}" />
                <form:errors path="patient.dob" />
            </div>
            <div class="form-group">
                <label for="gender">Gender:</label>
                <form:select path="patient.gender" id="gender">
                    <form:option value="Male" >Male</form:option>
                    <form:option value="Female" >Female</form:option>
                </form:select>
                <form:errors path="patient.gender" cssClass="error" />
            </div>
            <input type="submit" value="Update" class="link-btn"/>
        </form:form>
        <br/>
        <a href="/Patient-Home" class="link-btn">Go To Homepage</a>
         <a href="<c:url value='/logout'/>" class="link-btn logout-btn">Logout</a>
         <a href="<c:url value='/deletePatient'/>" class="link-btn logout-btn">Delete Account</a>
    </div>
</body>
</html>
