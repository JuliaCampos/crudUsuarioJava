
<%@ page
	import="com.crudJspJava.dao.UsuarioDao, com.crudJspJava.bean.Usuario, java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<!-- na viewMovimentacao.jsp ter� a lista de ve�culos com a op��o de entrada, edi��o e sa�da de veiculos -->
	<form action="login.jsp" method="post">
		Usuario: <br /> <input type="text" name="usuario" /><br /> 
		Senha: <br />
		<input type="password" name="senha" /><br /> <input type="submit"
			name="Logar" /><br />
	</form>

	<%
	//garante que os campos usuario e senha s�o preenchidos
	String usuario = request.getParameter("usuario");
	String senha = request.getParameter("senha");

	//valida��o dos campos usuario e senha
	if (usuario != null && senha != null && !usuario.isEmpty() && !senha.isEmpty()) {
		session.setAttribute("usuario", usuario);
		//redireciona para a p�gina de movimenta��o
		response.sendRedirect("viewMovimentacao.jsp");
	}
	%>
</body>
</html>