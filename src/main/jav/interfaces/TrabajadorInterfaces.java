package interfaces;


import java.util.ArrayList;


import entities.Trabajador;

public interface TrabajadorInterfaces {

	public int registrarTrabajador(Trabajador obj);
	public int actualizarTrabajador(int IN_ID_TRABAJADOR);
	public int eliminarTrabajador(int IN_ID_TRABAJADOR);
	public ArrayList<Trabajador> listadoTrabajador(Trabajador trabajador);
}
