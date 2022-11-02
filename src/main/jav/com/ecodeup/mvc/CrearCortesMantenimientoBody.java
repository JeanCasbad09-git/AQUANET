package com.ecodeup.mvc;

import lombok.Data;

@Data
public class CrearCortesMantenimientoBody {
	private int IN_ID_CORTXMAN;
	private String VC_DISTRITO;
	private String VC_PROVINCIA;
	private String VC_DEPARTAMENTO;
	private String VC_COMENTARIO;
	private String DT_FECHA;
}
