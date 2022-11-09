<%@page import="entities.Distrito"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entities.Solicitud_Servicio"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html xmlns:th="http://thymelesf.org">
<head>
<meta charset="ISO-8859-1">
<title>LISTADO DISTRITOS</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">

<script defer="defer" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>


<body style="background-color: #f0f8ff;" >
        <div class="container">
            <br>
        <h1 class="text-center - text-success"">LISTADO DISTRITOS</h1>
        
        <br>
        

<br>
        <br>
        <div>
            <table class="table">
                <thead >
                <tr class="table-info">
                    <th scope="col">ID</th>
                    <th scope="col">PLACA CISTERNA</th>
                </tr>
                </thead>
                <tbody>
                <%
                List<Distrito> lista=(List<Distrito>)request.getAttribute("ListDistrito");
                for(Distrito sede:lista){ %>
                <tr  class="table-warning">
                    <td><%=sede.getVC_DISTRITO() %></td>
                    <td>ACTIVO</td>          
                    </tr>
                <%} %>
                </tbody>
            </table>
            
                
</body>
</html>