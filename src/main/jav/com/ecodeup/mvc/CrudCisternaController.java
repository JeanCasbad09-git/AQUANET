package com.ecodeup.mvc;

import java.io.Console;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.DAOFactory;
import entities.AsignacionCisterna;
import entities.Cargos;
import entities.Cisterna;
import entities.Session;
import entities.Trabajador;
import interfaces.AsignacionCisternaInterfaces;
import interfaces.CocheCisternaInterfaces;
import interfaces.Solicitud_ServicioInterface;
import interfaces.TrabajadorInterfaces;
import interfaces.UsuarioInterface;
import interfaces.UtilidadesInterfaces;

@Controller
@RequestMapping("/cisterna")
public class CrudCisternaController {

	DAOFactory daoFactory=DAOFactory.getDAOFactory(DAOFactory.MYSQL8);
	
	AsignacionCisternaInterfaces asignacion = daoFactory.getAsignacionCisternaInterfaces();
	
	TrabajadorInterfaces trabajadorInterfaces = daoFactory.getTrabajadorInterfaces();
	
	CocheCisternaInterfaces cisternaInterfaces = daoFactory.getCisternaInterfaces();
	UsuarioInterface usuInt=daoFactory.getUsuarioInterface();
	Solicitud_ServicioInterface solSerInt = daoFactory.getSolicitud_ServicioInterface();
	
	@RequestMapping(value="/CrudCisterna",method=RequestMethod.GET)
	public String verRegistraCisterna(ModelMap model) {
		String tipo = usuInt.obtenerTipoUsuarioXUser(Session.getCurrentInstance().getLoggedUser());
		model.addAttribute("Tipo",tipo);
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
		return "RegistrarCisterna";
	}
	
	@RequestMapping(value="/AsigneCisterna",method=RequestMethod.POST)
	@ResponseBody
	public String registrarCisterna(String parada1,String parada2,String parada3,String fecha,int id_chofer,int id_aguatero,int id_cisterna,ModelMap model) {
		
		
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

	@RequestMapping(value="/ListaAsgCisterna",method=RequestMethod.GET)
	public String verListaAsgCisterna(ModelMap model) {
		String tipo = usuInt.obtenerTipoUsuarioXUser(Session.getCurrentInstance().getLoggedUser());
		model.addAttribute("Tipo",tipo);
		String query="";
		List<AsignacionCisterna> listAsgCisterna = asignacion.listado(query);
		System.out.println(listAsgCisterna);
		model.addAttribute("listAsgCisterna", listAsgCisterna);
		return "listarAsigacionCisterna";
	}
	@RequestMapping(value="/cisternaActualizar", method=RequestMethod.GET)
	public String ActualizarCisterna(String parametros, Model model) {
		AsignacionCisterna asg = asignacion.BuscarID(Integer.parseInt(parametros)); 
		String query="";
		model.addAttribute("asignacion", asg);
		System.out.println(asg);
		List<Cisterna> listcisterna = cisternaInterfaces.cboCisterna(query);
		System.out.println(listcisterna);
		model.addAttribute("listcisterna", listcisterna);
		List<Trabajador> listChofer = trabajadorInterfaces.ListarChoferes(query);
		System.out.println(listChofer);
		model.addAttribute("listChofer", listChofer);
		List<Trabajador> listAguatero = trabajadorInterfaces.ListarAguateros(query);
		System.out.println(listAguatero);
		model.addAttribute("listAguatero", listAguatero);
		return "ActualizarAsignacionCisterna";
	}
	
	@RequestMapping(value="/actualizarCisterna", method=RequestMethod.POST)
	@ResponseBody
	public String editarAsgCisterna(int id_asg_cisterna,String parada1,String parada2,String parada3,String fecha,int id_chofer,int id_aguatero,int id_cisterna,ModelMap model) {
		String resultado ="";
		int asg = -1;
		AsignacionCisterna newAsig = new AsignacionCisterna();
		newAsig.setIN_ID_ASIG_CISTERNA(id_asg_cisterna);
		newAsig.setIN_ID_CISTERNA(id_cisterna);
		newAsig.setIN_ID_CHOFER(id_chofer);
		newAsig.setIN_ID_AGUATERO(id_aguatero);
		newAsig.setDT_FEC_ASIGNADO(fecha);
		newAsig.setVC_PARADA_1(parada1);
		newAsig.setVC_PARADA_2(parada2);
		newAsig.setVC_PARADA_3(parada3);
		
		asg = asignacion.actualizarAsignacionCisterna(newAsig);
		
		if(asg == 1) {
			resultado = "ERROR AL ACTUALIZAR";
		}else {
			resultado = "ACTUALIZACION EXITOSA";
		}
		return resultado;
	}

	@RequestMapping(value="/eliminarCisterna/{id}", method=RequestMethod.GET)
	 public String EliminarAsgCisterna(@PathVariable("id") int id) {
        asignacion.eliminarAsignacionCisterna(id);      
        return "redirect:/cisterna/ListaAsgCisterna";
    }
}
