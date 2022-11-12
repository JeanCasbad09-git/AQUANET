<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="entities.Medidor"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">

<script defer="defer" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body style="background-color: #f0f8ff">
        <div class="container">
            <br>
        <h1 class="text-center - text-success">REGISTRO DE BOLETA</h1>
        <br>
        <form>
                    <table>
                        <%
                Medidor med =(Medidor)request.getAttribute("listMedidor");
                %>
                <tr>
                	<input id="ID_Medidor" type="hidden" value=<%=med.getVC_ID_MEDIDOR() %>>
                    <td><label class="col-sm-2  col-form-label ">NOMBRE DEL USUARIO :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
                    <td><input type="text" disabled="disabled" value=<%="'"+ med.getPersona().getVC_NOMBRE()+ "'"%> class="form-control" name="txtNomUsuario" id="txtNomUsuario" style="width: 600px;"></td>
                    
                </tr>
               <tr><td>&nbsp;</td></tr>
               <tr>
                    <td> <label class="col-sm-2  col-form-label " >CONSUMO TOTAL:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
                    <td><input type="text" disabled="disabled" value=<%="'"+ med.getDO_CONSUMO_ACTUAL()+ "'"%> class="form-control" name="txtConsumoTotal" id="txtConsumoTotal" style="width: 600px;"></td>
                    
                </tr>
                <tr><td>&nbsp;</td></tr>
                <tr>
                    <td>  <label class="col-sm-2  col-form-label " >INGRESE CONSUMO DEL MES:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
                    <td><input type="text" class="form-control" name="txtConsumoMes" id="txtConsumoMes" style="width: 600px;"></td>
                    
                </tr>
				
		
                    </table>
                    <br>
                    <div class="form-row">
            <center>
               <button type="button" class="btn btn-outline-success" onclick="grabar();">REGISTRAR</button>
               <button type="button" class="btn btn-outline-danger" onclick="location.href='http://localhost:8080/AQUANET/boleta/BuscarBoleta'">RETORNAR BUSCAR</button>
            </center>
            </div>
                </form>

              </div>
       
</body>
<script>
function grabar(){ 
	var consumo = $("#txtConsumoMes").val().trim();
	var id_medidor = $("#ID_Medidor").val();
   
    
    if(consumo != "" && consumo!=null){	
    		 $.ajax({
    		        type: 'POST',
    		          url: "RistroBoleta",
    		          data: {"id_medidor":id_medidor,"consumo":consumo},
    		          success: function(data){
    		        	  if(data == "REGISTRO EXITOSO"){
    		        		  alertaSuccesPersonalizada(data);
    		        		  limpiar();
    		          	  }
    		          	  else{
    		          		alertaErrorPersonalizada(data);
    		          	  }
    		          },
    		          error: function(){
    		        	  alertaErrorPersonalizada("ERROR");
    		          }
    		          }); 
    		
   }else{
    	alertaInfoPersonalizada("Ingrese el consumo de este mes");
    }
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