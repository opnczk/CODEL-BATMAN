<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Contact</title>
</head>
<body>
	<form method="post" action="AddEnterpriseServlet" >
		<table>
			<tr>
				<td>
					Nom
					<input type="text" name="nom"/>
				</td>
			</tr>
			<tr>
				<td>
					Mail
					<input type="email" name="email"/>
				</td>
			</tr>
			<tr>
				<td>
					Siret
					<input type="text" name="siret"/>
				</td>
			</tr>
			<tr>
				<td>
					<button type="submit">Add Contact</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>