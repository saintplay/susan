<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Web</title>

<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">


<!-- Custom styles for this template -->
<link href="css/jumbotron-narrow.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

	<div class="container">
		<jsp:include page="WEB-INF/fragmentos/menu.jsp"></jsp:include>

		<div class="row marketing">
			<div class="col-xs-12">
				<form action="usuario" method="post"  class="form-horizontal">
				    <input type="hidden" id="action" name="action" value="add">
					<div class="form-group">
						<label class="control-label col-sm-2" for="nombre">Nombre:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="nombre" name="nombre"
								required placeholder="Ingrese su nombre">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="usuario">Usuario:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="usuario" name="usuario"
								required placeholder="Ingrese su usuario">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="pwd">Clave:</label>
						<div class="col-sm-10">
							<input type="Password" class="form-control" id="clave" name="clave"
								placeholder="Ingrese su clave">
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-default">Registrarse</button>
						</div>
					</div>
				</form>
			</div>
		</div>




		<jsp:include page="WEB-INF/fragmentos/footer.jsp"></jsp:include>

	</div>
	<!-- /container -->

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script>
		window.jQuery
				|| document.write('<script src="js/jquery.min.js"><\/script>')
	</script>
	<script src="js/bootstrap.min.js"></script>

</body>
</html>
