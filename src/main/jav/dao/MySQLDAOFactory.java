package dao;

import interfaces.UsuarioInterface;

public class MySQLDAOFactory extends DAOFactory{

	@Override
	public UsuarioInterface getUsuarioInterface() {
		// TODO Auto-generated method stub
		return new UsuarioDAO();
	}

	

}
