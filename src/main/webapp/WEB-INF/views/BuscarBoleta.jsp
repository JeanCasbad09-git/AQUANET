<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="entities.Medidor"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Buscar Usuario Boleta</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">

<script defer="defer" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script></head>
<body style="background-color: #f0f8ff">
        <div class="container">
            <br>
        <h1 class="text-center - text-success">BUSCAR USUARIO BOLETA</h1>
        <br>
        <form>
            
            <div class="form-group row">
                <label class="col-sm-2  col-form-label ">INGRESE DNI  :</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="txtDNI" style="width: 300px;">
            </div>
              <div class="col-sm-10">
            <button class="btn btn-dark" type="button" onclick="BuscarDNI();">Buscar</button>
               </div>
            </div>
            <br>
      
                </form>

              </div>
       
</body>
<script type="text/javascript">
function BuscarDNI(){ 
	var DNI = $("#txtDNI").val().trim();

    		
    		 $.ajax({
    		        type: 'POST',
    		          url: "validarMedidor",
    		          data: {"DNI":DNI},
    		          success: function(data){
    		        	  if(data == "MEDIDOR ENCONTRADO"){
    		        		location.href='http://localhost:8080/AQUANET/boleta/RegistrarBoleta?parametros='+DNI;
    		          	  }
    		          	  else{
    		          		alertaRegistroMedidor();
    		          		
    		          	  }
    		          },
    		          error: function(){
    		        	  alertaErrorPersonalizada("ERROR");
    		          }
    		          }); 
    		
}

function alertaRegistroMedidor(){
	
	var DNI = $("#txtDNI").val().trim();
	
	
	Swal.fire({
		  title: 'Desea Registrar un Medidor a esta Persona?',
		  showDenyButton: true,
		  showCancelButton: true,
		  confirmButtonText: 'Si',
		  denyButtonText: 'No',
		}).then((result) => {
		  if (result.isConfirmed) {
			  $.ajax({
  		        type: 'POST',
  		          url: "registrarMedidor",
  		          data: {"DNI":DNI},
  		          success: function(data){
  		        	  if(data == "MEDIDOR ENCONTRADO"){
  		        		alertaSuccesPersonalizada(data);
  		          	  }
  		          	  else{
  		          		alertaErrorPersonalizada(data);
  		          		
  		          	  }
  		          },
  		          error: function(){
  		        	  alertaErrorPersonalizada("ERROR");
  		          }
  		          }); 
		  } else if (result.isDenied) {
		    Swal.fire('Changes are not saved', '', 'info')
		  }
		})
}

function alertaInfoPersonalizada(mensaje){
	Swal.fire({
  	  position: 'center',
  	  icon: 'info',
  	  title: mensaje,
  	  showConfirmButton: true,

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
}</script>
</html>