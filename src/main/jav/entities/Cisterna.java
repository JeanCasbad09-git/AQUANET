package entities;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cisterna {

	private int IN_ID_CISTERNA;
	private String VC_PLACA_COCHE;
	private Date DT_FEC_REGISTRO;
	private Date DT_FEC_MANTENIMIENTO;
	private String VC_ESTADO_OPER_MALO;
	private String VC_ESTADO_ASIG_LIBRE;
	
}
