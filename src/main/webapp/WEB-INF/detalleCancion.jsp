<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title> Detalle de la Canción </title>
	</head>
	<body>
		<h2> ${cancionActual.titulo} </h2>
		<p> Compuesta por ${cancionActual.artista.nombre} ${cancionActual.artista.apellido} en idioma ${cancionActual.idioma}, de género ${cancionActual.genero} perteneciente al álbum ${cancionActual.album}. </p>
		<div>
			<a href="/canciones"> Volver a canciones </a>
		</div>
		<form action="/canciones/formulario/editar/${cancionActual.id}" method="GET">
			<button type="submit"> Editar canción </button>
		</form>
		<form action="/canciones/eliminar/${cancionActual.id}" method="POST">
			<input type="hidden" name="_method" value="DELETE" />
			<button> Eliminar </button>
		</form>
	</body>
</html>