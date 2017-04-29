<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${sessionScope.cliente==null}">
   <jsp:forward page="../../index.jsp"></jsp:forward>
</c:if>

<div class="header clearfix">
	<nav>
		<ul class="nav nav-pills pull-right">
			<li role="presentation"><a href="panel.jsp">Inicio</a></li>
			<li role="presentation"><a href="hoteles">Hoteles</a></li>
			
		
			  <li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown" role="button" aria-haspopup="true"
				aria-expanded="false">${sessionScope.cliente.getUsuario()} <span
					class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="cliente?action=logout">Cerrar Sesion</a></li>
				</ul></li>
		</ul>
	</nav>
	<h3 class="text-muted">Susan</h3>
</div>