<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.List, 
    com.sar2016.entities.Contact, 
    com.sar2016.entities.Address, 
    com.sar2016.entities.ContactGroup, 
    com.sar2016.entities.Enterprise, 
    com.sar2016.entities.User, 
    com.sar2016.entities.PhoneNumber, 
    com.sar2016.dao.ContactDAO;"
    %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%List<Object> results = (List<Object>)request.getAttribute("result"); %>
	<% for(int i =0; i < results.size(); i++){%>
		<div>
		<%
			out.println(((Contact)results.get(i)).getEmail());
		%>
		</div>
	<%}%>
</body>
</html>