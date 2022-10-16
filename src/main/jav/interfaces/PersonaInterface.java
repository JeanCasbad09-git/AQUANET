package interfaces;

import entities.Persona;

public interface PersonaInterface {
	public int registrarPersona(Persona obj);
	public int obtenerUltimoCodigo();
	public String validarExistencia(Persona obj);
	public int eliminarPersona(int IN_ID_PERSONA);
	public int actualizarPersona(Persona obj);
}
