<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book Appointment</title>
<style>
    input[type="time"], input[type="date"] {
        padding: 5px;
        border: 1px solid #ccc;
        border-radius: 4px;
        margin-bottom: 10px;
        font-size: 16px;
    }
    body{
     background-color: #d89292;
     }
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
    
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
   
    var today = new Date().toISOString().split('T')[0];
    
    document.getElementById('apptDate').setAttribute('min', today);
</script>
</head>
<body>
<h2>Select time and date for your appointment</h2>
<form:form modelAttribute="appointment" method="post" action="/book-appointment">
    <form:errors path="*"></form:errors>

    Time <form:input path="apptTime" type="time" required="required"></form:input><form:errors path="apptTime"></form:errors><br/>

    Date <form:input path="apptDate" type="date" required="required" id="apptDate"></form:input><form:errors path="apptDate"></form:errors><br/>

    <input type="submit" class="link-btn" value="Confirm" />
</form:form>
<a href="/Patient-Home" class="link-btn">Go To Homepage</a>
         <a href="<c:url value='/logout'/>" class="link-btn logout-btn">Logout</a>


</body>
</html>
