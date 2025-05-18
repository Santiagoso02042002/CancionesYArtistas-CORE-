<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title> Formulario para Agregar Artista </title>
	</head>
	<body>
	<h2> Agregar Artista </h2>
		<form:form action="/artistas/procesa/agregar" method="POST" modelAttribute="artista">
			<div>
				<form:label path="nombre" for="nombre">
					Nombre:
				</form:label>
				<form:input path="nombre" type="text" id="nombre" name="nombre"/>
				<form:errors path="nombre"></form:errors>
			</div>
			<div>
				<form:label path="apellido" for="apellido">
					Apellido:
				</form:label>
				<form:input path="apellido" type="text" id="apellido" name="apellido"/>
				<form:errors path="apellido"></form:errors>
			</div>
			<div>
				<form:label path="biografia" for="biografia">
					Biografia:
				</form:label>
				<form:input path="biografia" type="text" id="biografia" name="biografia"/>
				<form:errors path="biografia"></form:errors>
			</div>
			<div>
				<button> Agregar </button>
			</div>
		</form:form>
		<a href="/artistas"> Volver a la lista de artistas </a>
	</body>
</html>