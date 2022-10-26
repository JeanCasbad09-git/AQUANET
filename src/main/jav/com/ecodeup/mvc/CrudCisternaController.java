package com.ecodeup.mvc;

import java.io.Console;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.DAOFactory;
import entities.AsignacionCisterna;
import interfaces.AsignacionCisternaInterfaces;

@Controller
@RequestMapping("/home")
public class CrudCisternaController {

	DAOFactory daoFactory=DAOFactory.getDAOFactory(DAOFactory.MYSQL8);
	
	AsignacionCisternaInterfaces asignacion = daoFactory.getAsignacionCisternaInterfaces();
	
	
	@RequestMapping(value="/CrudCisterna",method=RequestMethod.GET)
	public String verDolar(ModelMap model) {
		return "CrudCisterna";
	}
	
	@RequestMapping(value="/AsigneCisterna",method=RequestMethod.POST)
	@ResponseBody
	public String registrarDolar(String parada1,String parada2,String parada3,Date fecha,ModelMap model) {
		String resultado = "";
		int resulDolar = -1;
		AsignacionCisterna cisterna = new AsignacionCisterna();
		System.out.println(" "+cisterna);
		cisterna.setDT_FEC_ASIGNADO(fecha);
		cisterna.setVC_PARADA_1(parada1);
		cisterna.setVC_PARADA_2(parada2);
		cisterna.setVC_PARADA_3(parada3);
		cisterna.setIN_ID_CHOFER(1);
		cisterna.setIN_ID_AGUATERO(2);
		cisterna.setIN_ID_CISTERNA(1);
		
		resulDolar = asignacion.registrarAsignacionCisterna(cisterna);
		if(resulDolar == 1) {
			resultado ="REGISTRO EXITOSO";
		}else {
			resultado ="REGISTRO ERRONEO";
		}
		return resultado;
	}

	
}
