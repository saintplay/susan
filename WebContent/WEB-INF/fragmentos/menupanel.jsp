<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:if test="${sessionScope.user==null}">
   <jsp:forward page="../../index.jsp"></jsp:forward>
</c:if>

<div class="header clearfix">
	<nav>
		<ul class="nav nav-pills pull-right">
			<li role="presentation"><a href="panel.jsp">Panel</a></li>
			<li role="presentation"><a href="categoria">Categorias</a></li>
			<li role="presentation"><a href="posts">Posts</a></li>
			
		
			  <li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown" role="button" aria-haspopup="true"
				aria-expanded="false">${sessionScope.user.username} <span
					class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="usuario?action=logout">Cerrar Sesion</a></li>
				</ul></li>
		</ul>
	</nav>
	<h3 class="text-muted">POSTEAPPE</h3>
</div>