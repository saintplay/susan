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
<link href='css/bootstrap.min.css' rel='stylesheet'>
<link href='css/susan-base.css' rel='stylesheet'>
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
        <div class='row'>
            <div class='col-sm-offset-4 col-sm-4' align='center'>
                <form  action='/cliente' method='post' class='aligned-form'>
				    <h2 class='form-title'>Registrarse</h2>
				    <h3 class='form-subtitle'>Complete todos los datos</h3>
				    <input type='hidden' id='action' name='action' value='add'>
				    <div class="form-group">
				    	<input type='text' name='nombres' placeholder='Nombres' required/>
				    </div>
				    <div class="form-group">
    					<input type='text' name='apellidos' placeholder='Apellidos' required/>
    				</div>
				    <div class="form-group">
				    	<input class="form-group" type='text' name='usuario' placeholder='Usuario' required/>
				    </div>
				    
				    <div class="form-group">
    					<input type='email' name='correo' placeholder='Correo' required/>
    				</div>
    				
				    <div class="form-group">
				    	<input class="form-group" type='password' name='contrasenia' placeholder='Contraseña' required/>
				    </div>
				    <div class="form-group">
				    	<input type='submit' class='btn btn-success' value='Registrar' />
				    </div>
				</form>
            </div>
        </div>

        <jsp:include page='WEB-INF/fragmentos/footer.jsp'></jsp:include>
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript" src="js/bootstrap.min.js"></script>
    </div>
</body>
</html>