<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Doctor List</title>
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
   body {
        
        background-color: #d89292;
    }   
</style>
</head>
</head>
<body>
    <h2>Doctor List</h2>
    <form action="/filter-doctors" method="get">
        
        <label for="experience">Experience:</label>
        <input type="number" id="experience" name="experience">
        
        <label for="department">Department:</label>
        <select id="department" name="department">
            <option value="">Select Department</option>
            
            <c:forEach var="department" items="${departments}">
                <option value="${department}">${department}</option>
            </c:forEach>
        </select>
        
        <button type="submit">Search</button>
    </form>
    
    <table border="1">
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Experience</th>
            <th>Department</th>
            <th>Actions</th> 
        </tr>
       
        <c:forEach var="doctor"  items="${doclist}" varStatus="rowCounter">
        <c:if test="${rowCounter.index < 5}">
            <tr>
                <td>${doctor.fname}</td>
                <td>${doctor.lname}</td>
                <td>${doctor.experience} years</td>
                <td>${doctor.department}</td>
                <td>
                    <form:form action="/book-appointment" method="get">
                        <input type="hidden" name="doctorId" value="${doctor.doctorId}">
                        <button type="submit">Book Appointment</button>
                    </form:form>
                </td>
            </tr>
            </c:if>
        </c:forEach>
       
    </table>
    <br/>
   <a href="/Patient-Home" class="link-btn">Go To Homepage</a>
         <a href="<c:url value='/logout'/>" class="link-btn logout-btn">Logout</a>
</body>
</html>
