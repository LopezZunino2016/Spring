<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page session="false" %>
<html>
<head>
	<title>Mis Compras</title>
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
	          	type="button" class="btn btn-danger btn-lg">
	          	<i class="fa fa-reply-all" aria-hidden="true"></i> Volver </button></a>
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
	
		<h1>Mis Compras</h1>
		<hr>
		<table class="table table">
			<thead>
				<tr>
							<th>Producto</th>
							<th>Fecha</th>
							<th>Cantidad</th>
							<th></th>
				</tr>
			</thead>
			<c:forEach items="${listaCompra }" var="c">
				<tbody>
					<tr>
						<td><p>${c.articulo.nombre }</p></td>
						<td><p>${c.fecha }</p></td>
						<td><p>${c.cantidad }</p></td>
						<td>
							<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#borrarCompra${c.idcompra}">
								<i class="fa fa-times" aria-hidden="true" data-toggle="modal"></i> Borrar
							</button> 
							
							<div class="modal fade" id="borrarCompra${c.idcompra}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
								<div class="modal-dialog" role="document">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title" id="exampleModalLabel">Borrar
												Articulo</h5>
										</div>
										<div class="modal-body">
											¿Desea eliminar el articulo ${l.nombre } ?
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary"	data-dismiss="modal">No</button>
											<button type="button" class="btn btn-danger" onclick="location.href='<c:url value="/borrarCompra?idCompra=${c.idcompra }"/>'">Sí</button>
										</div>
									</div>
								</div>
							</div>
							
						</td>
					</tr>
				</tbody>
			</c:forEach>
		</table>
		
	</div>
	<script type="text/javascript" src='<c:url value="/resources/js/jquery-3.2.1.slim.min.js" />'></script>
	<script type="text/javascript" src='<c:url value="/resources/js/popper.min.js" />'></script>
	<script type="text/javascript" src='<c:url value="/resources/js/bootstrap.min.js" />'></script>
</body>
</html>
