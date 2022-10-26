package interfaces;
import java.util.ArrayList;

import entities.Sede;


public interface SedeInterface {
	public int registrarSede(Sede obj);
	public int actualizarEstadoSede(int IN_ID_SEDE);
	public int eliminarSede(int IN_ID_SEDE);
	public ArrayList<Sede> listado(Sede usuario);
}
