package com.ecodeup.mvc;

import java.io.Console;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.DAOFactory;
import entities.AsignacionCisterna;
import entities.Cargos;
import entities.Cisterna;
import entities.Trabajador;
import interfaces.AsignacionCisternaInterfaces;
import interfaces.CocheCisternaInterfaces;
import interfaces.TrabajadorInterfaces;
import interfaces.UtilidadesInterfaces;

@Controller
@RequestMapping("/home")
public class CrudCisternaController {

	DAOFactory daoFactory=DAOFactory.getDAOFactory(DAOFactory.MYSQL8);
	
	AsignacionCisternaInterfaces asignacion = daoFactory.getAsignacionCisternaInterfaces();
	
	TrabajadorInterfaces trabajadorInterfaces = daoFactory.getTrabajadorInterfaces();
	
	CocheCisternaInterfaces cisternaInterfaces = daoFactory.getCisternaInterfaces();
	
	
	@RequestMapping(value="/CrudCisterna",method=RequestMethod.GET)
	public String verDolar(ModelMap model) {
		String query="";
		List<Cisterna> listcisterna = cisternaInterfaces.cboCisterna(query);
		System.out.println(listcisterna);
		model.addAttribute("listcisterna", listcisterna);
		List<Trabajador> listChofer = trabajadorInterfaces.ListarChoferes(query);
		System.out.println(listChofer);
		model.addAttribute("listChofer", listChofer);
		List<Trabajador> listAguatero = trabajadorInterfaces.ListarAguateros(query);
		System.out.println(listAguatero);
		model.addAttribute("listAguatero", listAguatero);
		return "CrudCisterna";
	}
	
	@RequestMapping(value="/AsigneCisterna",method=RequestMethod.POST)
	@ResponseBody
	public String registrarDolar(String parada1,String parada2,String parada3,String fecha,int id_chofer,int id_aguatero,int id_cisterna,ModelMap model) {
		
		
		String resultado = "";
		int resulDolar = -1;
		AsignacionCisterna cisterna = new AsignacionCisterna();

		cisterna.setDT_FEC_ASIGNADO(fecha);
		cisterna.setVC_PARADA_1(parada1);
		cisterna.setVC_PARADA_2(parada2);
		cisterna.setVC_PARADA_3(parada3);
		cisterna.setIN_ID_CHOFER(id_chofer);
		cisterna.setIN_ID_AGUATERO(id_aguatero);
		cisterna.setIN_ID_CISTERNA(id_cisterna);
		
		resulDolar = asignacion.registrarAsignacionCisterna(cisterna);
		if(resulDolar == 1) {
			resultado ="REGISTRO EXITOSO";
		}else {
			resultado ="REGISTRO ERRONEO";
		}
		return resultado;
	}

	
}
