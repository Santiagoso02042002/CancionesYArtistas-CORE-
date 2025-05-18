<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title> Detalle del Artista </title>
	</head>
	<body>
		<h2> ${artistaActual.nombre} ${artistaActual.apellido} </h2>
		<p> ${artistaActual.biografia} </p>
		<h3> Canciones </h3>
		<ul>
			<c:forEach var="cancion" items="${artistaActual.canciones}">
				<li> ${cancion.titulo} </li>
			</c:forEach>
		</ul>
		<div>
			<a href="/artistas"> Volver a lista de Artistas </a>
		</div>
	</body>
</html>