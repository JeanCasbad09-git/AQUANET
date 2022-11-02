package com.ecodeup.mvc;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.DAOFactory;
import entities.Sede;

import interfaces.SedeInterface;

import interfaces.UsuarioInterface;

@Controller
@RequestMapping("/sede")
public class SedeController {
	DAOFactory daoFactory=DAOFactory.getDAOFactory(DAOFactory.MYSQL8);

	UsuarioInterface usuInt=daoFactory.getUsuarioInterface();
	SedeInterface sedeInt = daoFactory.getSedeInterface();
	
	@RequestMapping(value="/sedeAct",method=RequestMethod.GET)
	public String verListSede(String parametros, ModelMap model) {
		
		ArrayList<Sede> listado=new ArrayList<Sede>();
		Sede obj = new Sede();
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
		
		listado = sedeInt.listado(obj);
		model.addAttribute("Solicitud",listado);

		return "listadoSede";
	}
	
	@RequestMapping(value="/actualizarSede",method=RequestMethod.POST)
	@ResponseBody
	public String updateSede(String IN_ID_SEDE,ModelMap model) {
		String resultado ="";
		int upSed = 0;

		upSed = sedeInt.actualizarEstadoSede(Integer.parseInt(IN_ID_SEDE));
		
		if(upSed == 1)
			resultado = "ACTUALIZACION EXITOSA";
		else
			resultado = "ERROR AL ATENDER";
		
		return resultado;
	}
	
	@RequestMapping(value="/eliminarSede",method=RequestMethod.POST)
	@ResponseBody
	public String eliminarSede(String IN_ID_SEDE,ModelMap model) {
		
		String resultado="";
		int elimSed = 0;
		elimSed = sedeInt.eliminarSede(Integer.parseInt(IN_ID_SEDE));
		if(elimSed == 1) {
			resultado="ELIMINACION EXITOSA";
		}
		return resultado;
	}
	
	@RequestMapping(value="/registrar",method=RequestMethod.GET)
	public String registrar(ModelMap model) {
		return "registrarSede";
	}
	
	@RequestMapping(value="/registrarSede",method=RequestMethod.POST)
	@ResponseBody
	public String registrarSolicitud(String sede,String distrito,String provincia,String departamento,String usuario,ModelMap model) {
		int resulRegisSed = -1;
		int codUsuario=0;
		String resultadoFinal="";
		
		codUsuario = usuInt.obtenerIdUsuarioXUser(usuario);
		if(codUsuario!=0) {
			Sede obj = new Sede();
			obj.setVC_SEDE(sede);
			obj.setVC_DISTRITO(distrito);
			obj.setVC_PROVINCIA(provincia);
			obj.setVC_DEPARTAMENTO(departamento);
			obj.setIN_ID_USUARIO(codUsuario);
			
			resulRegisSed = sedeInt.registrarSede(obj);
			if(resulRegisSed != 2) {
				resultadoFinal ="REGISTRO EXITOSO";
			}else {
				resultadoFinal="LA SEDE YA HA SIDO REGISTRADA CON ESTE DISTRITO, PROVINCIA Y DEPARTAMENTO";
			}
		}
		else {
			resultadoFinal ="EL USUARIO NO EXISTE";
		}
		return resultadoFinal;
	}
}
