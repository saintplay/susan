<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<nav class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#menu-opciones" aria-expanded="false">
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
            </button>
        	<a class="navbar-brand" href="/">
		    	<img alt="Brand" src="img/susan-nav.png">
		    </a>
        </div>
        <div class="collapse navbar-collapse" id="menu-opciones">
            <ul class="nav navbar-nav">
               	<li <c:if test="${pageContext.request.requestURI == '/hotel'}">class='active'</c:if>><a href="/hotel">Hoteles</a></li>
               	<li <c:if test="${pageContext.request.requestURI == '/habitacion'}">class='active'</c:if>><a href="/habitacion">Habitaciones</a></li>
                <li <c:if test="${pageContext.request.requestURI == '/about.jsp'}">class='active'</c:if>><a href="https://github.com/saintplay/susan">Acerca De</a></li>	
            </ul>
            <c:choose>
	            <c:when test="${sessionScope.cliente!=null}">
		            <ul class="nav navbar-nav navbar-right">
		                <li class="dropdown">
		                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${sessionScope.cliente.getNombres()}<span class="caret"></span></a>
		                    <ul class="dropdown-menu">
		                        <li><a href="#">Opciones</a></li>
		                        <li role="separator" class="divider"></li>
		                        <li><a href="/cliente?action=logout">Cerrar Sesión</a></li>
		                    </ul>
		                </li>
		            </ul>
	            </c:when>
	            <c:otherwise>
	            	<ul class="nav navbar-nav navbar-right">
	            		<li role="separator" class="divider"></li>
                        <li><a href="/login.jsp">Iniciar Sesión</a></li>
                        <li><a href="/nuevo_cliente.jsp">Registrarse</a></li>
		            </ul>
	            </c:otherwise>
            </c:choose>
        </div>
    </div>
</nav>