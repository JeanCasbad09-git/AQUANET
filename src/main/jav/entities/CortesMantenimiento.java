package entities;

import java.sql.Date;
import lombok.Data;

@Data
public class CortesMantenimiento {	
	private int IN_ID_CORTXMAN;
	private Date DT_FECHA;
	private String VC_DISTRITO;
	private String VC_PROVINCIA;
	private String VC_DEPARTAMENTO;
	private String VC_COMENTARIO;
}