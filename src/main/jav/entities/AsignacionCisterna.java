package entities;


import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AsignacionCisterna {

	private int IN_ID_ASIG_CISTERNA;
	private int IN_ID_CISTERNA;
	private int IN_ID_CHOFER;
	private int IN_ID_AGUATERO ;
	private String DT_FEC_ASIGNADO;
	private Date DT_FEC_REGISTRO;
	private String VC_PARADA_1;
	private String VC_PARADA_2;
	private String VC_PARADA_3;
}
