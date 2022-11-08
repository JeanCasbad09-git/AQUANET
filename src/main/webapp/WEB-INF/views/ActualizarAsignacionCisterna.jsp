<%@page import="entities.AsignacionCisterna"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="entities.Trabajador"%>
<%@page import="entities.Cisterna"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Actualizar Asignacion de Cisterna</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">

<script defer="defer" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script></head>
<body style="background-color: #f0f8ff">
	<div class="container">
                    <br>
                <h1 class="text-center - text-success">ACTUALIZAR ASIGNACION DE CISTERNA</h1>
                <br>
                <form>
                    <table>
                        <%
                AsignacionCisterna asg=(AsignacionCisterna)request.getAttribute("asignacion");
                %>
                <tr>
                	<input id="id_asg_cisterna" type="hidden" value=<%=asg.getIN_ID_ASIG_CISTERNA() %>>
                    <td><label class="col-sm-2  col-form-label ">INGRESE PRIMER DESTINO:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
                    <td><input type="text" value=<%="'"+ asg.getVC_PARADA_1() + "'"%> class="form-control" name="txtParada1" id="txtParada1" style="width: 600px;"></td>
                    
                </tr>
               <tr><td>&nbsp;</td></tr>
               <tr>
                    <td> <label class="col-sm-2  col-form-label " >INGRESE SEGUNDO DESTINO:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
                    <td><input type="text" value=<%="'"+ asg.getVC_PARADA_2()+ "'"%> class="form-control" name="txtParada2" id="txtParada2" style="width: 600px;"></td>
                    
                </tr>
                <tr><td>&nbsp;</td></tr>
                <tr>
                    <td>  <label class="col-sm-2  col-form-label " >INGRESE TERCER DESTINO:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
                    <td><input type="text" value=<%="'"+ asg.getVC_PARADA_3() + "'"%> class="form-control" name="txtParada3" id="txtParada3" style="width: 600px;"></td>
                    
                </tr>
				
				<tr><td>&nbsp;</td></tr>
                <tr>
                    <td><label class="col-sm-2 form-label" for="form2Example17">ASIGNE DIA:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
                    <td><input type="date" value=<%="'"+ asg.getDT_FEC_ASIGNADO() + "'"%> class="form-control" name="asgfecha" id="asgfecha" style="width: 600px;"></td>
                    
                </tr>
				
				<tr><td>&nbsp;</td></tr>
                <tr>
                    <td><label class="col-sm-2  col-form-label" for="id_chofer">SELECCIONE CHOFER:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
                    <td><select id="id_chofer" name="id_chofer" class='form-control' style="width: 600px;">
				<% List<Trabajador> listaChofer = (List<Trabajador>)request.getAttribute("listChofer"); 
				if(listaChofer !=null)
					for(Trabajador lista:listaChofer){%>
					<option value="<%=lista.getIN_ID_TRABAJADOR() %> "><%=lista.getVC_NOMBRES() %></option>   
					<%}%> 
				</select></td>       
                </tr>
                       
                
          	    <tr><td>&nbsp;</td></tr>
                <tr>
                    <td><label class="col-sm-2  col-form-label" for="id_aguatero">SELECCIONE AGUATERO:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
                    <td><select id="id_aguatero" name="id_aguatero" class='form-control' style="width: 600px;">
				<% List<Trabajador> listaAguatero = (List<Trabajador>)request.getAttribute("listAguatero");  
				if(listaAguatero !=null)
					for(Trabajador lista:listaAguatero){%>
						<option value="<%=lista.getIN_ID_TRABAJADOR() %> "><%=lista.getVC_NOMBRES() %></option>   
					<%}%> 
				</select></td>       
                </tr>
                
                <tr><td>&nbsp;</td></tr>
                <tr>
                    <td><label class="col-sm-2  col-form-label" for="id_aguatero">SELECCIONE CISTERNA:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
                    <td><select id="id_cisterna" name="id_cisterna" class='form-control' style="width: 600px;">
				<% List<Cisterna> listaCisterna = (List<Cisterna>)request.getAttribute("listcisterna");  
				if(listaCisterna !=null)
					for(Cisterna lista:listaCisterna){%> 
					<option value="<%=lista.getIN_ID_CISTERNA() %> "><%=lista.getVC_PLACA_COCHE() %></option>   
					<%}%> 
				</select></td>       
                </tr>

                    </table>
                    <br>
                    <div class="form-row">
            <center>
               <button type="button" class="btn btn-outline-success" onclick="grabar();">ACTUALIZAR</button>
               <button type="button" class="btn btn-outline-danger" onclick="location.href='http://localhost:8080/AQUANET/cisterna/ListaAsgCisterna'">RETORNAR LISTA</button>
            </center>
            </div>
                </form>
        </div>
</body>
<script>

function grabar(){ 
	var parada1 = $("#txtParada1").val().trim();
    var parada2 = $("#txtParada2").val().trim();
    var parada3 = $("#txtParada3").val().trim();
    var fecha = 	$("#asgfecha").val();
 	var id_chofer = $("#id_chofer").val();
 	var id_aguatero = $("#id_aguatero").val();
 	var id_cisterna = $("#id_cisterna").val();
    var id_asg_cisterna = $("#id_asg_cisterna").val();
    if(parada1 != "" && parada1!=null){
    	if(parada2 != "" && parada2!=null){
    		if(parada3 != "" && parada3!=null){
    			if(fecha != "" && fecha!=null){
    			
    		
    		 $.ajax({
    		        type: 'POST',
    		          url: "actualizarCisterna",
    		          data: {"parada1":parada1,"parada2":parada2,"parada3":parada3,"fecha":fecha,"id_chofer":id_chofer,"id_aguatero":id_aguatero,"id_cisterna":id_cisterna,"id_asg_cisterna":id_asg_cisterna},
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
		    	alertaInfoPersonalizada("Asigne la fecha")
		    }
	    }else{
	    	alertaInfoPersonalizada("Ingrese la tercera parada")
	    }
    }else{
    	alertaInfoPersonalizada("Ingrese la segunda parada")
    }
   }else{
    	alertaInfoPersonalizada("Ingrese la primera parada");
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