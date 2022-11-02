<%@page import="java.util.ArrayList"%>
<%@page import="entities.Usuario"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html xmlns:th="http://thymelesf.org">
<head>
<meta charset="ISO-8859-1">
<title>Listado Usuarios</title>
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
        <h1 class="text-center - text-success"">LISTADO DE USUARIOS</h1>
        
        <br>
        <form>
        <div class="form-group row">
                <label class="col-sm-2  col-form-label ">Nombre Completo :</label>
                <input type="text" class="form-control" id="txtNombre" style="width: 200px;">

            <label class="col-sm-2  col-form-label ">Usuario :</label>
                <input type="text" class="form-control" id="txtUsuario" style="width: 200px;">
            
            
            </div>
        </form>
        <br>
<button type="button" class="btn btn-outline-info" onclick="filtrar();">BUSCAR</button>

<button type="button" class="btn btn-outline-warning" onclick="limpiar();">LIMPIAR</button>
<br>
        <br>
        <div>
            <table class="table">
                <thead >
                <tr class="table-info">
                    <th scope="col">NOMBRE</th>
                    <th scope="col">DNI</th>
                    <th scope="col">USUARIO</th>
                    <th scope="col">FECHA REGISTRO</th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <%
                List<Usuario> lista=(List<Usuario>)request.getAttribute("Usuario");
                for(Usuario usu:lista){ %>
                <tr  class="table-warning">
                    <td><%=usu.getVC_NOMBRE() %></td>
                    <td><%=usu.getVC_DNI() %></td>
                    <td><%=usu.getVC_USER() %></td>
                    <td><%=usu.getDT_FEC_REG() %></td>
                    <td >
                    <%if(tipo.trim().equals("ADM")) {%>
                    <input type="button" class="btn btn-danger btn-sm" value="ELIMINAR" onclick=<%="eliminar('"+usu.getIN_ID_USUARIO() +"','"+usu.getIN_ID_PERSONA() +"');" %> style='color:white; font-weight: bold;'>
                    <%}else{ %>
                    <input type="button" class="btn btn-danger btn-sm" value="ELIMINAR" onclick=<%="eliminar('"+usu.getIN_ID_USUARIO() +"','"+usu.getIN_ID_PERSONA() +"');" %> style='color:white; font-weight: bold;display:none'>
                    <%} %>
                    </td>
                    <td >
                    <%if(tipo.trim().equals("ADM")) {%>
                    <input type="button" class="btn btn-warning btn-sm" value="ACTUALIZAR" onclick=<%="actualizar('"+usu.getIN_ID_USUARIO() +"','"+usu.getIN_ID_PERSONA() +"');" %> style='color:white; font-weight: bold;'>
                    <%}else{ %>
                    <input type="button" class="btn btn-warning btn-sm" value="ACTUALIZAR" onclick=<%="actualizar('"+usu.getIN_ID_USUARIO() +"','"+usu.getIN_ID_PERSONA() +"');" %> style='color:white; font-weight: bold;display:none'>
                    <%} %>
                    </td>
                    </tr>
                <%} %>
                </tbody>
            </table>
            
        </div>

        </div>
        <script type="text/javascript">
            function eliminar(pCodUsu,pCodPer){
            	Swal.fire({
            		position: 'center',
          		  title: "ELIMINAR USUARIO?",
          		  showDenyButton: true,
          		  confirmButtonText: 'SI',
          		  denyButtonText: 'NO',
          		}).then((result) => {
            	   if (result.isConfirmed) {
                    $.ajax({
                        type: "POST",
                        url: "eliminarUsuario", 
                        data: {"IN_ID_USUARIO":pCodUsu,"IN_ID_PERSONA":pCodPer},
                        success: function(data){
                        	Swal.fire({
                        		position: 'center',
                        		icon: 'success',
                      		  title: "ELIMINACION EXITOSA",
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
            
            function filtrar(){
            	var nombre = $("#txtNombre").val().trim();
                var user = $("#txtUsuario").val().trim();
                if((nombre == null || nombre=="") && (user == null || user==""))
                	window.location.href = 'http://localhost:8080/AQUANET/home/usuarioAct?parametros=va,va';
                else if (nombre == null || nombre=="")
                	window.location.href = 'http://localhost:8080/AQUANET/home/usuarioAct?parametros=va,'+user;
                else if(user == null || user=="")
                	window.location.href = 'http://localhost:8080/AQUANET/home/usuarioAct?parametros=' + nombre+',va';
                else
                	window.location.href = 'http://localhost:8080/AQUANET/home/usuarioAct?parametros=' + nombre+','+user;
            }
            
            function limpiar(){
                window.location.href = 'http://localhost:8080/AQUANET/home/usuarioAct?parametros=va,va';
            }
            
            function actualizar(pCodUsuario,pCodPersona){
                window.location.href = 'http://localhost:8080/AQUANET/home/usuarioActualizar?parametros='+ pCodUsuario+','+pCodPersona;
            }
            
        </script>
</body>
</html>