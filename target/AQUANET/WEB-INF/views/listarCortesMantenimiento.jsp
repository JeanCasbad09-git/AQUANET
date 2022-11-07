<%@page import="entities.CortesMantenimiento"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html xmlns:th="http://thymelesf.org">
<head>
<meta charset="ISO-8859-1">
<title>Listado de cortes por mantenimiento</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">

<script defer="defer" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body style="background-color: #f0f8ff;" >
        <div class="container">
            <br>
        <h1 class="text-center - text-success">LISTADO DE CORTES POR MANTENIMIENTO</h1>
        
        <br>
        <form class="form-inline" method="GET" >
        	<div style="display: flex; gap: 1rem;">
                <label class="col-sm-2  col-form-label ">Buscar :</label>             
            </div>
            <div>
            	<input type="text" name="name" class="form-control" id="name" value="${name}" placeholder="Digite el valor a buscar">           	
            </div>
            <br>
            <div>
            	<input type="submit" class="btn btn-outline-warning" value="Buscar">
            	&nbsp;&nbsp;
            	<a type="button" id="btnli" class="btn btn-outline-warning" href="/AQUANET/cortes/listado">LIMPIAR</a>
            </div>
        </form>
        <br>
<a class="btn btn-outline-info" href="/AQUANET/cortes/crear">REGISTRAR</a>
<br>
        <br>
        <div>
            <table class="table">
                <thead >
                <tr class="table-info">
                    <th scope="col">ID</th>
                    <th scope="col">DISTRITO</th>
                    <th scope="col">PROVINCIA</th>
                    <th scope="col">DEPARTAMENTO</th>
                    <th scope="col">COMENTARIO</th>
                    <th scope="col">FECHA CORTE</th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <%
                List<CortesMantenimiento> lista=(List<CortesMantenimiento>)request.getAttribute("cortes");
                for(CortesMantenimiento cor:lista){ %>
                <tr  class="table-warning">
                    <td><%=cor.getIN_ID_CORTXMAN()%></td>
                    <td><%=cor.getVC_DISTRITO() %></td>
                    <td><%=cor.getVC_PROVINCIA() %></td>
                    <td><%=cor.getVC_DEPARTAMENTO() %></td>
                    <td><%=cor.getVC_COMENTARIO() %></td>
                    <td><%=cor.getDT_FECHA() %></td>
                    <td ><a class="btn btn-danger btn-sm" value="ELIMINAR" id="eliminar" onclick=<%="eliminar("+cor.getIN_ID_CORTXMAN()+")"%> style='color:white; font-weight: bold;'>ELIMINAR</a></td>
                    <td ><a class="btn btn-warning btn-sm" value="ACTUALIZAR" href=<%="/AQUANET/cortes/editar/"+cor.getIN_ID_CORTXMAN() +"" %> style='color:white; font-weight: bold;'>ACTUALIZAR</a></td>
                    </tr>
                <%} %>
                </tbody>
            </table>
            
        </div>

        </div>
</body>
<script type="text/javascript">
async function eliminar(pCodCor){
	const alertResponse = await Swal.fire({position: 'center',
		  title: "ELIMINAR CORTE?",
		  showDenyButton: true,
		  confirmButtonText: 'SI',
		  denyButtonText: 'NO',
	});
	
	if (alertResponse.isConfirmed) {
		const response = await fetch('/AQUANET/cortes/eliminar/' + pCodCor);
		if (response.ok) {
			const { isConfirmed } = await Swal.fire({
        		position: 'center',
        		icon: 'success',
      		  	title: "ELIMINACION EXITOSA",
      		  	showDenyButton: false,
      		  	confirmButtonText: 'OK'
      	
      		});
			if (isConfirmed) location.href="/AQUANET/cortes/listado?name=";
		} else {
			alertaErrorPersonalizada("ERROR");
		}
	}
}
</script>
</html>