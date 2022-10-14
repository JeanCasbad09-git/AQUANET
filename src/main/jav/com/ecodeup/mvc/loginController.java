package com.ecodeup.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.DAOFactory;
import entities.Usuario;
import interfaces.UsuarioInterface;

@Controller
@RequestMapping("/home")
public class loginController {
	
/*-------------VARIABLES GLOBALES-------------*/
    
	//ESTABLECEMOS VARIABLE PARA EL TIPO DE LA BD
	DAOFactory daoFactory=DAOFactory.getDAOFactory(DAOFactory.MYSQL8);
	//INVOCAMOS A LA INTERFACE AlumnoDAO
	UsuarioInterface usuInt=daoFactory.getUsuarioInterface();
	/*-----------------------------------------------*/
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String verLogin(ModelMap model) {
		Usuario usu = new Usuario();
		usu = usuInt.listado();
		/*PRUEBA COMMIT*/
		model.addAttribute("mensaje",usu.getVC_USER() + "-"+usu.getVC_PASSWORD() + "-"+ usu.getIN_ID_USUARIO());
		return "login";
	}
}
