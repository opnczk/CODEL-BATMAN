<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Contact</title>
</head>
<body>
	<form method="post" action="AddContactServlet" >
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
			<table>
			<tr>
				<td>
					Street
					<input type="text" name="street"/>
				</td>
			</tr>
			<tr>
				<td>
					City
					<input type="text" name="city"/>
				</td>
			</tr>
			<tr>
				<td>
					Zip					
					<input type="text" name="zip"/>
				</td>
			</tr>
			<tr>
				<td>
					Country
					<input type="text" name="country"/>
				</td>
			</tr>
			<tr>
				<td>
					<button type="submit">Add Contact</button>
				</td>
			</tr>
				<tr>
				<td>
					PhoneKind
					<input type="text" name="phoneKind"/>
				</td>
			</tr>
			<tr>
				<td>
					PhoneNumber
					<input type="text" name="phoneNumber"/>
				</td>
			</tr>			
		</table>
		 <input id="autocomplete" placeholder="Enter a location" type="text" />
		 <div id="map"></div>
		<script src="./js/GMapsHelper.js"></script>
		<script>
				GMapsHelper.init({
					type : "address"
				});
		</script>
	</form>
</body>
</html>