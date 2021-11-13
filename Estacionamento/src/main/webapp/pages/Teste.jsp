<%@page import="br.com.estacionamento.factory.ConexaoMySQL"%>
<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		out.println(ConexaoMySQL.statusConection());
	%>

	<%
		ConexaoMySQL.getConnection();
		out.println(ConexaoMySQL.statusConection());
	%>
</body>
</html>