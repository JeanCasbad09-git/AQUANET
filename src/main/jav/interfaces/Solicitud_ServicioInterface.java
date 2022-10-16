package interfaces;
import java.util.ArrayList;

import entities.Solicitud_Servicio;

public interface Solicitud_ServicioInterface {
	public int registrarSolicitud_Servicio(Solicitud_Servicio obj);
	public int actualizarEstadoSolicitud_Servicio(int IN_ID_SOLICITUD_SERVICIO);
	public int eliminarSolicitud_Servicio(int IN_ID_SOLICITUD_SERVICIO);
	public ArrayList<Solicitud_Servicio> listado(Solicitud_Servicio usuario);
}
