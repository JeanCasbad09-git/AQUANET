package com.ecodeup.mvc;




import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.DAOFactory;
import entities.AsignacionCisterna;
import entities.Boleta;
import entities.Medidor;
import entities.Session;
import interfaces.BoletaInterfaces;
import interfaces.MedidorInterfaces;
import interfaces.UsuarioInterface;

@Controller
@RequestMapping("/boleta")
public class BoletaController {

	DAOFactory daoFactory=DAOFactory.getDAOFactory(DAOFactory.MYSQL8);
	
	BoletaInterfaces boletaInterfaces = daoFactory.getBoletaInterface();
	
	UsuarioInterface usuInt=daoFactory.getUsuarioInterface();
	
	MedidorInterfaces medidorInterfaces = daoFactory.getMedidorInterfaces();
	
	
	@RequestMapping(value="/BuscarBoleta",method=RequestMethod.GET)
	public String verBuscarBoleta(ModelMap model) {
		String tipo = usuInt.obtenerTipoUsuarioXUser(Session.getCurrentInstance().getLoggedUser());
		model.addAttribute("Tipo",tipo);
		return "BuscarBoleta";
	}
	
	@RequestMapping(value="/validarMedidorxDNI", method=RequestMethod.POST)
	@ResponseBody
	public String validarMedidorxDNI(String DNI, Model model) {
		String resultado ="";
		int medidor = -1;
		
		medidor = medidorInterfaces.validarExistenciaMedidorxDNI(DNI);
	
		if(medidor == 1) {
			resultado = "MEDIDOR ENCONTRADO";
		}else {
			resultado = "NO TIENE MEDIDOR";
		}
		return resultado;
	}
	
	@RequestMapping(value="/validarMedidorXcod", method=RequestMethod.POST)
	@ResponseBody
	public String validarMedidorXcod(String COD, Model model) {
		String resultado ="";
		int medidor = -1;
		
		medidor = medidorInterfaces.validarExistenciaMedidorxCodigo(COD);
	
		if(medidor == 1) {
			resultado = "MEDIDOR ENCONTRADO";
		}else {
			resultado = "MEDIDOR NO EXISTE";
		}
		return resultado;
	}
	
	@RequestMapping(value="/registrarMedidor", method=RequestMethod.POST)
	@ResponseBody
	public String RegistrarMedidor(String DNI,String CodMed, Model model) {
		String resultado ="";
		int DniMedidor = -1;
		int medidor = -1;
		
		DniMedidor = medidorInterfaces.validarExistenciaPersona(DNI);
		medidor = medidorInterfaces.registrarMedidor(DNI,CodMed);
	
		if(DniMedidor == 1) {
		if(medidor == 1) {
			resultado = "MEDIDOR REGISTRADO";
		}else {
			resultado = "CODIGO YA REGISTRADO";
		}
		}else {
			resultado = "DNI NO ENCONTRADO";
		}
		return resultado;
	}
	
	@RequestMapping(value="/RegistrarBoletaxDNI", method=RequestMethod.GET)
	public String verRegistroBoletaxDNI(String parametros, Model model) {
		Medidor asg = medidorInterfaces.BuscarIDxDNI(parametros); 
		model.addAttribute("listMedidor", asg);
		System.out.println(asg);
		return "RegistrarBoleta";
	}
	@RequestMapping(value="/RegistrarBoletaxCOD", method=RequestMethod.GET)
	public String verRegistroBoletaxCOD(String parametros, Model model) {
		Medidor asg = medidorInterfaces.BuscarIDxCodMedidor(parametros); 
		model.addAttribute("listMedidor", asg);
		System.out.println(asg);
		return "RegistrarBoleta";
	}
	
	@RequestMapping(value="/RistroBoleta",method=RequestMethod.POST)
	@ResponseBody
	public String registrarBoleta(String id_medidor,Double consumo,ModelMap model) {
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
	
	@RequestMapping(value="/ListaBoletaUsuario",method=RequestMethod.GET)
	public String verListaBoletaxUsuario(ModelMap model) {
		String tipo = usuInt.obtenerTipoUsuarioXUser(Session.getCurrentInstance().getLoggedUser());
		model.addAttribute("Tipo",tipo);
		int codigo = usuInt.obtenerIdUsuarioXUser(Session.getCurrentInstance().getLoggedUser());
		String query="";
		List<Boleta> listBolUsuario = boletaInterfaces.listadoxUsuario(codigo,query);
		System.out.println(listBolUsuario);
		model.addAttribute("listBolUsuario", listBolUsuario);
		return "ListarBoletaXusuario";
	}
	
	@RequestMapping(value="/ListaBoletaADM", method=RequestMethod.GET)
	public String verListaBoletasADM(Model model) {
		String tipo = usuInt.obtenerTipoUsuarioXUser(Session.getCurrentInstance().getLoggedUser());
		model.addAttribute("Tipo",tipo);
		String query="";
		List<Boleta> listBolUsuario = boletaInterfaces.listado(query);
		model.addAttribute("listBolUsuario", listBolUsuario);
		System.out.println(listBolUsuario);
		return "ListarBoletaADM";
	}
	@RequestMapping(value="/ListaBoletaDNI", method=RequestMethod.GET)
	public String verListaBoletasDNI_ADM(String parametros, Model model) {
		String tipo = usuInt.obtenerTipoUsuarioXUser(Session.getCurrentInstance().getLoggedUser());
		model.addAttribute("Tipo",tipo);
		String query="";
		List<Boleta> listBolUsuario = boletaInterfaces.listadoxDNI(parametros,query);
		model.addAttribute("listBolUsuario", listBolUsuario);
		System.out.println(listBolUsuario);
		return "ListarBoletaADM";
	}
	
	@RequestMapping(value="/pagarBoleta", method=RequestMethod.POST)
	@ResponseBody
	public String pagarBoleta(int ID_BOLETA,ModelMap model) {
		String resultado ="";
		int bol = -1;

		bol = boletaInterfaces.actualizarPagoBoleta(ID_BOLETA);
		
		if(bol == 1) {
			resultado = "PAGO EXITOSO";
		}else {
			resultado = "ERROR AL PAGAR";
		}
		return resultado;
	}
	
	@RequestMapping(value="/EliminarBoleta", method=RequestMethod.POST)
	@ResponseBody
	public String eliminarBoleta(int ID_BOLETA,ModelMap model) {
		String resultado ="";
		int bol = -1;

		bol = boletaInterfaces.eliminarBoleta(ID_BOLETA);
		
		if(bol == 1) {
			resultado = "ELIMINACION EXITOSA";
		}else {
			resultado = "ERROR AL ELIMINAR";
		}
		return resultado;
	}


	
}
