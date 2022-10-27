<%@page import="java.util.ArrayList"%>
<%@page import="entities.Usuario"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>REGISTRAR SEDE</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">

<script defer="defer" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script></head>
<body style="background-color: #f0f8ff">
        <div class="container">
            <br>
        <h1 class="text-center - text-success">REGISTRO DE SEDE</h1>
        <br>
        <form>
            
            <div class="form-group row">
                <label class="col-sm-2  col-form-label ">SEDE  :</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="txtSede" style="width: 600px;">
            </div>
            </div>
            <br>
            <div class="form-group row">
                <label class="col-sm-2  col-form-label " >DISTRITO  :</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="txtDistrito" style="width: 600px;">
            </div>
            </div>
            <br>
             <div class="form-group row">
                <label class="col-sm-2  col-form-label " >PROVINCIA  :</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="txtProvincia" style="width: 600px;">
            </div>
            </div>
            <br>
                <div class="form-group row">
                <label class="col-sm-2  col-form-label " >DEPARTAMENTO  :</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="txtDepartamento"  style="width: 600px;">
            </div>
            </div>
            <br>
            <div class="form-group row">
                <label class="col-sm-2  col-form-label " >USUARIO  :</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="txtUsuario" style="width: 200px;">
            </div>
            </div>
            <br>
            <div class="form-row">
            <center>
               <button type="button" class="btn btn-outline-success" onclick="grabar();">GRABAR</button>
               <button type="button" class="btn btn-outline-info" onclick="limpiar();">LIMPIAR</button>
               <button type="button" class="btn btn-outline-danger" onclick="location.href='http://localhost:8080/AQUANET/sede/sedeAct?parametros=va,va,va'">CANCELAR</button>
            </center>
            </div>
            <br>
        </form>
        </div>
        
        <h1>Hola</h1>
        <script type="text/javascript">
        
        function limpiar(){
        	$("#txtSede").val('');
        	$("#txtDistrito").val('');
        	$("#txtProvincia").val('');
        	$("#txtDepartamento").val('');
        	$("#txtUsuario").val('');
         }
        
	function grabar(){ 
		var sede = $("#txtSede").val().trim();
	    var distrito = $("#txtDistrito").val().trim();
	    var provincia = $("#txtProvincia").val().trim();
	    var departamento = $("#txtDepartamento").val().trim();
	    var usuario = $("#txtUsuario").val().trim();

	    
	    if(sede!= null && sede != ""){
	    	if(distrito!= null && distrito != ""){
	    		if(provincia!= null && provincia != ""){
	    			if(departamento!= null && departamento != ""){
	    				if(usuario!= null && usuario != ""){
	    					 $.ajax({
							        type: 'POST',
							          url: "registrarSede",
							          data: {"sede":sede,"distrito":distrito,"provincia":provincia,"departamento":departamento,"usuario":usuario},
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
					    	alertaInfoPersonalizada("Ingrese su usuario")
					    }
				    }else{
				    	alertaInfoPersonalizada("Ingrese su departamento")
				    }
			    }else{
			    	alertaInfoPersonalizada("Ingrese su provincia")
			    }
		    }else{
		    	alertaInfoPersonalizada("Ingrese su distrito")
		    }
	    }else{
	    	alertaInfoPersonalizada("Ingrese su sede")
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