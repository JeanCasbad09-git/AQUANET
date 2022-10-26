package dao;

import interfaces.AsignacionCisternaInterfaces;
import interfaces.CocheCisternaInterfaces;
import interfaces.CortesMantenimientoInterface;
import interfaces.PersonaInterface;
import interfaces.ReclamosInterface;
import interfaces.Solicitud_ServicioInterface;
import interfaces.TrabajadorInterfaces;
import interfaces.UsuarioInterface;
import interfaces.UtilidadesInterfaces;

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

	@Override
	public CortesMantenimientoInterface getCortesMantenimientoInterface() {

		return new CortesMantenimientoDAO();
	}

	@Override
	public ReclamosInterface getReclamosInterface() {
		// TODO Auto-generated method stub
		return new ReclamosDAO();
	}
	@Override
	public UtilidadesInterfaces getUtilidadesInterface() {

		return new UtilidadesDAO();
	}
	
	@Override
	public AsignacionCisternaInterfaces getAsignacionCisternaInterfaces() {

		return new AsignacionCisternaDAO();
	}
	
	@Override
	public TrabajadorInterfaces getTrabajadorInterfaces() {

		return new TrabajadorDAO();
	}
	@Override
	public CocheCisternaInterfaces getCisternaInterfaces() {

		return new CisternaDAO();
	}


}
	


