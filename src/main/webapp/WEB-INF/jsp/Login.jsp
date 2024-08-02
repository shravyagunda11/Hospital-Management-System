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
    body {
        font-family: Arial, sans-serif;
        background-color: #d89292;
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
        background-color: green;
        color: white;
        padding: 14px 20px;
        margin: 8px 0;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        width: 100%;
    }
    .error-message {
        color: red;
           text-align: center;
    }
    a {
        display: block;
        margin-top: 10px;
        text-align: center;
        text-decoration: none;
    }
</style>
</head>
<body>
<h2>Welcome to login page</h2>
  <c:if test="${not empty errorMessage}">
        <<div class="error-message">${errorMessage}</div>
    </c:if>
<form:form modelAttribute="user" method="post" action="/login">
	<form:errors path="*"></form:errors>
	User Name <form:input path="username" required="required"></form:input><form:errors path="username"></form:errors><br/>
	Password <form:input path="password" required="required"></form:input><form:errors path="password"></form:errors><br/>       
	<a href="<c:url value="/patientRegistration"/>">Register as Patient</a>
	<a href="<c:url value="/doctorRegistration"/>">Register as Doctor</a><br/>
    <input type="submit" value="LOGIN" />
            
</form:form>


</body>
</html>