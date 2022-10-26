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
<body style="background-color: #7fb0b8;">
<section class="vh-100" style="background-color: #7fb0b8;">
  <div class="container py-5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col col-xl-10">
        <div class="card" style="border-radius: 1rem;">
          <div class="row g-0">
       
            <div class="col-md-6 col-lg-7 d-flex align-items-center">
              <div class="card-body p-4 p-lg-5 text-black">
                <form>
			
                  <h5 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Programacion de Cisterna</h5>

                  <div class="form-outline mb-4">
                    <input type="text" class="form-control form-control-lg" id="txtParada1"/>
                    <label class="form-label" for="form2Example17">Ingrese el primer destino</label>
                  </div>

                  <div class="form-outline mb-4">
                    <input type="text" class="form-control form-control-lg" id="txtParada2" />
                    <label class="form-label" for="form2Example27">Ingrese la segunda parada</label>
                  </div>
                  
                  <div class="form-outline mb-4">
                    <input type="text" class="form-control form-control-lg" id="txtParada3" />
                    <label class="form-label" for="form2Example27">Ingrese la tercera parada</label>
                  </div>
				
				<div class="form-outline mb-4">
                    <input type="date" class="form-control form-control-lg" id="asgfecha"/>
                    <label class="form-label" for="form2Example17">Asigne el dia </label>
                  </div>
                
                 	<div class="form-outline mb-4">
				<label class="control-label" for="id_chofer">Seleccione al chofer</label>
				
				<select id="id_chofer" name="id_chofer" class='form-control'>
				<% List<Trabajador> listaChofer = (List<Trabajador>)request.getAttribute("listChofer"); 
				if(listaChofer !=null)
					for(Trabajador lista:listaChofer){%>
					<option value="<%=lista.getIN_ID_TRABAJADOR() %> "><%=lista.getVC_NOMBRES() %></option>   
					<%}%> 
				</select>
			
		    		</div>
		    		
		    		<div class="form-outline mb-4">
				<label class="control-label" for="id_aguatero">Seleccione al Aguatero</label>
				
				<select id="id_aguatero" name="id_aguatero" class='form-control'>
				<% List<Trabajador> listaAguatero = (List<Trabajador>)request.getAttribute("listAguatero"); 
				if(listaAguatero !=null)
					for(Trabajador lista:listaAguatero){%>
					<option value="<%=lista.getIN_ID_TRABAJADOR() %> "><%=lista.getVC_NOMBRES() %></option>   
					<%}%> 
				</select>	
		    		</div>
		    		
		    			<div class="form-outline mb-4">
				<label class="control-label" for="id_cisterna">Seleccione un vehiculo disponible</label>
				
				<select id="id_cisterna" name="id_cisterna" class='form-control'>
				<% List<Cisterna> listaCisterna = (List<Cisterna>)request.getAttribute("listcisterna"); 
				if(listaAguatero !=null)
					for(Cisterna lista:listaCisterna){%>
					<option value="<%=lista.getIN_ID_CISTERNA() %> "><%=lista.getVC_PLACA_COCHE() %></option>   
					<%}%> 
				</select>	
		    		</div>
		    	
					
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
    	if(parada1.match("^[a-zA-Z ]+$")){
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
    	}
    
       else{
    	alertaErrorPersonalizada("Formato del tipo de cambio no valido");
    }
    }else{
    	alertaInfoPersonalizada("Ingrese el tipo de cambio");
    }
}
   
    
$.getJSON("CrudCisterna", {}, function(data){ 
	$.each(data, function(i, item){
		$("#IN_ID_CHOFER").append("<option value='"+ item +"'>"+ item +"</option>");
	
	});
});   
   
    

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