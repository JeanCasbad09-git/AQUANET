package dao;

import interfaces.PersonaInterface;
import interfaces.Solicitud_ServicioInterface;
import interfaces.UsuarioInterface;

public class MySQLDAOFactory extends DAOFactory{

	@Override
	public UsuarioInterface getUsuarioInterface() {
		// TODO Auto-generated method stub
		return new UsuarioDAO();
	}

	@Override
	public PersonaInterface getPersonaInterface() {
		// TODO Auto-generated method stub
		return new PersonaDAO();
	}

	@Override
	public Solicitud_ServicioInterface getSolicitud_ServicioInterface() {
		// TODO Auto-generated method stub
		return new Solicitud_ServicioDAO();
	}

	

}
