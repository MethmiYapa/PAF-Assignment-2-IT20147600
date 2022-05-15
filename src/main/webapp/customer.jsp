<%@ page import="model.customer" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.js"></script>
<script src="Components/customers.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h1>Supplier Management </h1>
<form id="formItem" name="formItem">
 Supplier Name: 
 <br>
 <input id="name" name="name" type="text" 
 class="form-control form-control-sm">
 <br> Supplier Discription :<br> 
 <input id="discription" name="discription" type="text" 
 class="form-control form-control-sm">
 <br> Password: <br>
 <input id="password" name="password" type="text" 
 class="form-control form-control-sm">
<br>
<input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidItemIDSave" 
 name="hidItemIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
 <%
 customer customerObj = new customer(); 
	 out.print(customerObj.readCustomers()); 
 %>
</div>
</div> </div> </div> 
</body>
</html>
