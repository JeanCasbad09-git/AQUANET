<%@page import="entities.Reclamos"%>
<%@page import="entities.CortesMantenimiento"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html xmlns:th="http://thymelesf.org">
<head>
<meta charset="ISO-8859-1">
<title>Listado de Reclamos</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">

<script defer="defer" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<%
String tipo =(String)request.getAttribute("Tipo");
if(tipo.trim().equals("ADM")){  
%>
	<%@ include file = "MenuAdmin.jsp" %>
	<%}else{ %>
	<%@ include file = "MenuCliente.jsp" %>
	<%} %>
<body style="background-color: #f0f8ff;" >
        <div class="container">
            <br>
        <h1 class="text-center - text-success">LISTADO DE RECLAMOS</h1>
        
        <br>

        <br>
<a class="btn btn-outline-success" href="/AQUANET/reclamos/export">EXPORTAR A EXCEL</a>
<a class="btn btn-outline-info" href="/AQUANET/reclamos/crear">REGISTRAR</a>

<br>
        <br>
        <div>
            <table class="table">
                <thead >
                <tr class="table-info">
                    <th scope="col">ID</th>
                    <th scope="col">USUARIO</th>
                    <th scope="col">ESTADO</th>
                    <th scope="col">FECHA REGISTRO</th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <%
                List<Reclamos> lista=(List<Reclamos>)request.getAttribute("reclamos");
                for(Reclamos rec:lista){ %>
                <tr  class="table-warning">
                    <td><%=rec.getIN_ID_RECLAMO()%></td>
                    <td><%=rec.getUsuarioCreador() %></td>
                    <td><%=rec.getVC_ESTADO() %></td>
                    <td><%=rec.getDT_FECHA() %></td>
                     <td ><a class="btn btn-danger btn-sm" value="ELIMINAR" id="eliminar" onclick=<%="eliminar("+rec.getIN_ID_RECLAMO()+")"%> style='color:white; font-weight: bold;'>ELIMINAR</a></td>
                    <td ><a class="btn btn-warning btn-sm" value="ACTUALIZAR" href=<%="/AQUANET/reclamos/visualizar/"+rec.getIN_ID_RECLAMO() +"" %> style='color:white; font-weight: bold;'>VISUALIZAR RECLAMO</a></td>
                    </tr>
                <%} %>
                </tbody>
            </table>
            
        </div>

        </div>
</body>
<script type="text/javascript">
async function eliminar(pCodRec){
	const alertResponse = await Swal.fire({position: 'center',
		  title: "ELIMINAR RECLAMO?",
		  showDenyButton: true,
		  confirmButtonText: 'SI',
		  denyButtonText: 'NO',
	});
	
	if (alertResponse.isConfirmed) {
		const response = await fetch('/AQUANET/reclamos/eliminar/' + pCodRec);
		if (response.ok) {
			const { isConfirmed } = await Swal.fire({
        		position: 'center',
        		icon: 'success',
      		  	title: "ELIMINACION EXITOSA",
      		  	showDenyButton: false,
      		  	confirmButtonText: 'OK'
      	
      		});
			if (isConfirmed) location.href="/AQUANET/reclamos/listado";
		} else {
			alertaErrorPersonalizada("ERROR");
		}
	}
}

</script>
</html>