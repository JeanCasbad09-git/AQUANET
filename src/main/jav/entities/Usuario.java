package entities;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Usuario {
	private int IN_ID_USUARIO;
	private int IN_ID_PERSONA;
	private String VC_USER;
	private String VC_PASSWORD;
	private Date DT_FEC_REG;
	private String VC_NOMBRE;
	private String VC_DNI;
	private String VC_DIRECCION;
	private String VC_DISTRITO;
	private String VC_PROVINCIA;
	private String VC_DEPARTAMENTO;

}
