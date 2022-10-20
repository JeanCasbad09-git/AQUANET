<%@page import="entities.CortesMantenimiento"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Actualizar Cortes por Mantenimiento</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">

<script defer="defer" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script></head>
<body style="background-color: #f0f8ff">
	<div class="container">
                    <br>
                <h1 class="text-center - text-success">ACTUALIZAR CORTES POR MANTENIMIENTO</h1>
                <br>
                <form>
                    <table>
                        <%
                CortesMantenimiento cor=(CortesMantenimiento)request.getAttribute("cortes");
                %>
                <tr>
                	<input id="txtCodCorte" type="hidden" value=<%=cor.getIN_ID_CORTXMAN() %>>
                    <td><label class="col-sm-2  col-form-label ">Distrito:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
                    <td><input type="text" value=<%="'"+ cor.getVC_DISTRITO() + "'"%> class="form-control" id="txtDistrito" style="width: 600px;"></td>
                    
                </tr>
               <tr><td>&nbsp;</td></tr>
               <tr>
                    <td><label class="col-sm-2  col-form-label ">Provincia:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
                    <td><input type="text" value=<%="'"+ cor.getVC_PROVINCIA() + "'"%> class="form-control" id="txtProvincia" style="width: 600px;"></td>
                    
                </tr>
                <tr><td>&nbsp;</td></tr>
                <tr>
                    <td><label class="col-sm-2  col-form-label ">Departamento:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
                    <td><input type="text" value=<%="'"+ cor.getVC_DEPARTAMENTO() + "'"%> class="form-control" id="txtDepartamento" style="width: 600px;"></td>
                    
                </tr>
				
				<tr><td>&nbsp;</td></tr>
                <tr>
                    <td><label class="col-sm-2  col-form-label ">Comentario:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
                    <td><input type="text" value=<%="'"+ cor.getVC_COMENTARIO() + "'"%> class="form-control" id="txtComentario" style="width: 600px;"></td>
                    
                </tr>
				
				<tr><td>&nbsp;</td></tr>
                <tr>
                    <td><label class="col-sm-2  col-form-label ">Fecha Cortes:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
                    <td><input type="text" value=<%="'"+ cor.getDT_FECHA() + "'"%> class="form-control" id="txtFecha" style="width: 600px;"></td>
                    
                </tr>

                    </table>
                    <br>
                    <div class="form-row">
            <center>
               <button type="button" class="btn btn-outline-success" onclick="grabar();">Actualizar</button>
               <a  class="btn btn-outline-danger" href="/AQUANET/cortes/index">Regresar</a>
            </center>
            </div>
                </form>
        </div>
</body>
</html>