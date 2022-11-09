package interfaces;

import java.util.ArrayList;


import entities.Medidor;

public interface MedidorInterfaces {
	public int registrarMedidor(String obj);
	public int actualizarMedidor(Medidor IN_ID_MEDIDOR);
	public int eliminarMedidor(int IN_ID_MEDIDOR);
	public ArrayList<Medidor> listado(String query);
	public int validarExistenciaMedidor(String DNI);
	public Medidor BuscarID(String VC_DNI);
	public ArrayList<Medidor> listarxDNI(String DNI);
}
