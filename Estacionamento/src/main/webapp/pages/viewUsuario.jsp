<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Visualização de usuarios</title>
</head>
<body>
	<%@ page import="com.crudJspJava.dao.UsuarioDao, com.crudJspJava.bean.Usuario, java.util.*"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
	
	<h1>Listagem de usuario</h1>

	<%
		List<Usuario> list = UsuarioDao.getAllUsuarios();
		request.setAttribute("list", list);
		
		//validar as informações do login
		String usuario = (String)session.getAttribute("usuario");
		
		if(usuario == null){
			response.sendRedirect("login.jsp");
		}else{
			response.sendRedirect("viewMovimentacao.jsp");
		}
		
		
	%>
	
	<table>
		<tr>
			<th>ID:</th><th> Usuario:</th><th> Nome:</th><th>Senha:</th><th>Editar:</th><th>Excluir:</th>
		</tr>
		<c:forEach items="$(list)" var="usuario"> 
			<tr>
				<td>${usuario.getId()}</td>
				<td>${usuario.getUsuario()}</td>
				<td>${usuario.getNome()}</td>
				<td>${usuario.getSenha()}</td>
				<td><a href="editForm.jsp?idd=${usuario.getId()} }">Editar</a></td>
				<td><a href="deleteusuario.jsp?id=${usuario.getId()} }">Excluir</a></td>
			</tr>
		</c:forEach>
		<td colspan="2"><a href="deslogar.jsp"> Deslogar</a></td>
	</table>
	<br>
	<a href="addusuario.jsp">Adicionar novo usuario</a>
</body>
</html>