<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Updte Contact</title>
</head>
<body>
	<form method="post" action="UpdateContactServlet" >
		<table>
			<tr>
				<td>
					Prenom
					<input type="text" name="first_name"/>
				</td>
			</tr>
			<tr>
				<td>
					Nom
					<input type="text" name="last_name"/>
				</td>
			</tr>
			<tr>
				<td>
					Pseudo
					<input type="text" name="nickname"/>
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
					<button type="submit">Update Contact</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>