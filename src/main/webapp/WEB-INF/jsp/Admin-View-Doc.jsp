<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
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
<h2>List of doctors</h2>
<table border="1">
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Experience</th>
            <th>Department</th>
            <th>Education</th>
            <th>Actions</th> 
        </tr>
       
        <c:forEach var="doctor"  items="${docList}" >
        
            <tr>
                <td>${doctor.fname}</td>
                <td>${doctor.lname}</td>
                <td>${doctor.experience} years</td>
                <td>${doctor.department}</td>
                <td>${doctor.education}</td>
                <td>
                    <form:form action="/delete-doctor" method="get">
                        <input type="hidden" name="doctorId" value="${doctor.doctorId}">
                        <button type="submit">Delete Doctor</button>
                    </form:form>
                </td>
                
            </tr>
         
        </c:forEach>
       
    </table>
    <a href="<c:url value='/admin-home'/>" class="link-btn">Homepage</a>
    <a href="<c:url value='/logout'/>" class="link-btn logout-btn">Logout</a>
</body>
</html>