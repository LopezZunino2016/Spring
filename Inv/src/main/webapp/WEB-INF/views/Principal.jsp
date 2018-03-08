<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>

<%@ page session="false" %>
<html>
<head>
	<title>Principal</title>
	<link rel="stylesheet" href='<c:url value="/resources/css/bootstrap.min.css"/>'>
	<link rel="stylesheet" href='<c:url value="/resources/fonts/font-awesome.min.css"/>'>


<!-- Stylesheets ASIDE -->
</head>
<body>
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
		<a class="navbar-brand" href=""><i class="fa fa-home"
			aria-hidden="true"></i>Inventario</a>
		<a href="<c:url value="/addArticulo"/>"><button
          	type="button" class="btn btn-primary">Añadir Articulo</button>
         </a>	
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarExample" aria-controls="navbarExample"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		
	</nav>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
		<c:if test="${mensaje != ''}">
			<div class="alert alert-warning alert-dismissable">
					<button type="button" class="close" data-dismiss="alert"
							aria-hidden="true">x</button>
					<strong>Info!</strong> ${mensaje}
			</div>	
		</c:if>
		<table>
			<tr>
				<td>Nombre</td>
				<td>descripcion</td>
				<td>codigo</td>
				<td>cantidad</td>
				<td></td>
				<td></td>
			</tr>
			<c:forEach items="${listaArticulo}" var="l">
			<tr>
				<td>${l.nombre }</td>
				<td>${l.descripcion}</td>
				<td>${l.codigo}</td>
				<td>${l.cantidad}</td>
				<td>
					<a href="<c:url value="/EditarArticulo?idArticulo=${l.idArticulos }"/>"><button
          				type="button" class="btn btn-primary">Editar Articulo</button>
          			</a>	
          		</td>
				<td>
					<a href="<c:url value="/BorrarArticulo?idArticulo=${l.idArticulos }"/>"><button
          				type="button" class="btn btn-primary">Borrar Articulo</button>
          			</a>	
          		</td>
			</tr>
				</c:forEach>
			
		</table>


	<script type="text/javascript" src='<c:url value="/resources/js/jquery-3.2.1.slim.min.js" />'></script>
	<script type="text/javascript" src='<c:url value="/resources/js/popper.min.js" />'></script>
	<script type="text/javascript" src='<c:url value="/resources/js/bootstrap.min.js" />'></script>
</body>
</html>
