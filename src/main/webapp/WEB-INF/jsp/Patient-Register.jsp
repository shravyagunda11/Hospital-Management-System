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

        
        background-color:#d89292;
  
    }
      button{
    background-color: #4CAF50;
        color: white;
        padding: 14px 20px;
        margin: 8px 0;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        width: 400px;
        align:center;
    }
    form {
        background-color: #fff;
        padding: 20px;
        border-radius: 5px;
        box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        width: 400px;
        margin: 0 auto;
    }
    h2 {
        text-align: center;
    }
    input[type="text"], input[type="password"] {
        width: 100%;
        padding: 10px;
        margin: 5px 0;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
    }
    input[type="submit"] {
        background-color: #4CAF50;
        color: white;
        padding: 14px 20px;
        margin: 8px 0;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        width: 100%;
    }
    input[type="submit"]:hover {
        background-color: #45a049;
    }
    .error-message {
        color: red;
    }
    .container{
    text-align: center;  
    }
</style>
</head>
<body>
<h2>Welcome patient. Please register here</h2>
<form:form modelAttribute="userPatientWrapper" method="post" action="/patientRegistration">
            <form:errors path="*"></form:errors>
            User Name <form:input path="user.username" required="required"></form:input><form:errors path="user.username"></form:errors><br/>
             <c:if test="${not empty errorMessage}">
		        <div class="error-message">${errorMessage}</div>
		    </c:if><br/>
            Password <form:input path="user.password" required="required"></form:input><form:errors path="user.password"></form:errors><br/>
              <c:if test="${not empty errorMessagePwd}">
		        <div class="error-message">${errorMessagePwd}</div>
		    </c:if><br/>
            Email<form:input path="user.email"></form:input><form:errors path="user.email"></form:errors><br/>
             <c:if test="${not empty errorMessageEmail}">
		        <div class="error-message">${errorMessageEmail}</div>
		    </c:if><br/>
            First Name <form:input path="patient.fname" required="required"></form:input><form:errors path="patient.fname"></form:errors><br/>
            Last Name <form:input path="patient.lname" required="required"></form:input><form:errors path="patient.lname"></form:errors><br/>
            Date of Birth<form:input path="patient.dob" type="date" required="required"></form:input><form:errors path="patient.dob"></form:errors><br/>
            Gender <form:select path="patient.gender">
                	<form:option value="Male">Male</form:option>
                	<form:option value="Female">Female</form:option>
           		</form:select><form:errors path="patient.gender"></form:errors><br/>
           	
            <input type="submit" value="REGISTER" />
            
</form:form>
<div class="container"><button onclick="window.location.href='/login'">Login</button></div>

</body>
</html>