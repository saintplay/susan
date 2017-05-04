<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html>
<html lang='es'>
<head>
<meta charset='utf-8'>
<meta http-equiv='X-UA-Compatible' content='IE=edge'>
<meta name='viewport' content='width=device-width, initial-scale=1'>
<meta name='description' content=''>
<meta name='author' content=''>
<link rel='icon' href='../../favicon.ico'>

<title>Susan</title>
<link href='../css/bootstrap.min.css' rel='stylesheet'>
<link href='../css/susan-base.css' rel='stylesheet'>
<!--[if lt IE 9]>
      <script src='https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js'></script>
      <script src='https://oss.maxcdn.com/respond/1.4.2/respond.min.js'></script>
    <![endif]-->
</head>

<body>

	<jsp:include page='WEB-INF/fragmentos/menu.jsp'></jsp:include>
	<div class='container'>
		<c:if test='${not empty message}'>
		    <div class='row'>
		    	<div class='col-sm-12'>
		            <div class='alert alert-success'>${message}</div>
		        </div>
		    </div>
		</c:if>
  		
		<form method='post' class='aligned-form'>
			<h2 class='form-title'><c:if test='${empty editar}'>Registrar Nuevo </c:if>Hotel<c:if test='${not empty editar}'> ${i.id}</c:if></h2>
			<input type='hidden' id='action' name='action' value='guardar'>
			<div class="form-group">
				<input class="form-group" type='text' name='nombre' placeholder='Nombre' <c:if test='${not empty editar}'>value="${i.nombre}"</c:if> required/>
			</div>
			<div class="form-group">
				<select name='calificacion' class="form-control">
					<option <c:if test='${not empty editar && i.calificacion == 5}'>selected</c:if>>5</option>
					<option <c:if test='${not empty editar && i.calificacion == 4}'>selected</c:if>>4</option>
					<option <c:if test='${not empty editar && i.calificacion == 3}'>selected</c:if>>3</option>
					<option <c:if test='${not empty editar && i.calificacion == 2}'>selected</c:if>>2</option>
					<option <c:if test='${not empty editar && i.calificacion == 1}'>selected</c:if>>1</option>
				</select>
			</div>
			<div class="form-group">
				<input class="form-group" type='text' name='direccion' placeholder='Direccion' <c:if test='${not empty editar}'>value="${i.direccion}"</c:if>/>
			</div>
			<div class="form-group">
				<input type='submit' class='btn btn-success' value='Guardar' />
			</div>
		</form>

	    <jsp:include page='../WEB-INF/fragmentos/footer.jsp'></jsp:include>
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript" src="js/bootstrap.min.js"></script>
	</div>
</body>