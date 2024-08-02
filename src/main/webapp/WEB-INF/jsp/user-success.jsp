<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
button {
        background-color: green;
        color: white;
        padding: 14px 20px;
        margin: 8px 0;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        width: 100%;
    }
 h2, .container{
 text-align:center;
 }
   body {
        
        background-color: #d89292;
    }
</style>
</head>
<body>
<h2>You have successfully registered! Please login to access the system</h2>
<div class="container">
<button onclick="window.location.href='/login'">Login</button>
</div>


</body>
</html>