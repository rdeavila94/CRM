<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
<title>Test</title>
<!-- Reference style sheets -->
<link type="text/css" rel="stylesheet"
	href = "${pageContext.request.contextPath}/resources/css/style.css">
	
	
</head>

<body>

	<div id="wrapper" >
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
			
		</div>


	
	</div>


	<div id = "container">
	
		<div id="content">
		<!-- Add button here -->
		<input type="button" value="Add Customer" onclick = "window.location.href='showFormForAdd'; return false" class="add-button">
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
				
				<!-- Loop over and print out our customers -->
				<c:forEach var="tempCustomer" items="${customers}">
				
				<!-- construct a customer id with update link. This is mapping a variable, updateLink to the location of page. Also, 
				a param called customerId which is this iterations customers id -->
				<c:url var="updateLink" value="/customer/showFormForUpdate">
					<c:param name="customerId" value="${tempCustomer.id}"/>
				</c:url>
				<c:url var="deleteLink" value="/customer/delete"> 

					<c:param name="customerId" value="${tempCustomer.id}"/>
				</c:url>
					<tr>
					
						<td>${tempCustomer.firstName}</td>
						<td>${tempCustomer.lastName}</td>
						<td>${tempCustomer.email}</td>
						<td><a href = "${updateLink}">Update</a>|
						<a href="${deleteLink}"
						onClick="if (!(confirm('Are you sure you want to delete ${tempCustomer.firstName} ${tempCustomer.lastName }?'))) return false"> Delete</a></td>
					</tr>
					
				</c:forEach>
			</table>
		
		</div>
	
	</div>
	
</body>

</html>