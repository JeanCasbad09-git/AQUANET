package interfaces;

import java.util.ArrayList;

import entities.Usuario;

public interface UsuarioInterface {
	public String verificarCredenciales(String user, String password);
	public int registrarUsuario(Usuario obj);
	public String validacionExistenciaUsuario(String user);
	public ArrayList<Usuario> listado(Usuario usuario);
	public int eliminarUsuario(int IN_ID_USUARIO);
	public Usuario obtenerUsuario(int IN_ID_USUARIO,int IN_ID_PERSONA);
	public int actualizarUsuario(Usuario obj);
	public int obtenerIdUsuarioXUser(String user);
	public String obtenerTipoUsuarioXUser(String user);
}
