package interfaces;

import java.util.ArrayList;


import entities.Medidor;

public interface MedidorInterfaces {
	public int registrarMedidor(String DNI,String Cod_Medidor);
	public int actualizarMedidor(Medidor IN_ID_MEDIDOR);
	public int eliminarMedidor(int IN_ID_MEDIDOR);
	
	public ArrayList<Medidor> listado(String query);
	public ArrayList<Medidor> listarxDNI(String DNI);
	
	public int validarExistenciaMedidorxDNI(String DNI);
	public int validarExistenciaMedidorxCodigo(String COD);
	public int validarExistenciaPersona(String DNI);
	
	public Medidor BuscarIDxDNI(String VC_DNI);
	public Medidor BuscarIDxCodMedidor(String VC_ID_MEDIDOR);
	
}
