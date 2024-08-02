<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
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

</head>
<body>
<h2>Your profile is deleted. Please login or register again</h2>

         <a href="<c:url value='/login'/>" class="link-btn logout-btn">Login </a>
         </body>
         </html>