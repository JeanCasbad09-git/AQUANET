package entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Solicitud_Servicio {
	private int IN_ID_SOLICITUD_SERVICIO;
	private int IN_ID_USUARIO;
	private String VC_DIRECCION;
	private String VC_DISTRITO;
	private String VC_PROVINCIA;
	private String VC_DEPARTAMENTO;
	private String VC_USER;
	private String VC_ESTADO;
}
