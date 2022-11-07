package com.ecodeup.mvc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dao.DAOFactory;
import entities.CortesMantenimiento;
import entities.Session;
import interfaces.CortesMantenimientoInterface;
import interfaces.UsuarioInterface;

@Controller
@RequestMapping("/cortes")
public class CortesMantenimientoController {
	
	CortesMantenimientoInterface corManInt;
	DAOFactory daoFactory2=DAOFactory.getDAOFactory(DAOFactory.MYSQL8);
	UsuarioInterface usuInt=daoFactory2.getUsuarioInterface();
	
	public CortesMantenimientoController() {
		DAOFactory daoFactory=DAOFactory.getDAOFactory(DAOFactory.MYSQL8);	
		this.corManInt = daoFactory.getCortesMantenimientoInterface();	
	}
	
	@RequestMapping(value="/listado", method=RequestMethod.GET)
	public String FiltrarListaCortesMantenimiento(Model model,@RequestParam(name = "name", required = false) String name) {
		List<CortesMantenimiento> cortes = corManInt.listCorte(name);		
		if (name == null || name == "") {	 
			cortes = corManInt.listCorte(name);
		}else {
			cortes = corManInt.Search(name);
		}
		
		model.addAttribute("name", name);
		model.addAttribute("cortes", cortes);
		String tipo = usuInt.obtenerTipoUsuarioXUser(Session.getCurrentInstance().getLoggedUser());
		model.addAttribute("Tipo",tipo);
		return "listarCortesMantenimiento";
	}
	
	@RequestMapping(value="/crear", method=RequestMethod.GET)
	public String mostrarcreateCortesMantenimiento(ModelMap model) {
		String tipo = usuInt.obtenerTipoUsuarioXUser(Session.getCurrentInstance().getLoggedUser());
		model.addAttribute("Tipo",tipo);
		return "registraCortesMantenimiento";
	}
	
	@RequestMapping(value="/crear", method=RequestMethod.POST)
	public String createCortesMantenimiento(@ModelAttribute("corte") CrearCortesMantenimientoBody corte) {
		Date d;
		
		try {
			d = new SimpleDateFormat("yyyy-MM-dd").parse(corte.getDT_FECHA());
		} catch (ParseException e) {
			return "redirect:/cortes/crear";	
		}
		
		CortesMantenimiento newCorte = new CortesMantenimiento();
		newCorte.setVC_COMENTARIO(corte.getVC_COMENTARIO());
		newCorte.setVC_DEPARTAMENTO(corte.getVC_DEPARTAMENTO());
		newCorte.setVC_DISTRITO(corte.getVC_DISTRITO());
		newCorte.setVC_PROVINCIA(corte.getVC_PROVINCIA());
		newCorte.setDT_FECHA(new java.sql.Date(d.getTime()));
		
		corManInt.createCorte_Mantenimiento(newCorte);
		return "redirect:/cortes/listado?name=";
	}
	
	@RequestMapping(value="/editar/{id}", method=RequestMethod.GET)
	public String mostrarEditarCortes(@PathVariable("id") int id, Model model) {
		CortesMantenimiento cortes = corManInt.getCorteById(id); 
		model.addAttribute("cortes", cortes);
		System.out.println(cortes);
		String tipo = usuInt.obtenerTipoUsuarioXUser(Session.getCurrentInstance().getLoggedUser());
		model.addAttribute("Tipo",tipo);
		return "actualizarCortesMantenimiento";
	}
	@RequestMapping(value="/editar/{id}", method=RequestMethod.POST)
	public String EditCortesMantenimiento(@PathVariable("id") int id, @ModelAttribute("corte") CrearCortesMantenimientoBody corte) {
		Date d;
		
		try {
			d = new SimpleDateFormat("yyyy-MM-dd").parse(corte.getDT_FECHA());
		} catch (ParseException e) {
			return "redirect:/cortes/listado?name=";	
		}
		
		CortesMantenimiento newCorte = new CortesMantenimiento();
		newCorte.setIN_ID_CORTXMAN(id);
		newCorte.setVC_COMENTARIO(corte.getVC_COMENTARIO());
		newCorte.setVC_DEPARTAMENTO(corte.getVC_DEPARTAMENTO());
		newCorte.setVC_DISTRITO(corte.getVC_DISTRITO());
		newCorte.setVC_PROVINCIA(corte.getVC_PROVINCIA());
		newCorte.setDT_FECHA(new java.sql.Date(d.getTime()));
		
		corManInt.updateCorte_Mantenimiento(newCorte);
		return "redirect:/cortes/listado?name=";
	}
	
	
	@RequestMapping(value="/eliminar/{id}", method=RequestMethod.GET)
    public String deleteCortesMantenimiento(@PathVariable("id") int id) {
        corManInt.deleteCorte_Mantenimiento(id);      
        return "redirect:/cortes/listado?name=";
    }
	
}
