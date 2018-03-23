<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sform" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>
</head>
<body>
	<div align="center">
		<h2>Controller Working</h2>
<h2>>>> <a href="register">Add Here</a></h2>
		<table border="1">

			<tr>
				<th>Name</th>
				<th>job</th>
				<th>Action</th>
			</tr>

			<c:forEach var="entity" items="${list}">
				
				<tr>
					<td>${entity.name }</td>
					<td>${entity.job }</td>
					<td><a href="deleteUser?id=${entity.id }">Delete</a>
				</tr>

			</c:forEach>



		</table>


	</div>
</body>
</html>