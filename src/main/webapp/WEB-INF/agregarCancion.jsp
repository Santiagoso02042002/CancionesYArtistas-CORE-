<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title> Formulario para Agregar Cancion </title>
	</head>
	<body>
		<form:form action="/canciones/procesa/agregar" method="POST" modelAttribute="cancion">
			<div>
				<form:label path="titulo" for="titulo">
					Título:
				</form:label>
				<form:input path="titulo" type="text" id="titulo" name="titulo"/>
				<form:errors path="titulo"></form:errors>
			</div>
			<div>
				<form:label path="artista" for="artista">
					Artista:
				</form:label>
				<form:input path="artista" type="text" id="artista" name="artista"/>
				<form:errors path="artista"></form:errors>
			</div>
			<div>
				<form:label path="album" for="album">
					Álbum:
				</form:label>
				<form:input path="album" type="text" id="album" name="album"/>
				<form:errors path="album"></form:errors>
			</div>
			<div>
				<form:label path="genero" for="genero">
					Género:
				</form:label>
				<form:input path="genero" type="text" id="genero" name="genero"/>
				<form:errors path="genero"></form:errors>
			</div>
			<div>
				<form:label path="idioma" for="idioma">
					Idioma:
				</form:label>
				<form:input path="idioma" type="text" id="idioma" name="idioma"/>
				<form:errors path="idioma"></form:errors>
			</div>
			<div>
				<button> Agregar </button>
			</div>
		</form:form>
		<a href="/canciones"> Volver a la lista de canciones </a>
	</body>
</html>