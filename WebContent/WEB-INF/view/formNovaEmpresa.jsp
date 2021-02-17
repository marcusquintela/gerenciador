
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value='/entrada' var= "linkServletEntrada"/>
<c:url value='NovaEmpresa' var= "linkNova"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="${linkServletEntrada}" method="post">
		<c:import url="logout-parcial.jsp"></c:import>
	
		Nome: <input type="text" name="nome">
		Data Abertura: <input type="text" name="data">
	    <input type="hidden" name="acao" value="${linkNova}">
	    <input type="hidden" name="NomeEmpresa" value="${linkNova}">
	
		<input type="submit">
	</form>
</body>
</html>