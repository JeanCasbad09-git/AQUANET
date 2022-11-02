package dao;

import interfaces.AsignacionCisternaInterfaces;
import interfaces.CocheCisternaInterfaces;
import interfaces.CortesMantenimientoInterface;
import interfaces.PersonaInterface;
import interfaces.ReclamosInterface;
import interfaces.SedeInterface;
import interfaces.Solicitud_ServicioInterface;
import interfaces.TrabajadorInterfaces;
import interfaces.UsuarioInterface;
import interfaces.UtilidadesInterfaces;

public abstract class DAOFactory {
	
	//VALOR POR DEFECTO SEGÚN LA BD
	public static final int MYSQL = 1;
	public static final int SQLSERVER = 2;
	public static final int MYSQL8 = 3;
	public static final int ORACLE = 4;
	
	//MÉTODO PARA ACCEDER A LAS INTERFAES 
	public abstract UsuarioInterface getUsuarioInterface();
	public abstract PersonaInterface getPersonaInterface();
	public abstract Solicitud_ServicioInterface getSolicitud_ServicioInterface();
	public abstract CortesMantenimientoInterface getCortesMantenimientoInterface();
	public abstract ReclamosInterface getReclamosInterface();
	public abstract UtilidadesInterfaces getUtilidadesInterface();
	public abstract AsignacionCisternaInterfaces getAsignacionCisternaInterfaces();
	public abstract TrabajadorInterfaces getTrabajadorInterfaces();
	public abstract CocheCisternaInterfaces getCisternaInterfaces();
	public abstract SedeInterface getSedeInterface();
	//MÉTODO QUE GENERA LOS GET DE LAS BD
	public static DAOFactory getDAOFactory(int db) {
		switch(db) {
		case MYSQL8:
			return new MySQLDAOFactory();
		default:
			return null;
		}
	}
}
