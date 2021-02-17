
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value='/entrada' var= "linkServletEntrada"/>
<c:url value='login' var= "linkLogin"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="${linkServletEntrada}" method="post">
		
		<c:if test="${ not empty erroLogin}">
			Erro de acesso: ${erroLogin}
		</c:if>
		
		<BR>
	
		Login : <input type="text" name="login"><br>
		Senha : <input type="password" name="senha">
		
	    <input type="hidden" name="acao" value="${linkLogin}">
	
		<input type="submit">
	</form>
</body>
</html>