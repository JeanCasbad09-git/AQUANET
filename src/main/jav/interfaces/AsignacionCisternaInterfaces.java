package interfaces;

import java.util.ArrayList;

import entities.AsignacionCisterna;
import entities.Trabajador;

public interface AsignacionCisternaInterfaces {

	public int registrarAsignacionCisterna(AsignacionCisterna obj);
	public int actualizarAsignacionCisterna(int IN_ID_ASIG_CISTERNA);
	public int eliminarAsignacionCisterna(int IN_ID_ASIG_CISTERNA);
	public ArrayList<AsignacionCisterna> listado(String query);
}
