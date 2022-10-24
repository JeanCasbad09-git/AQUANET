package entities;

import java.sql.Date;

import lombok.Data;

@Data
public class Reclamos {
	private int IN_ID_RECLAMO;
	private int IN_ID_USUARIO;
	private String usuarioCreador;
	private String VC_ESTADO;
	private String VC_RECLAMO;
	private Date DT_FECHA;

}
