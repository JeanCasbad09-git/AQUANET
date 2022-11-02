<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
<link rel="stylesheet" href="/css/Estilo.css">
<link
	href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css"
	rel="stylesheet">
<title>AQUANET</title>
<style>
<%@include file="/WEB-INF/views/css/Estilo.css"%>
</style>

</head>
<%
String tipo =(String)request.getAttribute("Tipo");
if(tipo.trim().equals("ADM")){  
%>
	<%@ include file = "MenuAdmin.jsp" %>
	<%}else{ %>
	<%@ include file = "MenuCliente.jsp" %>
	<%} %>
<body>

	<div class="head">
		<div class="logo">
			<a href="#"></a> <img src="https://i.ibb.co/vw2GC1m/logo.png" />
		</div>

		<nav class="navbar">
			<a href="#inicio">Inicio</a> <a href="#nosotros">Nosotros</a> <a
				href="#servicios">Servicios</a> <a href="#nuestrosclientes">Nuestros
				Clientes</a> <a href="#contacto">Contacto</a> <a href="#login">Acceder</a>
		</nav>

	</div>

	<section class="content inicio" id="inicio">
		<h2 class="title"></h2>
		<p>La empresa de transporte de agua AQUANET dedicada con más de 20
			años de experiencia en el rubro de abastecimiento de agua alquiler de
			cisternas, succión y servicios en general. Brindamos nuestros
			servicios a todo público en general, fabricas empresas en
			construcción, pesqueras, hoteles parques y jardines, pruebas de
			tanques elevados. Contamos con unidades implementadas para todo tipo
			de servicio al gusto del cliente con un personal calificado
			preparados para todo tipo de trabajo, cuentan con charla de
			seguridad, EPPS.</p>

		<div class="btn-home">
			<a href="#servicios" class="btn">Nuestros Servicios</a> <a
				href="#contacto" class="btn">Contáctese con Nosotros</a>
		</div>
	</section>

	<section class="content nosotros" id="nosotros">

		<h2 class="title">Nosotros</h2>
		<div class="divnosotros">
			<p>La empresa de transporte de agua potable dedicada con más de
				20 años de experiencia en el rubro de abastecimiento de agua potable
				y agua tratada, succión, servicios en general</p>
			<p>Brindamos nuestros servicios a todo público en general,
				fabricas empresas en construcción, pesqueras, hoteles parques y
				jardines, pruebas de tanques elevados</p>
			<p>Contamos con unidades implementadas para todo tipo de servicio
				al gusto del cliente con un personal calificado preparados para todo
				tipo de trabajo, cuentan con charla de seguridad, EPPS</p>
			<p>MISIÓN: Nuestra misión es proporcionar a nivel nacional el
				servicio de transporte de agua, alquiler de cisternas con altos
				estándares operativos.</p>
			<p>VISIÓN: Nuestra visión es consolidarnos como una de las
				empresa más importante del sector de agua a nivel nacional para ello
				debemos brindar un servicio de calidad que exceda ampliamente las
				expectativas de nuestros clientes</p>
			<p>CALIDAD: Nuestro deber es proporcionar una buena calidad de
				agua para nuestros clientes por eso contamos con pruebas de agua de
				un laboratorio para verificar que este en buen estado ya que
				nosotros nos debemos a nuestros clientes</p>
		</div>


	</section>

	<section class="content servicio" id="servicios">

		<h2 class="title">Servicios</h2>
		<ul class="lista-servicio" style="list-style-type: circle;">
			<li>Alquiler de camiones cisternas</li>
			<li>Transporte de agua</li>
			<li>Transporte de agua tratada</li>
			<li>Servicio de llenado de piscinas</li>
			<li>Transporte de abastecimiento en fabricas</li>
			<li>Servicio para obras</li>
			<li>Servicio de succión</li>
		</ul>
		<div class="btn-servicio">
			<a href="#" class="btn">Solicitar Servicios</a> <a href="#"
				class="btn">Solicitar Reclamo</a>
		</div>

	</section>

	<section class="content cliente" id="nuestrosclientes">


		<h2 class="title">Nuestros Clientes</h2>
		<p>Actualmente trabajamos con empresas prestigiosas como:</p>

		<ul class="lista-cliente" style="list-style-type: circle;">
			<li>Alicorp</li>
			<li>Ransa</li>
			<li>Olva Courier</li>
			<li>Good Year</li>
		</ul>
		<div class="slider">
			<ul>
				<li><img
					src="https://aguaclear.com/wp-content/uploads/2020/01/historia-logo-goodyear.jpg"width: 250px; height: 150px;>

				</li>
				<li><img
					src="https://www.alicorp.com.pe/static/images/share.png"width: 250px; height: 150px;>

				</li>
				<li><img
					src="https://www.america-retail.com/static//2022/01/grupo-ransa.jpg"width: 250px; height: 150px;>

				</li>
				<li><img
					src="https://www.olvacourier.com/wp-content/uploads/2017/06/logo_olva_post.jpg"width: 250px; height: 150px;>

				</li>
			</ul>
		</div>
	</section>

	<section class="content contacto" id="contacto">
		<h2 class="title">Contacto</h2>
		<p>Telf.: Lima: 974325000 / Sur: 982123554</p>
		<p>Email: info@aquanet.com / aquanet@hotmail.com</p>
		<p>Dirección: Av. Maquinarias 2484 Bellavista</p>
		<figure class="map">
			<img
				src="https://www.google.com/maps/d/thumbnail?mid=1xalFNTTa5Maqam7O_zKBrmJtWk4&hl=en_US"
				height="150px" width="250px" alt="mapa">
		</figure>
		<p class="final">©2019 - 2022 aquanet.com.pe</p>
	</section>

</body>
</html>