package entities;

import java.util.Date;

import lombok.Data;

@Data
public class Boleta {

	
	private int IN_ID_BOLETA;
	private int IN_ID_MEDIDOR;
	private Double DO_CONSUMO;
	private Double DO_TARIFA;
	private Double DO_MONTO;
	private Date DT_FECHAEMIS;
	private Date DT_FECHAVENC;
	private Date DT_FECHACANCE;
	
	private Medidor medidor = new Medidor();
}
