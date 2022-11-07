package com.ecodeup.mvc;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dao.DAOFactory;
import entities.Reclamos;
import entities.Session;
import interfaces.ReclamosInterface;
import interfaces.UsuarioInterface;

@Controller
@RequestMapping("/reclamos")
public class ReclamosController {
	ReclamosInterface reInt;
	DAOFactory daoFactorya=DAOFactory.getDAOFactory(DAOFactory.MYSQL8);
	UsuarioInterface usuInt=daoFactorya.getUsuarioInterface();
	public ReclamosController() {
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL8);
		this.reInt = daoFactory.getReclamosInterface();
	}
	
	@RequestMapping(value="/listado", method=RequestMethod.GET)
	public String mostrarListaReclamoss(Model model) {
		String query = "";
		List<Reclamos> reclamos = reInt.listReclamos(query);	
		System.out.println(reclamos);
		model.addAttribute("reclamos", reclamos);
		String tipo = usuInt.obtenerTipoUsuarioXUser(Session.getCurrentInstance().getLoggedUser());
		model.addAttribute("Tipo",tipo);
		return "listadoReclamos";
	}
	
	@RequestMapping(value="/crear", method=RequestMethod.GET)
	public String mostrarCreateReclamos(Model model, @RequestParam(required = false) String errorMessage) {
		String tipo = usuInt.obtenerTipoUsuarioXUser(Session.getCurrentInstance().getLoggedUser());
		model.addAttribute("Tipo",tipo);
		model.addAttribute("message", errorMessage);
		return "registraReclamos";
	}
	
	
	@RequestMapping(value="/crear", method=RequestMethod.POST)
	public String createReclamos(@ModelAttribute("reclamo") Reclamos reclamo) {
		if (reclamo.getVC_RECLAMO().isEmpty()) {
			return "redirect:/reclamos/crear?errorMessage=" + "No se puede crear un reclamo vacio";	
		}
		
		reInt.createReclamos(reclamo);
		return "redirect:/reclamos/listado";
	}
	
	@RequestMapping(value="/visualizar/{id}", method=RequestMethod.GET)
	public String mostrarVisualizarReclamo(@PathVariable("id") int id, Model model) {
		Reclamos reclamos = reInt.getReclamosById(id); 
		model.addAttribute("reclamos", reclamos);
		String tipo = usuInt.obtenerTipoUsuarioXUser(Session.getCurrentInstance().getLoggedUser());
		model.addAttribute("Tipo",tipo);
		return "visualizarReclamos";
	}

	@RequestMapping(value="/export", method=RequestMethod.GET)
	public ResponseEntity<byte[]> exportAllData() throws Exception {
		String filename = "Reclamos.xls";
		ByteArrayOutputStream stream = reInt.exportAllData();
		
        HttpHeaders header = new HttpHeaders();
        header.set(HttpHeaders.CONTENT_TYPE, "application/vnd.ms-excel");
        header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);
		
		return ResponseEntity.ok()
				.headers(header)
				.body(stream.toByteArray());
	}
	
	@RequestMapping(value="/eliminar/{id}", method=RequestMethod.GET)
    public String deleteReclamos(@PathVariable("id") int id) {
        reInt.deleteReclamos(id);      
        return "redirect:/reclamos/listado";
    }
}
