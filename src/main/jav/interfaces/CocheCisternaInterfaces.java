package interfaces;

import java.util.ArrayList;

import entities.Cisterna;


public interface CocheCisternaInterfaces {
	public int registrarCocheCisterna(Cisterna obj);
	public int actualizarCocheCisterna(int IN_ID_CISTERNA);
	public int eliminarCocheCisterna(int IN_ID_CISTERNA);
	public ArrayList<Cisterna> listadoCisterna(Cisterna trabajador);
}
