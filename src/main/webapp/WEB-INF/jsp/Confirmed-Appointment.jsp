<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<h2>Your appointment is confirmed</h2>
<a href="/Patient-Home" class="link-btn">Go To Homepage</a>
         <a href="<c:url value='/logout'/>" class="link-btn logout-btn">Logout</a>
</body>
</html>