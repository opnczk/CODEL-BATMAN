<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Research Contact</title>
</head>
<body>
	<form method="post" action="AddContactServlet" >
		<table>
			<tr>
				<td>
					Email
					<input type="text" name="email"/>
				</td>
			</tr>
			<tr>
				<td>
					<button type = "submit">Rechercher</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>