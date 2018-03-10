<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>

<%@ page session="false" %>
<html>
<head>
	<title>Login</title>
	<link rel="stylesheet" href='<c:url value="/resources/css/bootstrap.min.css"/>'>
	<link rel="stylesheet" href='<c:url value="/resources/fonts/font-awesome.min.css"/>'>
	<link rel="stylesheet" href='<c:url value="/resources/css/Prueba.css"/>'>
	
	

<!-- Stylesheets ASIDE -->
</head>
<body>
	<div class="container">
		<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
			<a class="navbar-brand" href=""><i class="fa fa-home"
				aria-hidden="true"></i>Inventario</a>
			<a class="divider"></a>
			<a href="<c:url value="/Volver"/>"><button
	          	type="button" class="btn btn-danger btn-lg"> </li>Volver </button></a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarExample" aria-controls="navbarExample"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
		</nav>
		<br>
		<br>
		<br>
		<!-- Prueba columnas -->
	
		<div class="row">
			<div class="col-xs-12 col-md-8">
	  			<h1>Editar Mi Usuario</h1>
				<c:if test="${mensaje != ''}">
			    	<div class="alert alert-warning alert-dismissable">
			     		<button type="button" class="close" data-dismiss="alert"
			      		aria-hidden="true">x</button>
			     		<strong>Info!</strong> ${mensaje}
			    	</div>
			  	 </c:if>
				<br>
				<c:url value="/EditarUsuario" var="editarU"></c:url>
				<f:form class="navbar-form navbar-right " method="POST"
					action="${editarU }" id="formularioInicio" commandName="usuario">
					<f:input style="display: none;" path="idUsuario" type="number" name="idArticulos"  required="required" class="form-control"/>
												
					<div class="form-group">
						<f:label path="nombre">Nombre:</f:label>
						<f:input type="text" placeholder="Nombre Usuario"
							class="form-control" path="nombre" name="nombre" id="nombre" required="required"/>
					</div>
					<div class="form-group">
						<f:label path="apellidos">Apellidos:</f:label>
						<f:input type="text" placeholder="Apellidos"
							class="form-control" path="apellidos" name="apellidos" id="apellidos" required="required"/>
					</div>
					<div class="form-group">
						<f:label path="alias">Usuario:</f:label>
						<f:input type="text" placeholder="Usuario"
							class="form-control" path="alias" name="alias" id="alias" required="required"/>
					</div>
					
					<button type="submit" class="btn btn-primary">
						<i class="fa fa-sign-in"></i>Editar
					</button>
					<a href="<c:url value="/Volver"/>"><button
	          	type="button" class="btn btn-danger">Cancelar </button></a>
				</f:form>
	  		</div>
  		</div>
	</div>
	<script type="text/javascript" src='<c:url value="/resources/js/jquery-3.2.1.slim.min.js" />'></script>
	<script type="text/javascript" src='<c:url value="/resources/js/popper.min.js" />'></script>
	<script type="text/javascript" src='<c:url value="/resources/js/bootstrap.min.js" />'></script>
</body>
</html>
