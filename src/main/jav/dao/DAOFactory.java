package dao;

import interfaces.PersonaInterface;
import interfaces.Solicitud_ServicioInterface;
import interfaces.UsuarioInterface;

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
