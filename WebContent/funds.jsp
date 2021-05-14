<%@page import="com.Fund"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Fund Management</title>
		<link rel="stylesheet" href="Views/bootstrap.min.css">
		<script src="Components/jquery-3.2.1.min.js"></script>
		<script src="Components/funds.js"></script>
	</head>
	<body> 
		<div class="container">
			<form id="formFund" name="formFund">
			<div class="row">
				<div class="col-12">
					<h1>Fund Management </h1>
				</div>
				<div class="col-6">
					 Fund code: 
					 <input id="fundCode" name="fundCode" type="text" 
					 class="form-control form-control-sm">
					 <br> Project name: 
					 <input id="fundName" name="projectName" type="text" 
					 class="form-control form-control-sm">
			 	</div>
				<div class="col-6">
					 Fund Amount: 
					 <input id="fundAmount" name="fundAmount" type="text" 
					 class="form-control form-control-sm">
					 <br> Fund description: 
					 <input id="fundDesc" name="fundDesc" type="text" 
					 class="form-control form-control-sm">
				</div>
				<div class="col-sm-12 text-right pt-4">
					 <input id="btnSave" name="btnSave" type="button" value="Save" 
					 class="btn btn-primary">
					 <input type="hidden" id="hidFundIDSave" 
					 name="hidFundIDSave" value="">
				</div>
			</div>
			</form>
			<div class="row">
				<div class="col-12">
					<br>
					<div id="alertSuccess" class="alert alert-success"></div>
					<div id="alertError" class="alert alert-danger"></div>
					<br>
				</div>
				<div class="col-12">
						<div id="divfundsGrid">
						 <%
						 Fund itemObj = new Fund(); 
						 out.print(itemObj.readFunds()); 
						 %>
						</div>
				</div>
			</div>
		</div> 
	</body>
</html>