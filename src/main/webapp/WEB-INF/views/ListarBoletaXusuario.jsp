<%@page import="java.util.ArrayList"%>
<%@page import="entities.Boleta"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">

<script defer="defer" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<title>LISTADO DE BOLETAS</title>
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
        <h1 class="text-center - text-success"">LISTADO DE BOLETAS PENDIENTES</h1>
        
        <br>
        <div>
            <table class="table">
                <tr class="table-info">
                    <th scope="col">CODIGO MEDIDOR</th>
                    <th scope="col">CONSUMO DEL MES</th>
                    <th scope="col">CONSUMO TOTAL</th>
                    <th scope="col">MONTO A PAGAR</th>
                    <th scope="col">FECHA DE VENCIMIENTO</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <%
                List<Boleta> lista=(List<Boleta>)request.getAttribute("listBolUsuario");
                for(Boleta bol:lista){ %>
                <tr  class="table-warning">
                    <td><%=bol.getMedidor().getVC_ID_MEDIDOR() %></td>
                    <td><%=bol.getDO_CONSUMO() %></td>
                     <td><%=bol.getMedidor().getDO_CONSUMO_ACTUAL() %></td>
                    <td><%=bol.getDO_MONTO()%></td>
                     <td><%=bol.getDT_FECHAVENC() %></td>

             
                    <td><input type="button" class="btn btn-danger btn-sm" value="PAGAR" onclick=<%="eliminar('"+bol.getIN_ID_BOLETA() +"');" %> style='color:white; font-weight: bold;'></td>
                    </tr>
                <%} %>
                </tbody>
            </table>
            
       
        
</body>
<script type="text/javascript">

function eliminar(ID_BOLETA){
	
	Swal.fire({
		position: 'center',
		  title: "PAGAR BOLETA?",
		  showDenyButton: true,
		  confirmButtonText: 'SI',
		  denyButtonText: 'NO',
		}).then((result) => {
	   if (result.isConfirmed) {
        $.ajax({
            type: "POST",
            url: "pagarBoleta", 
            data: {"ID_BOLETA":ID_BOLETA},
            success: function(data){
            	Swal.fire({
            		position: 'center',
            		icon: 'success',
          		  title: "PAGO REALIZADO",
          		  showDenyButton: false,
          		  confirmButtonText: 'OK'
          	
          		}).then((result) => {
            	   if (result.isConfirmed) {
                    window.location.reload();
            	   }
            	   });
            },
            error: function(){
            	alertaErrorPersonalizada("ERROR");
            }
       });
    }
});
}

function alertaInfoPersonalizada(mensaje){
	Swal.fire({
  	  position: 'center',
  	  icon: 'info',
  	  title: mensaje,
  	  showConfirmButton: false,
  	  timer: 1500
  	})
}

function alertaSuccesPersonalizada(mensaje){
	Swal.fire({
  	  position: 'center',
  	  icon: 'success',
  	  title: mensaje,
  	  showConfirmButton: false,
  	  timer: 1500
  	})
}

function alertaErrorPersonalizada(mensaje){
	Swal.fire({
  	  position: 'center',
  	  icon: 'error',
  	  title: mensaje,
  	  showConfirmButton: false,
  	  timer: 1500
  	})
}

</script>
</html>