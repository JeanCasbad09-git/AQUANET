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
<title>CrudCisterna</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">

<script defer="defer" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body style="background-color: #f0f8ff">
        <div class="container">
            <br>
        <h1 class="text-center - text-success">REGISTRO DE SOLICITUD</h1>
        <br>
        <form>
            
            <div class="form-group row">
                <label class="col-sm-2  col-form-label ">INGRESE PRIMER DESTINO  :</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="txtParada1" style="width: 600px;">
            </div>
            </div>
            <br>
            <div class="form-group row">
                <label class="col-sm-2  col-form-label " >INGRESE SEGUNDO DESTINO  :</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="txtParada2" style="width: 600px;">
            </div>
            </div>
            <br>
             <div class="form-group row">
                <label class="col-sm-2  col-form-label " >INGRESE TERCER DESTINO  :</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="txtParada3" style="width: 600px;">
            </div>
            </div>
            <br>
			<div class="form-group row">
					<label class="col-sm-2 form-label" for="form2Example17">ASIGNE DIA</label>
					<div class="col-sm-10">
                    <input type="date" class="form-control" id="asgfecha" style="width: 600px;"/>
                    </div>
                  </div>
   
                <br>
                 	<div class="form-group row">
				<label class="col-sm-2  col-form-label" for="id_chofer">SELECCIONE CHOFER</label>
				<div class="col-sm-10">
				<select id="id_chofer" name="id_chofer" class='form-control' style="width: 600px;">
				<% List<Trabajador> listaChofer = (List<Trabajador>)request.getAttribute("listChofer"); 
				if(listaChofer !=null)
					for(Trabajador lista:listaChofer){%>
					<option value="<%=lista.getIN_ID_TRABAJADOR() %> "><%=lista.getVC_NOMBRES() %></option>   
					<%}%> 
				</select>
				 </div>
		    	 </div>
		    		
		    		   <br>
		    		   
		    		<div class="form-group row">
				<label class="col-sm-2 control-label" for="id_aguatero">SELECCIONE AGUATERO</label>
				<div class="col-sm-10">
				<select id="id_aguatero" name="id_aguatero" class='form-control' style="width: 600px;">
				<% List<Trabajador> listaAguatero = (List<Trabajador>)request.getAttribute("listAguatero"); 
				if(listaAguatero !=null)
					for(Trabajador lista:listaAguatero){%>
					<option value="<%=lista.getIN_ID_TRABAJADOR() %> "><%=lista.getVC_NOMBRES() %></option>   
					<%}%> 
				</select>	
		    		</div>
		    		</div>
		    		
		    		   <br>
		    		   
		    		   
		    			<div class="form-group row">
				<label class="col-sm-2 control-label" for="id_cisterna">SELECCIONE EL VEHICULO</label>
				<div class="col-sm-10">
				<select id="id_cisterna" name="id_cisterna" class='form-control' style="width: 600px;">
				<% List<Cisterna> listaCisterna = (List<Cisterna>)request.getAttribute("listcisterna"); 
				if(listaAguatero !=null)
					for(Cisterna lista:listaCisterna){%>
					<option value="<%=lista.getIN_ID_CISTERNA() %> "><%=lista.getVC_PLACA_COCHE() %></option>   
					<%}%> 
				</select>	
		    		</div>
		    	</div>
					   <br>
					<div class="form-row">
            
               <button class="btn btn-dark" type="button" onclick="grabar();">Registrar</button>
               <button type="button" class="btn btn-info" onclick="limpiar();" style="color:white;">Limpiar</button>
               <button type="button" class="btn btn-danger" onclick="location.href='http://localhost:8080/AQUANET/home/index'" style="color:white;">Regresar</button>
            
            </div>
					
                  
                  <a href="#!" class="small text-muted">Terminos de uso</a>
                  <a href="#!" class="small text-muted">Politicas de privacidad</a>
                </form>

              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
</body>

<script>
function limpiar(){
	$("#txtParada1").val('');
	$("#txtParada2").val('');
	$("#txtParada3").val('');
 }

function grabar(){ 
	var parada1 = $("#txtParada1").val().trim();
    var parada2 = $("#txtParada2").val().trim();
    var parada3 = $("#txtParada3").val().trim();
    var fecha = 	$("#asgfecha").val();
 	var id_chofer = $("#id_chofer").val();
 	var id_aguatero = $("#id_aguatero").val();
 	var id_cisterna = $("#id_cisterna").val();
    
    if(parada1 != "" && parada1!=null){
    	if(parada2 != "" && parada2!=null){
    		if(parada3 != "" && parada3!=null){
    			if(fecha != "" && fecha!=null){
    			
    		
    		 $.ajax({
    		        type: 'POST',
    		          url: "AsigneCisterna",
    		          data: {"parada1":parada1,"parada2":parada2,"parada3":parada3,"fecha":fecha,"id_chofer":id_chofer,"id_aguatero":id_aguatero,"id_cisterna":id_cisterna},
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