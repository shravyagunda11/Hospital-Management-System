<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    h2 {
        text-align: center;
        margin-top: 50px;
    }
    .container {
        width: 50%;
        margin: 0 auto;
        padding: 20px;
        background-color: #fff;
        border-radius: 8px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }
    table {
        width: 100%;
        border-collapse: collapse;
    }
    th, td {
        padding: 8px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }
    th {
        background-color: #f2f2f2;
    }
    input[type="text"],
    input[type="number"],
    input[type="checkbox"],
    button[type="button"],
    button[type="submit"],
    .link-btn {
        padding: 10px 20px;
        font-size: 16px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        text-decoration: none;
        display: inline-block;
        margin: 10px;
    }
    input[type="text"],
    input[type="number"],
    input[type="checkbox"] {
        width: calc(100% - 20px);
        margin: 0;
    }
    .link-btn {
        background-color: #4CAF50;
        color: white;
    }
    .logout-btn {
        background-color: #f44336;
    }
    .link-btn:hover,
    .logout-btn:hover {
        background-color: #45a049;
    }
    
    </style>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
 <script>
        function addTabletRow() {
            const table = document.getElementById("tablet");
            const rowCount = table.rows.length;
            const row = table.insertRow(rowCount);
            const index = rowCount - 2;
            console.log("Current index:", index);
            const cell0 = row.insertCell(0);
            cell0.innerHTML ='<input type="text" name="tablets[' + index + '].tabletName">';
            const cell1 = row.insertCell(1);
            cell1.innerHTML ='<input type="number" name="tablets[' + index + '].days">';
            const cell2 = row.insertCell(2);
            cell2.innerHTML ='<input type="checkbox" name="tablets[' + index + '].breakfast">';
            const cell3 = row.insertCell(3);
            cell3.innerHTML ='<input type="checkbox" name="tablets[' + index + '].lunch">';
            const cell4 = row.insertCell(4);
            cell4.innerHTML ='<input type="checkbox" name="tablets[' + index + '].dinner">';
       
        }
    </script>
</head>
<body>
<div class="container">
<h2>Prescription Form</h2>
    <form action="/savePrescription" method="post" >
    <input type="hidden" name="apptId" value="${apptId}">
    <input type="hidden" name="patUserName" value="${patUserName}">
        <table id="tablet">
            <thead>
            <tr></tr>
                <tr>
                    <th>Tablet Name</th>
                    <th>Number of Days</th>
                    <th>Breakfast</th>
                    <th>Lunch</th>
                    <th>Dinner</th>
                </tr>
            </thead>
            <tbody>
            
                <tr>
                    <td><input type="text" name="tablets[0].tabletName" required></td>
                    <td><input type="number" name="tablets[0].days" required></td>
                    <td><input type="checkbox" name="tablets[0].breakfast"></td>
                    <td><input type="checkbox" name="tablets[0].lunch"></td>
                    <td><input type="checkbox" name="tablets[0].dinner"></td>
                </tr>
            </tbody>
        </table>
        <button type="button" onclick="addTabletRow()">Add Tablet</button>
        <button type="submit">Save Prescription</button>
    </form>
    <a href="/Doctor-Home" class="link-btn">Go To HomePage</a>
    <a href="<c:url value='/logout'/>" class="link-btn logout-btn">Logout</a>
</div>
</body>
</html>