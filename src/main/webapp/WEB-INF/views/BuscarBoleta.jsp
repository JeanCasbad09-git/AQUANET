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
<%
String tipo =(String)request.getAttribute("Tipo");
if(tipo.trim().equals("ADM")){  
%>
	<%@ include file = "MenuAdmin.jsp" %>
	<%}else{ %>
	<%@ include file = "MenuCliente.jsp" %>
	<%} %>
<body style="background-color: #f0f8ff">
        <div class="container">
            <br>
        <h1 class="text-center - text-success">BUSCAR USUARIO BOLETA</h1>
        <br>
        <form>
            
            <div class="form-group row">
                <label class="col-sm-3  col-form-label ">BUSCAR X DNI  :</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" id="txtDNI" style="width: 300px;" maxlength="8">
            </div>
              <div class="col-sm-8">
            <button class="btn btn-dark" type="button" onclick="BuscarDNI();">Buscar</button>
               </div>
            </div>
            <br>
            <br>
            <div class="form-group row">
                <label class="col-sm-3  col-form-label ">BUSCAR X CODIGO MEDIDOR:</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="txtCodMed" style="width: 300px;" maxlength="8">
            </div>
              <div class="col-sm-8">
            <button class="btn btn-dark" type="button" onclick="BuscarXmedidor();">Buscar</button>
               </div>
            </div>
            
      
                </form>

              </div>
       
</body>
<script type="text/javascript">
function BuscarDNI(){ 
	var DNI = $("#txtDNI").val().trim();

	if(DNI != "" && DNI!=null){
		if(DNI.match("[0-9]{8,9}")){
			$.ajax({
 		        type: 'POST',
 		          url: "validarMedidorxDNI",
 		          data: {"DNI":DNI},
 		          success: function(data){
 		        	  if(data == "MEDIDOR ENCONTRADO"){
 		        		location.href='http://localhost:8080/AQUANET/boleta/RegistrarBoletaxDNI?parametros='+DNI;
 		          	  }
 		          	  else{
 		          		alertaRegistroMedidor();
 		          		
 		          	  }
 		          },
 		          error: function(){
 		        	  alertaErrorPersonalizada("ERROR");
 		          }
 		          }); 
		}else{
			alertaInfoPersonalizada("Formato de DNI no valido (8 digitos)");
		}
	}else{
		alertaInfoPersonalizada("Ingrese un DNI");
	}
    			  		
}

function BuscarXmedidor(){ 
	var COD = $("#txtCodMed").val().trim();

	if(COD != "" && COD!=null){
			$.ajax({
 		        type: 'POST',
 		          url: "validarMedidorXcod",
 		          data: {"COD":COD},
 		          success: function(data){
 		        	  if(data == "MEDIDOR ENCONTRADO"){
 		        		location.href='http://localhost:8080/AQUANET/boleta/RegistrarBoletaxCOD?parametros='+COD;
 		          	  }
 		          	  else{
 		          		alertaErrorPersonalizada(data)
 		          		
 		          	  }
 		          },
 		          error: function(){
 		        	  alertaErrorPersonalizada("ERROR");
 		          }
 		          }); 
		
	}else{
		alertaInfoPersonalizada("Ingresa el codigo del Medidor");
	}
    			  		
}

function alertaRegistroMedidor(){
	
	var DNI = $("#txtDNI").val().trim();
	
	
	Swal.fire({
		  title: 'Persona no cuenta con Medidor',
		  text:	 'Desea Registrar un Medidor a esta Persona?',
		  showDenyButton: true,
		  showCancelButton: true,
		  confirmButtonText: 'Si',
		  denyButtonText: 'No',
		}).then((result) => {
		  if (result.isConfirmed) {
			  Swal.fire({
				  title: 'Ingrese Codigo del Medidor',
				  input: 'text',
				  showCancelButton: true,
				  confirmButtonText: 'Registrar',
				  cancelButtonText: "Cancelar",
				  inputValidator: codMed => {
			            // Si el valor es válido, debes regresar undefined. Si no, una cadena
			            if (!codMed) {
			                return "Escriba el codigo del Medidor";
			            } else {
			                return undefined;
			            }
			        }
			    })
			    .then(resultado => {
			        if (resultado.value) {
			            let codMed = resultado.value;
			            $.ajax({
			                type: 'POST',
			                  url: "registrarMedidor",
			                  data: {"DNI":DNI,"CodMed":codMed},
			                  success: function(data){
			                      if(data == "MEDIDOR REGISTRADO"){
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
			        }
			    });
			 
		  } else if (result.isDenied) {
		    Swal.fire('CAMBIOS NO REALIZADOS', '', 'info')
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