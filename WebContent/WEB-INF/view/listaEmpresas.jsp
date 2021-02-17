<%@page import="br.com.alura.gerenciador.modelo.Empresa"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:url value='/entrada' var= "linkServletEntrada"/>
<c:url value='MostraEmpresa' var= "linkMostra"/>
<c:url value='RemoveEmpresa' var= "linkRemove"/>
<c:url value='NovaEmpresaForm' var= "linkNova"/>
<c:url value='logout' var= "linkLogou"/>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	Usuário logado: ${usuarioLogado.login }
	
	<c:import url="logout-parcial.jsp"></c:import>

	<BR>
	<BR>
	
	<c:if test="${ not empty nomeEmpresa}">
		Empresa ${nomeEmpresa} cadastrada com sucesso!
	</c:if>
	
	<BR>
	<BR>
	
	Lista de Empresas
	<ul>
		<c:forEach items="${empresas}" var="empresa">
			
			<li>
				${empresa.nome} <fmt:formatDate value="${empresa.dataAbertura}" pattern="dd/MM/yyyy"/> [
				<a href="${linkServletEntrada}?acao=${linkMostra }&id=${empresa.id}" > Edit </a> - 
				<a href="${linkServletEntrada}?acao=${linkRemove }&id=${empresa.id}" > Remove </a>]
			 	
			</li>
		</c:forEach>
	</ul>
	
	
	<a href="${linkServletEntrada}?acao=${linkNova }" > Cadastrar Nova </a>

</body>
</html>