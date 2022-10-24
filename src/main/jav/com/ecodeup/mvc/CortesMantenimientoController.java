package com.ecodeup.mvc;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import dao.DAOFactory;
import entities.CortesMantenimiento;
import interfaces.CortesMantenimientoInterface;

@Controller
@RequestMapping("/cortes")
public class CortesMantenimientoController {
	CortesMantenimientoInterface corManInt;
	
	
	public CortesMantenimientoController() {
		DAOFactory daoFactory=DAOFactory.getDAOFactory(DAOFactory.MYSQL8);	
		this.corManInt = daoFactory.getCortesMantenimientoInterface();	
	}
	
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String mostrarListaCortesMantenimiento(Model model) {
		String query = "";
		List<CortesMantenimiento> cortes = corManInt.listCorte(query);	
		System.out.println(cortes);
		model.addAttribute("cortes", cortes);
		return "listarCortesMantenimiento";
	}
	
	@RequestMapping(value="/crear", method=RequestMethod.GET)
	public String createCortesMantenimiento() {
		return "registraCortesMantenimiento";
	}
	
	/**@GetMapping("/cortes/editar/{id}")
	public String mostrarEditarCortes(@PathVariable("id") int id, Model model) {
		corManInt.getCorteById(id);
		if
		return "";
	}**/
	
	/**@PostMapping("/cortes/editar/{id}")
	public String EditCortesMantenimiento(@PathVariable("id") int id, @ModelAttribute("cortes") Corte_Mantenimiento corte)  {
		corte.setIN_ID_CORTXMAN(id);
		corManInt.updateCorte_Mantenimiento(id, corte);
		return "redirect:/cortes";
	} **/
	
	@RequestMapping(value="/editar/{id}", method=RequestMethod.GET)
	public String mostrarEditarCortes(@PathVariable("id") int id, Model model) {
		CortesMantenimiento cortes = corManInt.getCorteById(id); 
		model.addAttribute("cortes", cortes);
		return "actualizarCortesMantenimiento";
	}
	@PostMapping("/cortes/editar/{id}")
	public String EditCortesMantenimiento(@PathVariable("id") int id, @ModelAttribute("cortes") CortesMantenimiento corte) {
		
		
		return "redirect:/cortes";
	}
	
	
	@GetMapping("/cortes/eliminar/{id}")
    public String deleteCortesMantenimiento(@PathVariable("id") int id) {
        corManInt.deleteCorte_Mantenimiento(id);      
        return "redirect:/cortes";
    }
}
