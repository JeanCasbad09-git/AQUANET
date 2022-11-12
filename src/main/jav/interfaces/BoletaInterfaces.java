package interfaces;

import java.util.ArrayList;


import entities.Boleta;

public interface BoletaInterfaces {
	public int registrarBoleta(String COD_MEDIDOR, Double MONTO);
	public int actualizarBoleta(Boleta IN_ID_BOLETA);
	public int eliminarBoleta(int IN_ID_BOLETA);
	public ArrayList<Boleta> listado(String query);
	public Boleta BuscarID(int IN_ID_MEDIDOR);
}
