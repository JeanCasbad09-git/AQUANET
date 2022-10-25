package com.ecodeup.mvc;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.DAOFactory;
import entities.Cargos;
import interfaces.UtilidadesInterfaces;

@Controller
@RequestMapping("/home")
public class UtilController {
	DAOFactory daoFactory=DAOFactory.getDAOFactory(DAOFactory.MYSQL8);
	
	UtilidadesInterfaces utilInterfaces = daoFactory.getUtilidadesInterface();
	
	
	
	
	@RequestMapping(value="/listaCargos")
	public List<Cargos> listaCliente() {
		return utilInterfaces.listaCargos();
	}

}
