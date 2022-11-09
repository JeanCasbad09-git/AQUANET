package com.ecodeup.mvc;




import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.DAOFactory;
import entities.Medidor;
import interfaces.BoletaInterfaces;
import interfaces.MedidorInterfaces;

@Controller
@RequestMapping("/boleta")
public class BoletaController {

	DAOFactory daoFactory=DAOFactory.getDAOFactory(DAOFactory.MYSQL8);
	
	BoletaInterfaces boletaInterfaces = daoFactory.getBoletaInterface();
	
	MedidorInterfaces medidorInterfaces = daoFactory.getMedidorInterfaces();
	
	
	@RequestMapping(value="/BuscarBoleta",method=RequestMethod.GET)
	public String verLogin(ModelMap model) {
		return "BuscarBoleta";
	}
	
	@RequestMapping(value="/validarMedidor", method=RequestMethod.POST)
	@ResponseBody
	public String validarMedidor(String DNI, Model model) {
		String resultado ="";
		int medidor = -1;
		
		medidor = medidorInterfaces.validarExistenciaMedidor(DNI);
	
		if(medidor == 1) {
			resultado = "MEDIDOR ENCONTRADO";
		}else {
			resultado = "NO TIENE MEDIDOR";
		}
		return resultado;
	}
	
	@RequestMapping(value="/registrarMedidor", method=RequestMethod.POST)
	@ResponseBody
	public String RegistrarMedidor(String DNI, Model model) {
		String resultado ="";
		int medidor = -1;
		
		medidor = medidorInterfaces.registrarMedidor(DNI);
	
		if(medidor == 1) {
			resultado = "MEDIDOR REGISTRADO";
		}else {
			resultado = "ERROR: VERIFICAR DNI";
		}
		return resultado;
	}
	
	@RequestMapping(value="/RegistrarBoleta", method=RequestMethod.GET)
	public String verRegistroBoleta(String parametros, Model model) {
		Medidor asg = medidorInterfaces.BuscarID(parametros); 
		model.addAttribute("listMedidor", asg);
		System.out.println(asg);
		return "RegistrarBoleta";
	}
	
	@RequestMapping(value="/RistroBoleta",method=RequestMethod.POST)
	@ResponseBody
	public String registrarBoleta(int id_medidor,Double consumo,ModelMap model) {
		String resultado = "";
		int resulBol = -1;
	
		resulBol = boletaInterfaces.registrarBoleta(id_medidor,consumo);
		if(resulBol == 1) {
			resultado ="REGISTRO EXITOSO";
		}else {
			resultado ="REGISTRO ERRONEO";
		}
		return resultado;
	}

	
}
