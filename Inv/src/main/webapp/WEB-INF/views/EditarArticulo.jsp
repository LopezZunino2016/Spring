<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>

<%@ page session="false" %>
<html>
<head>
	<title>Añadir Articulo</title>
	<link rel="stylesheet" href='<c:url value="/resources/css/bootstrap.min.css"/>'>
	<link rel="stylesheet" href='<c:url value="/resources/fonts/font-awesome.min.css"/>'>


<!-- Stylesheets ASIDE -->
</head>
<body>
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
		<a class="navbar-brand" href=""><i class="fa fa-home"
			aria-hidden="true"></i>Inventario</a>
		<a
         href="<c:url value="/AnadirArticulo"/>"><button
          type="button" class="btn btn-primary">Añadir Articulo</button></a>	
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
	<c:if test="${mensaje != ''}">
    	<div class="alert alert-warning alert-dismissable">
     		<button type="button" class="close" data-dismiss="alert"
      		aria-hidden="true">x</button>
     		<strong>Info!</strong> ${mensaje}
    	</div>
   </c:if>
	<c:url value="/editarArticulo" var="editarA"></c:url>
	<f:form class="navbar-form navbar-right " method="POST"
		action="${editarA }" id="formularioInicio" commandName="articulo">
		<f:input style="display: none;" path="idArticulos" type="number" name="idArticulos"  required="required"
												class="form-control" 
												placeholder="Nombre"/>
		<div class="form-group">
			<f:label path="nombre">Nombre:</f:label>
			<f:input type="text" placeholder="Nombre Articulo"
				class="form-control" path="nombre" name="nombre" id="nombre" required="required"/>
		</div>
		<div class="form-group">
			<f:label path="descripcion">Descripcion:</f:label>
			<f:input type="text" placeholder="Descripcion"
				class="form-control" path="descripcion" name="descripcion" id="descripcion" required="required"/>
		</div>
		<div class="form-group">
			<f:label path="cantidad">Cantidad:</f:label>
			<f:input type="number" placeholder="Cantidad"
				class="form-control" path="cantidad" name="cantidad" id="cantidad" required="required"/>
		</div>
		<div class="form-group">
			<f:label path="codigo">Codigo:</f:label>
			<f:input type="text" placeholder="Codigo"
				class="form-control" path="codigo" name="codigo" id="codigo" required="required"/>
		</div>
		<button type="submit" class="btn btn-primary">
			<i class="fa fa-sign-in"></i>Enviar
		</button>
		<a href="<c:url value="/Volver"/>"><button
          	type="button" class="btn btn-primary">Cancelar</button>
         </a>
	</f:form>


	<script type="text/javascript" src='<c:url value="/resources/js/jquery-3.2.1.slim.min.js" />'></script>
	<script type="text/javascript" src='<c:url value="/resources/js/popper.min.js" />'></script>
	<script type="text/javascript" src='<c:url value="/resources/js/bootstrap.min.js" />'></script>
</body>
</html>
