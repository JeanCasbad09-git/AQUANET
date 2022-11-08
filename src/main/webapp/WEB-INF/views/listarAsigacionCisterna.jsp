<%@page import="java.util.ArrayList"%>
<%@page import="entities.AsignacionCisterna"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html xmlns:th="http://thymelesf.org">
<head>
<meta charset="ISO-8859-1">
<title>LISTADO ASIGNACION DE CISTERNA</title>
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
        <h1 class="text-center - text-success"">LISTADO DE ASIGNACION DE CISTERNAS</h1>
        
        <br>
        

<br>
        <br>
        <div>
            <table class="table">
                <thead >
                <tr class="table-info">
                    <th scope="col">ID</th>
                    <th scope="col">PLACA CISTERNA</th>
                    <th scope="col">NOMBRE CHOFER</th>
                    <th scope="col">NOMBRE AGUATERO</th>
                    <th scope="col">FECHA ASIGNADA</th>
                    <th scope="col">PARADA 1</th>
                    <th scope="col">PARADA 2</th>
                    <th scope="col">PARADA 3</th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <%
                List<AsignacionCisterna> lista=(List<AsignacionCisterna>)request.getAttribute("listAsgCisterna");
                for(AsignacionCisterna cis:lista){ %>
                <tr  class="table-warning">
                    <td><%=cis.getIN_ID_ASIG_CISTERNA() %></td>
                    <td><%=cis.getCisterna().getVC_PLACA_COCHE() %></td>
                    <td><%=cis.getTrabajador().getVC_NOMBRES() %></td>
                     <td><%=cis.getTrabajador().getVC_NOMBRES() %></td>
                    <td><%=cis.getDT_FEC_ASIGNADO() %></td>
                     <td><%=cis.getVC_PARADA_1() %></td>
                     <td><%=cis.getVC_PARADA_2() %></td>
                     <td><%=cis.getVC_PARADA_3() %></td>
                 
                    <td><input type="button" class="btn btn-danger btn-sm" value="ELIMINAR" onclick=<%="eliminar('"+cis.getIN_ID_ASIG_CISTERNA() +"');" %> style='color:white; font-weight: bold;'></td>
                    <td><input type="button" class="btn btn-warning btn-sm" value="ACTUALIZAR"   onclick=<%="actualizar('"+cis.getIN_ID_ASIG_CISTERNA() +"');" %>  style='color:white; font-weight: bold'></td>
                    </tr>
                <%} %>
                </tbody>
            </table>
            
        </div>
		<button type="button" class="btn btn-dark" onclick="location.href='http://localhost:8080/AQUANET/cisterna//CrudCisterna'" style="color:white;">Nueva Asignacion</button>
		
        </div>
        
</body>
<script type="text/javascript">
async function eliminar(pCodCis){
	const alertResponse = await Swal.fire({position: 'center',
		  title: "ELIMINAR Asignacion?",
		  showDenyButton: true,
		  confirmButtonText: 'SI',
		  denyButtonText: 'NO',
	});
	
	if (alertResponse.isConfirmed) {
		const response = await fetch('/AQUANET/cisterna/eliminarCisterna/' + pCodCis);
		if (response.ok) {
			const { isConfirmed } = await Swal.fire({
        		position: 'center',
        		icon: 'success',
      		  	title: "ELIMINACION EXITOSA",
      		  	showDenyButton: false,
      		  	confirmButtonText: 'OK'
      	
      		});
			if (isConfirmed) location.href="/AQUANET/cisterna/ListaAsgCisterna";
		} else {
			alertaErrorPersonalizada("ERROR");
		}
	}
}
function actualizar(cod_cisterna){
    window.location.href = 'http://localhost:8080/AQUANET/cisterna/cisternaActualizar?parametros='+cod_cisterna;
}

</script>
</html>