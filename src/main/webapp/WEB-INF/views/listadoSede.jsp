<%@page import="entities.Sede"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entities.Solicitud_Servicio"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html xmlns:th="http://thymelesf.org">
<head>
<meta charset="ISO-8859-1">
<title>Listado sede</title>
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
        <h1 class="text-center - text-success"">LISTADO DE SEDE</h1>
        
        <br>
        <form>
        <div class="form-group row">
                <label class="col-sm-2  col-form-label ">Distrito :</label>
                <input type="text" class="form-control" id="txtDistrito" style="width: 150px;">

            <label class="col-sm-2  col-form-label ">Provincia :</label>
                <input type="text" class="form-control" id="txtProvincia" style="width: 150px;">
            
            <label class="col-sm-2  col-form-label ">Departamento :</label>
                <input type="text" class="form-control" id="txtDepartamento" style="width: 150px;">
            
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
                	<th scope="col">DEPARTAMENTO</th>
                	<th scope="col">PROVINCIA</th>
                    <th scope="col">DISTRITO</th>
                    <th scope="col">SEDE</th>
                    <th scope="col">USUARIO</th>
                    <th scope="col"></th>
<!--                     <th scope="col"></th> -->
                </tr>
                </thead>
                <tbody>
                <%
                List<Sede> lista=(List<Sede>)request.getAttribute("Solicitud");
                for(Sede sol:lista){ %>
                <tr  class="table-warning">
                    <td><%=sol.getVC_DEPARTAMENTO() %></td>
                    <td><%=sol.getVC_PROVINCIA() %></td>
                    <td><%=sol.getVC_DISTRITO() %></td>
                    <td><%=sol.getVC_SEDE() %></td>
                    <td><%=sol.getVC_USER() %></td>
                    <td ><input type="button" class="btn btn-danger btn-sm" value="ELIMINAR" onclick=<%="eliminar('"+sol.getIN_ID_SEDE() +"');" %> style='color:white; font-weight: bold;'></td>
<%--                     <td ><input type="button" class="btn btn-warning btn-sm" value="ATENDIDA" onclick=<%="actualizar('"+sol.getIN_ID_SEDE() +"');" %> style='color:white; font-weight: bold;'></td> --%>
                   
                    </tr>
                <%} %>
                </tbody>
            </table>
            
        </div>
		<button type="button" class="btn btn-dark" onclick="location.href='http://localhost:8080/AQUANET/sede/registrar'" style="color:white;">Nueva Sede</button>
		
		
        </div>
        
        <script type="text/javascript">
            function eliminar(pCodSol){
            	Swal.fire({
            		position: 'center',
          		  title: "ELIMINAR SEDE?",
          		  showDenyButton: true,
          		  confirmButtonText: 'SI',
          		  denyButtonText: 'NO',
          		}).then((result) => {
            	   if (result.isConfirmed) {
                    $.ajax({
                        type: "POST",
                        url: "eliminarSede", 
                        data: {"IN_ID_SEDE":pCodSol},
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
            	var distrito = $("#txtDistrito").val().trim();
                var provincia = $("#txtProvincia").val().trim();
                var departamento = $("#txtDepartamento").val().trim();
                
                if((distrito != null && distrito!="") && (provincia != null && provincia!="") && (departamento != null && departamento!=""))
                	window.location.href = 'http://localhost:8080/AQUANET/sede/sedeAct?parametros='+distrito +','+provincia +','+ departamento;
                else if ((distrito != null && distrito!="") && (provincia != null && provincia!=""))
                	window.location.href = 'http://localhost:8080/AQUANET/sede/sedeAct?parametros='+distrito+','+ provincia+',va';
                else if((distrito != null && distrito!="") && (departamento != null && departamento!=""))
                	window.location.href = 'http://localhost:8080/AQUANET/sede/sedeAct?parametros=' + distrito+',va,' + departamento;
                else if((provincia != null && provincia!="") && (departamento != null && departamento!=""))
                	window.location.href = 'http://localhost:8080/AQUANET/sede/sedeAct?parametros=va,'+ provincia+ ','+ departamento;
                else if(distrito != null && distrito!="")
                	window.location.href = 'http://localhost:8080/AQUANET/sede/sedeAct?parametros='+ distrito+ ',va,va';
                else if(provincia != null && provincia!="")
                	window.location.href = 'http://localhost:8080/AQUANET/sede/sedeAct?parametros=va,'+provincia + ',va';
                else if(departamento != null && departamento!="")
                	window.location.href = 'http://localhost:8080/AQUANET/sede/sedeAct?parametros=va,va,' + departamento;
                else
                	window.location.href = 'http://localhost:8080/AQUANET/sede/sedeAct?parametros=va,va,va';
            }
            
            function limpiar(){
                window.location.href = 'http://localhost:8080/AQUANET/sede/sedeAct?parametros=va,va,va';
            }
            
            function actualizar(pCodSol){
            	Swal.fire({
            		position: 'center',
          		  title: "ATENDER SEDE?",
          		  showDenyButton: true,
          		  confirmButtonText: 'SI',
          		  denyButtonText: 'NO',
          		}).then((result) => {
            	   if (result.isConfirmed) {
                    $.ajax({
                        type: "POST",
                        url: "actualizarSede", 
                        data: {"IN_ID_SEDE":pCodSol},
                        success: function(data){
                        	Swal.fire({
                        		position: 'center',
                        		icon: 'success',
                      		  title: "ATENCION EXITOSA",
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
            
        </script>
</body>
</html>