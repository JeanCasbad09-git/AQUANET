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
            <div class="col-md-6 col-lg-5 d-none d-md-block">
              <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/img1.webp"
                alt="login form" class="img-fluid" style="border-radius: 1rem 0 0 1rem;" />
            </div>
            <div class="col-md-6 col-lg-7 d-flex align-items-center">
              <div class="card-body p-4 p-lg-5 text-black">
                <form>

                  <h5 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Registrate</h5>

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
				<label class="control-label" for="id_chofer">DEPARTAMENTO</label>
				<select id="id_chofer" name="IN_ID_CHOFER" class='form-control'>
					<option value=" ">[Seleccione Chofer]</option>    
				</select>
			
		    		</div>
		    	<div class="form-outline mb-4">
				<label class="control-label" for="id_aguatero">DEPARTAMENTO</label>
				<select id="id_aguatero" name="IN_ID_AGUATERO" class='form-control'>
					<option value=" ">[Seleccione Aguatero]</option>    
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
    var fecha = new Date("#asgfecha");
 	var id_chofer = $("#id_chofer").val();
 	var id_aguatero = $("#id_aguatero").val();
    
    if(parada1 != "" && parada1!=null){
    	if(parada1.match("^[a-zA-Z ]+$")){
    		 $.ajax({
    		        type: 'POST',
    		          url: "AsigneCisterna",
    		          data: {"parada1":parada1,"parada2":parada2,"parada3":parada3,"fecha":fecha},
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