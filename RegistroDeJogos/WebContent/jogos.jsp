<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="entidades.Jogo"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registro de jogos</title>
<link rel="stylesheet" href="./resources/css/bootstrap.min.css" />
<script src="./resources/js/bootstrap.min.js"></script>
</head>
<style>
body {
	background-color: #FFF0F5;
}

.container {
	margin: auto;
	width: 500px;
	height: 150px;
	background: #FFC0CB;
	border: 2px solid #FF0000;
	padding: 18px;
}

.titulo {
	margin: auto;
	width: 100%;
	height: 75px;
	background: #FFFFFF;
	border: 5px #FF0000;
	padding: 10px;
	color: #FF0000;
	font-family: verdana;
}

.subtitulo {
	margin: auto;
	width: 320px;
	background: FF0000;
	border: 3px #FF0000;
	padding: 10px;
	color: #FF0000;
	font-family: verdana;
}

.alert {
	font-family: verdana;
	margin: auto;
	color: #FFFFFF;
	font-weight: bold;
	padding: 10px;
	background: #3CB371;
}
</style>
<body>
	<div class="titulo">
		<h1>Sistema de gestão</h1>
		<hr noshade></hr>
	</div>
	<div class="subtitulo">
		<h2>Registro de jogos</h2>
	</div>
	<%
		String msg = (String) session.getAttribute("MESSAGE");
		Jogo jogoatual = ((Jogo) getServletContext().getAttribute("JogoAtual"));
		if (msg != null) {
	%>
	<div class="alert alert-danger">
		<%=msg%>
	</div>
	<%
		session.setAttribute("MESSAGE", null);

		}
	%>
	<div class="container">
		<form action="./JogosController">
			<div class="row">
				<div class="col-sm">
					<label for="jogoNome">Nome:</label>
				</div>
				<div class="col-sm">
					<input type="text" id="jogoNome" name="txtNome" />
				</div>
				<div class="col-sm">
					<button type="submit" class="btn btn-danger" name="cmd"
						value="Pesquisar">Pesquisar</button>
				</div>
			</div>
			<div class="row">
				<div class="col-sm">
					<label for="jogoDificuldade">Dificuldade:</label>
				</div>
				<div class="col-sm">
					<select id="jogoDificuldade" name="txtDificuldade">
						<option value="Fácil">Fácil</option>
						<option value="Normal">Normal</option>
						<option value="Difícil">Difícil</option>
						<option value="Muito difícil">Muito difícil</option>
						<option value="Impossível">Impossível</option>
					</select>
					<script>
					var s = "<%=jogoatual%>";
						var sel = document.getElementById("sel");
						for (var i = 0; i < sel.options.length; i++) {
							var optAtual = sel.options[i];
							if (optAtual.value == s) {
								optAtual.selected = "selected";
							}
							console.log(optAtual);
						}
					</script>
				</div>
				<div class="col-sm"></div>
			</div>
			<div class="row">
				<div class="col-sm">
					<button class="btn btn-danger" type="submit" name="cmd"
						value="Registrar">Registrar</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>