<%@page import="java.util.ArrayList"%>
<%@page import="entities.Usuario"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ACTUALIZAR USUARIO</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">

<script defer="defer" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script></head>
<body style="background-color: #f0f8ff">
	<div class="container">
                    <br>
                <h1 class="text-center - text-success">ACTUALIZAR USUARIO</h1>
                <br>
                <form>
                    <table>
                        <%
                Usuario usu=(Usuario)request.getAttribute("Usuario");
                %>
                <tr>
                	<input id="txtCodUsuario" type="hidden" value=<%=usu.getIN_ID_USUARIO() %>>
                	<input id="txtCodPersona" type="hidden" value=<%=usu.getIN_ID_PERSONA() %>>
                    <td><label class="col-sm-2  col-form-label ">Nombre(s):&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
                    <td><input type="text" value=<%="'"+ usu.getVC_NOMBRE() + "'"%> class="form-control" id="txtNombre" style="width: 600px;"></td>
                    
                </tr>
               <tr><td>&nbsp;</td></tr>
               <tr>
                    <td><label class="col-sm-2  col-form-label ">Usuario:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
                    <td><input type="text" value=<%="'"+ usu.getVC_USER() + "'"%> class="form-control" id="txtUsuario" style="width: 600px;"></td>
                    
                </tr>
                <tr><td>&nbsp;</td></tr>
                <tr>
                    <td><label class="col-sm-2  col-form-label ">DNI:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
                    <td><input type="text" value=<%="'"+ usu.getVC_DNI() + "'"%> class="form-control" id="txtDNI" style="width: 600px;"></td>
                    
                </tr>
				
				<tr><td>&nbsp;</td></tr>
                <tr>
                    <td><label class="col-sm-2  col-form-label ">Contraseña:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
                    <td><input type="text" value=<%="'"+ usu.getVC_PASSWORD() + "'"%> class="form-control" id="txtContrasenna" style="width: 600px;"></td>
                    
                </tr>
				
				<tr><td>&nbsp;</td></tr>
                <tr>
                    <td><label class="col-sm-2  col-form-label ">Direccion:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
                    <td><input type="text" value=<%="'"+ usu.getVC_DIRECCION() + "'"%> class="form-control" id="txtDireccion" style="width: 600px;"></td>
                    
                </tr>
                
                <tr><td>&nbsp;</td></tr>
                <tr>
                    <td><label class="col-sm-2  col-form-label ">Distrito:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
                    <td><input type="text" value=<%="'"+ usu.getVC_DISTRITO() + "'"%> class="form-control" id="txtDistrito" style="width: 600px;"></td>
                    
                </tr>
                
                <tr><td>&nbsp;</td></tr>
                <tr>
                    <td><label class="col-sm-2  col-form-label ">Provincia:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
                    <td><input type="text" value=<%="'"+ usu.getVC_PROVINCIA() + "'"%> class="form-control" id="txtProvincia" style="width: 600px;"></td>
                    
                </tr>
                
                <tr><td>&nbsp;</td></tr>
                <tr>
                    <td><label class="col-sm-2  col-form-label ">Departamento:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
                    <td><input type="text" value=<%="'"+ usu.getVC_DEPARTAMENTO() + "'"%> class="form-control" id="txtDepartamento" style="width: 600px;"></td>
                    
                </tr>
				
				
                    </table>
                    <br>
                    <div class="form-row">
            <center>
               <button type="button" class="btn btn-outline-success" onclick="grabar();">ACTUALIZAR</button>
               <button type="button" class="btn btn-outline-danger" onclick="location.href='http://localhost:8080/AQUANET/home/usuarioAct?parametros=va,va'">CANCELAR</button>
            </center>
            </div>
                </form>
        </div>
        
        <script type="text/javascript">
function grabar(){ 
	var nombre = $("#txtNombre").val().trim();
    var dni = $("#txtDNI").val().trim();
    var user = $("#txtUsuario").val().trim();
    var codUser = $("#txtCodUsuario").val().trim();
    var codPersona = $("#txtCodPersona").val().trim();
    var password = $("#txtContrasenna").val().trim();
    var direccion = $("#txtDireccion").val().trim();
    var distrito = $("#txtDistrito").val().trim();
    var provincia = $("#txtProvincia").val().trim();
    var departamento = $("#txtDepartamento").val().trim();
    
    if(nombre != "" && nombre!=null){
    	if(nombre.match("^[a-zA-Z ]+$")){
        if(dni != "" && dni!=null){
        	if(dni.match("[0-9]{8,9}")){
    if(user != "" && user!=null){
    	if(password != "" && password!=null){
    		if(direccion != "" && direccion!=null){
        		if(distrito != "" && distrito!=null){
        			if(provincia != "" && provincia!=null){
        				if(departamento != "" && departamento!=null){
					          $.ajax({
					        type: 'POST',
					          url: "actualizarUsuario",
					          data: {"nombre":nombre,"dni":dni,"user":user,"codUser":codUser,"codPersona":codPersona,"password":password,"direccion":direccion,"distrito":distrito,"provincia":provincia,"departamento":departamento},
					          success: function(data){
					        	  if(data == "ACTUALIZACION EXITOSA"){
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
        				}else{
                        	alertaInfoPersonalizada("Ingrese su departamento");
                        }
        			}else{
                    	alertaInfoPersonalizada("Ingrese su provincia");
                    }
        		}else{
                	alertaInfoPersonalizada("Ingrese su distrito");
                }
        	}else{
            	alertaInfoPersonalizada("Ingrese su direccion");
            }
    	}else{
        	alertaInfoPersonalizada("Ingrese su contraseña");
        }

    }else{
    	alertaInfoPersonalizada("Ingrese su usuario");
    }
        	}else{
        		alertaErrorPersonalizada("Formato de DNI no valido (8 digitos)");
        	}
        }else{
        	alertaInfoPersonalizada("Ingrese su dni");
        }
    }else{
    	alertaErrorPersonalizada("Formato de nombre no valido");
    }
    }else{
    	alertaInfoPersonalizada("Ingrese su nombre");
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
        
</body>
</html>