package entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Usuario {
	private int IN_ID_USUARIO;
	private int VC_NOMBRE;
	private String VC_USER;
	private String VC_PASSWORD;
	private String VC_CARGO;
}
