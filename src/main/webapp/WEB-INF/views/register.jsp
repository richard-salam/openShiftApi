<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register Page</title>
</head>
<body>
	<div align="center">

		<h3>Enter and Submit</h3>

		<form:form action="addUser" method="post" modelAttribute="entity">
			<table>
				<form:hidden path="id" />
				<tr>
					<td>Name:</td>
					<td><form:input path="name" /></td>
				</tr>
				<tr>
					<td>Job:</td>
					<td><form:input path="job" /></td>
				</tr>
				<tr>
					<td align="center"><input type="submit" value="Save" /></td>
				</tr>
			</table>
		</form:form>

	</div>
</body>
</html>