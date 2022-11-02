package interfaces;


import java.util.ArrayList;
import java.util.List;

import entities.Trabajador;

public interface TrabajadorInterfaces {

	public int registrarTrabajador(Trabajador obj);
	public int actualizarTrabajador(int IN_ID_TRABAJADOR);
	public int eliminarTrabajador(int IN_ID_TRABAJADOR);
	public ArrayList<Trabajador> listadoTrabajador(Trabajador trabajador);
	public List<Trabajador> ListarChoferes(String query);
	public List<Trabajador> ListarAguateros(String query);
}
