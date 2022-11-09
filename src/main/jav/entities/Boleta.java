package entities;

import lombok.Data;

@Data
public class Boleta {

	
	private int IN_ID_BOLETA;
	private int IN_ID_MEDIDOR;
	private Double DO_CONSUMO;
	private Double DO_TARIFA;
	private Double DO_MONTO;
	
	private Medidor medidor = new Medidor();
}
