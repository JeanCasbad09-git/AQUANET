package entities;

import java.util.Date;

import lombok.Data;

@Data 
public class Medidor {

	private String VC_ID_MEDIDOR;
	private int IN_ID_PERSONA;
	private Date DT_FEC_REGISTRO; 
	private Double DO_CONSUMO_ACTUAL;
	
	private Persona persona = new Persona();
	
}
