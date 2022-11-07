<%@page import="entities.Reclamos"%>
<%@page import="entities.CortesMantenimiento"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Vista Reclamo</title>
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
                <h1 class="text-center - text-success">VISTA RECLAMO</h1>
                <br>
                <form >
                <center>
                    <table  >
                        <%
                Reclamos rec=(Reclamos)request.getAttribute("reclamos");
                %>
                <tr>
                	<input id="txtCodReclamo" type="hidden" value=<%=rec.getIN_ID_RECLAMO() %>>
                    <td><label class="col-sm-2  col-form-label" style="padding-top: 0; padding-bottom: 0;">ID:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
                    <td><p type="text" id="txtId" style="margin-bottom: 0;" ><%=rec.getIN_ID_RECLAMO()%></p></td>
                    
                </tr>
               <tr><td>&nbsp;</td></tr>
               <tr>
                    <td><label class="col-sm-2  col-form-label " style="padding-top: 0; padding-bottom: 0;">User:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
                    <td><p type="text" id="txtUser" style="margin-bottom: 0;""><%=rec.getUsuarioCreador()%></p></td>
                    
                </tr>
                <tr><td>&nbsp;</td></tr>
                <tr>
                    <td><label class="col-sm-2  col-form-label " style="padding-top: 0; padding-bottom: 0;">Estado:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
                    <td><p type="text"  id="txtEstado" style="margin-bottom: 0;""><%=rec.getVC_ESTADO()%> </p></td>
                    
                </tr>
				
				<tr><td>&nbsp;</td></tr>
                <tr>
                    <td><label class="col-sm-2  col-form-label " style="padding-top: 0; padding-bottom: 0;">Reclamo:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
                    <td><p type="text" id="txtReclamo" style="margin-bottom: 0;""> <%=rec.getVC_RECLAMO()%></p></td>
                    
                </tr>
				
				<tr><td>&nbsp;</td></tr>
                <tr>
                    <td><label class="col-sm-2  col-form-label " style="padding-top: 0; padding-bottom: 0;">Fecha Registro:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
                    <td><p type="text" id="txtFecha" style="margin-bottom: 0;""><%=rec.getDT_FECHA()%></p></td>
                    
                </tr>

                    </table>
                    </center>
                    <br>
                    <div class="form-row">
            <center>
               <a  class="btn btn-outline-danger" href="/AQUANET/reclamos/listado">Regresar</a>
            </center>
            </div>
                </form>
        </div>
</body>
</html>