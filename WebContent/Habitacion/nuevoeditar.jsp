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
			<h2 class='form-title'><c:if test='${empty editar}'>Registrar Nueva </c:if>Habitacion<c:if test='${not empty editar}'> ${i.id}</c:if></h2>
			<c:choose>
				<c:when test='${empty editar}'>
					<input type='hidden' id='action' name='action' value='guardar'>
				</c:when>
				<c:otherwise>
					<input type='hidden' id='action' name='action' value='actualizarporid'>
					<input type='hidden' id='hotel_id' name='hotel_id' value='${i.hotel_id} }'>
				</c:otherwise>
			</c:choose>
			<div class="form-group">
				<input class="form-group" type='text' name='nombre' placeholder='Nombre' <c:if test='${not empty editar}'>value="${i.nombre}"</c:if> required/>
			</div>
			<div class="form-group">
				<input class="form-group" type='number' name='piso' placeholder='Piso' <c:if test='${not empty editar}'>value="${i.piso}"</c:if>/>
			</div>
			<div class="form-group">
				<select name='tipo' class="form-control">
					<option value='1' <c:if test='${not empty editar && i.tipo == 1}'>selected</c:if>>Simple</option>
					<option value='2' <c:if test='${not empty editar && i.tipo == 2}'>selected</c:if>>Individual</option>
					<option value='3' <c:if test='${not empty editar && i.tipo == 3}'>selected</c:if>>Individual Doble</option>
					<option value='4' <c:if test='${not empty editar && i.tipo == 4}'>selected</c:if>>Doble</option>
					<option value='5' <c:if test='${not empty editar && i.tipo == 5}'>selected</c:if>>Triple</option>
					<option value='6' <c:if test='${not empty editar && i.tipo == 6}'>selected</c:if>>Suite</option>
				</select>
			</div>
			<div class="form-group">
				<input class="form-group" type='text' name='descripcion' placeholder='Descripcion' <c:if test='${not empty editar}'>value="${i.descripcion}"</c:if>/>
			</div>
			<div class="form-group">
				<select name='tipo' class="form-control">
		    	<c:forEach var="hotel" items="${hoteles}">
					<option value='${hotel.id}' <c:if test='${not empty editar && i.hotel_id == hotel.id}'>selected</c:if>>${hotel.nombre}</option>
		    	</c:forEach>
		    	</select>
		    </div>
			<div class="form-group">
				<input class="form-group" type='number' step="0.1" name='precio' placeholder='Precio' <c:if test='${not empty editar}'>value="${i.precio}"</c:if>/>
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