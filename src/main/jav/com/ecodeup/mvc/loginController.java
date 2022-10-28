package com.ecodeup.mvc;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.DAOFactory;
import entities.Persona;
import entities.Session;
import entities.Usuario;
import interfaces.PersonaInterface;
import interfaces.UsuarioInterface;

@Controller
@RequestMapping("/home")
public class loginController {
	

	DAOFactory daoFactory=DAOFactory.getDAOFactory(DAOFactory.MYSQL8);

	UsuarioInterface usuInt=daoFactory.getUsuarioInterface();
	PersonaInterface persInt=daoFactory.getPersonaInterface();
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String verLogin(ModelMap model) {
		return "login";
	}
	
	@RequestMapping(value="/verificarLogin",method=RequestMethod.POST)
	@ResponseBody
	public String verificarLogin(String user,String password,ModelMap model) {
		String existe = usuInt.verificarCredenciales(user, password);
		if(existe == "SI") {
			existe = "BIENVENIDO";
			Session.getCurrentInstance().setLoggedUser(user);
		}else {
			existe = "USUARIO O CONTRASEÑA INCORRECTOS"; 
		}
		return existe;
	}
	
	@RequestMapping(value="/usuario",method=RequestMethod.GET)
	public String verUsuario(ModelMap model) {
		
		return "registraUsuario";
	}
	
	@RequestMapping(value="/registrarUsuario",method=RequestMethod.POST)
	@ResponseBody
	public String registrarUsuario(String nombre,String dni,String user,String password,String direccion,String distrito,String provincia,String departamento,ModelMap model) {
		int resulRegisPers = -1;
		int resulRegisUsu = -1;
		String resultadoFinal="";
		int codPersona =0;
		String validarUser="0";
		Persona objPer = new Persona();
		objPer.setVC_NOMBRE(nombre);
		objPer.setVC_DNI(dni);
		objPer.setVC_DIRECCION(direccion);
		objPer.setVC_DISTRITO(distrito);
		objPer.setVC_PROVINCIA(provincia);
		objPer.setVC_DEPARTAMENTO(departamento);
		
		validarUser = usuInt.validacionExistenciaUsuario(user);
		if(validarUser == "0") {
		resulRegisPers = persInt.registrarPersona(objPer);
		if(resulRegisPers != 2) {
		if(resulRegisPers == 1) {
			codPersona = persInt.obtenerUltimoCodigo();
			Usuario objUsu = new Usuario();
			objUsu.setIN_ID_PERSONA(codPersona);
			objUsu.setVC_USER(user);
			objUsu.setVC_PASSWORD(password);
			
			resulRegisUsu = usuInt.registrarUsuario(objUsu);
			if(resulRegisUsu == 1) {
				resultadoFinal ="REGISTRO EXITOSO";
			}else {
				resultadoFinal ="REGISTRO ERRONEO";
			}
			
		}
		else {
			resultadoFinal ="REGISTRO ERRONEO";
		}
		}
		else {
			resultadoFinal ="NOMBRE Y/O DNI YA ESTAN REGISTRADOS";
		}
		}else {
			resultadoFinal ="EL USUARIO YA EXISTE";
		}
		return resultadoFinal;
	}
	
	@RequestMapping(value="/contrasenna",method=RequestMethod.GET)
	public String verRecuperarContrasenna(ModelMap model) {
		return "recuperarContrasennaUsuario";
	}
	
	
	@RequestMapping(value="/recuperarContrasenna",method=RequestMethod.POST)
	@ResponseBody
	public String recuperarContrasenna(String nombre,String dni,String user,ModelMap model) {
		String resulExistPer = "";
		String contrasenna="";

		Persona objPer = new Persona();
		objPer.setVC_NOMBRE(nombre);
		objPer.setVC_DNI(dni);


		resulExistPer = persInt.validarExistencia(objPer);
		if(resulExistPer != "NO") {		
			contrasenna = usuInt.validacionExistenciaUsuario(user);	
			if(contrasenna =="0") {
				contrasenna="USUARIO NO EXISTE";
			}
		}		
		else {
			contrasenna ="NOMBRE Y/O DNI NO EXISTEN";
		}

		return contrasenna;
	}
	
	@RequestMapping(value="/usuarioAct",method=RequestMethod.GET)
	public String verListUsuario(String parametros, ModelMap model) {
		
		ArrayList<Usuario> listado=new ArrayList<Usuario>();
		Usuario obj = new Usuario();
		String [] xCC = parametros.split(",");
//		String name = xCC[0].replace('%', ' ');
		String name =xCC[0]; 
		name=name.replaceAll("%20", " ");//para los espacios
		
		String user = xCC[1];
		user=user.replaceAll("%20", " ");//para los espacios
		
		if(name.equals("va")) {
			obj.setVC_NOMBRE("");
		}else {
			obj.setVC_NOMBRE(name);
		}
		if(user.equals("va")) {
			obj.setVC_USER("");
		}
		else
		{
			obj.setVC_USER(user);
		}

		
		listado = usuInt.listado(obj);
		model.addAttribute("Usuario",listado);
		
		String tipo = usuInt.obtenerTipoUsuarioXUser(Session.getCurrentInstance().getLoggedUser());
		model.addAttribute("Tipo",tipo);
		
		return "listadoUsuarios";
	}
	
	@RequestMapping(value="/eliminarUsuario",method=RequestMethod.POST)
	@ResponseBody
	public String eliminarUsuario(int IN_ID_USUARIO,int IN_ID_PERSONA,ModelMap model) {
		
		String resultado="";
		int elimPer = 0;
		int elimUsu = 0;
		elimPer = persInt.eliminarPersona(IN_ID_PERSONA);
		elimUsu = usuInt.eliminarUsuario(IN_ID_USUARIO);
		if(elimPer == 1 && elimUsu==1) {
			resultado="ELIMINACION EXITOSA";
		}
		return resultado;
	}
	
	@RequestMapping(value="/usuarioActualizar",method=RequestMethod.GET)
	public String actualizarUsuario(String parametros,ModelMap model) {
		String [] xCC = parametros.split(",");
		Usuario usuario = new Usuario();
		usuario = usuInt.obtenerUsuario(Integer.parseInt(xCC[0]), Integer.parseInt(xCC[1]));

		model.addAttribute("Usuario",usuario);
		String tipo = usuInt.obtenerTipoUsuarioXUser(Session.getCurrentInstance().getLoggedUser());
		model.addAttribute("Tipo",tipo);
		return "actualizarUsuario";
	}
	
	@RequestMapping(value="/actualizarUsuario",method=RequestMethod.POST)
	@ResponseBody
	public String updateUsuario(String nombre,String dni,String user,String codUser,String codPersona,String password,String direccion,String distrito,String provincia,String departamento,ModelMap model) {
		String resultado ="";
		int upPer = 0;
		int upUse = 0;
		
		Usuario usu = new Usuario();
		usu.setIN_ID_USUARIO(Integer.parseInt(codUser));
		usu.setVC_USER(user);
		usu.setVC_PASSWORD(password);
		
		upUse = usuInt.actualizarUsuario(usu);
		
		Persona per = new Persona();
		per.setIN_ID_PERSONA(Integer.parseInt(codPersona));
		per.setVC_DNI(dni);
		per.setVC_NOMBRE(nombre);
		per.setVC_DIRECCION(direccion);
		per.setVC_DISTRITO(distrito);
		per.setVC_PROVINCIA(provincia);
		per.setVC_DEPARTAMENTO(departamento);
		
		upPer = persInt.actualizarPersona(per);
		
		if(upPer == 1 && upUse ==1)
			resultado = "ACTUALIZACION EXITOSA";
		else
			resultado = "ERROR AL ACTUALIZAR";
		
		return resultado;
	}

	
	@RequestMapping(value="/menu",method=RequestMethod.GET)
	public String verMenu(ModelMap model) {
		return "Menu";
	}
	
	
}
