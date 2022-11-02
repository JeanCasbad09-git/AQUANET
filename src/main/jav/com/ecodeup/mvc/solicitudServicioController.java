package com.ecodeup.mvc;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.DAOFactory;
import entities.Session;
import entities.Solicitud_Servicio;
import interfaces.Solicitud_ServicioInterface;
import interfaces.UsuarioInterface;

@Controller
@RequestMapping("/solicitudServicio")
public class solicitudServicioController {
	DAOFactory daoFactory=DAOFactory.getDAOFactory(DAOFactory.MYSQL8);

	UsuarioInterface usuInt=daoFactory.getUsuarioInterface();
	Solicitud_ServicioInterface solSerInt = daoFactory.getSolicitud_ServicioInterface();
	
	@RequestMapping(value="/solicitudesAct",method=RequestMethod.GET)
	public String verListSolicitudes(String parametros, ModelMap model) {
		
		String tipo = usuInt.obtenerTipoUsuarioXUser(Session.getCurrentInstance().getLoggedUser());
		
		ArrayList<Solicitud_Servicio> listado=new ArrayList<Solicitud_Servicio>();
		Solicitud_Servicio obj = new Solicitud_Servicio();
		String [] xCC = parametros.split(",");
		String distrito =xCC[0]; 
		distrito=distrito.replaceAll("%20", " ");//para los espacios
		
		String provincia = xCC[1];
		provincia=provincia.replaceAll("%20", " ");//para los espacios
		
		String departamento = xCC[2];
		departamento=departamento.replaceAll("%20", " ");//para los espacios
		
		if(distrito.equals("va")) {
			obj.setVC_DISTRITO("");
		}else {
			obj.setVC_DISTRITO(distrito);
		}
		if(provincia.equals("va")) {
			obj.setVC_PROVINCIA("");
		}
		else
		{
			obj.setVC_PROVINCIA(provincia);
		}

		if(departamento.equals("va")) {
			obj.setVC_DEPARTAMENTO("");
		}
		else
		{
			obj.setVC_DEPARTAMENTO(departamento);
		}
		
		listado = solSerInt.listado(obj);
		model.addAttribute("Solicitud",listado);
		model.addAttribute("Tipo",tipo);

		return "listadoSolicitudes";
	}
	
	@RequestMapping(value="/actualizarSolicitud",method=RequestMethod.POST)
	@ResponseBody
	public String updateSolicitud(String IN_ID_SOLICITUD_SERVICIO,ModelMap model) {
		String resultado ="";
		int upSol = 0;

		upSol = solSerInt.actualizarEstadoSolicitud_Servicio(Integer.parseInt(IN_ID_SOLICITUD_SERVICIO));
		
		if(upSol == 1)
			resultado = "ACTUALIZACION EXITOSA";
		else
			resultado = "ERROR AL ATENDER";
		
		return resultado;
	}
	
	@RequestMapping(value="/eliminarSolicitud",method=RequestMethod.POST)
	@ResponseBody
	public String eliminarSolicitud(String IN_ID_SOLICITUD_SERVICIO,ModelMap model) {
		
		String resultado="";
		int elimSol = 0;
		elimSol = solSerInt.eliminarSolicitud_Servicio(Integer.parseInt(IN_ID_SOLICITUD_SERVICIO));
		if(elimSol == 1) {
			resultado="ELIMINACION EXITOSA";
		}
		return resultado;
	}
	
	@RequestMapping(value="/registrar",method=RequestMethod.GET)
	public String registrar(ModelMap model) {
		String tipo = usuInt.obtenerTipoUsuarioXUser(Session.getCurrentInstance().getLoggedUser());
		model.addAttribute("Tipo",tipo);
		return "registrarSolicitud";
	}
	
	@RequestMapping(value="/registrarSolicitud",method=RequestMethod.POST)
	@ResponseBody
	public String registrarSolicitud(String direccion,String distrito,String provincia,String departamento,/*String usuario,*/ModelMap model) {
		int resulRegisSol = -1;
		int codUsuario=0;
		String resultadoFinal="";
		
//		codUsuario = usuInt.obtenerIdUsuarioXUser(usuario);
		codUsuario = usuInt.obtenerIdUsuarioXUser(Session.getCurrentInstance().getLoggedUser());
		if(codUsuario!=0) {
			Solicitud_Servicio obj = new Solicitud_Servicio();
			obj.setVC_DIRECCION(direccion);
			obj.setVC_DISTRITO(distrito);
			obj.setVC_PROVINCIA(provincia);
			obj.setVC_DEPARTAMENTO(departamento);
			obj.setIN_ID_USUARIO(codUsuario);
			
			resulRegisSol = solSerInt.registrarSolicitud_Servicio(obj);
			if(resulRegisSol != 2) {
				resultadoFinal ="REGISTRO EXITOSO";
			}else {
				resultadoFinal="LA DIRECCION YA HA SIDO REGISTRADA CON ESTE DISTRITO, PROVINCIA Y DEPARTAMENTO";
			}
		}
		else {
			resultadoFinal ="EL USUARIO NO EXISTE";
		}
//		System.out.println("Usuario: " + Session.getCurrentInstance().getLoggedUser());
//		System.out.println("CodUsuario: " + codUsuario);
		return resultadoFinal;
	}
}
