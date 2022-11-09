package entities;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Distrito {
	private String VC_DISTRITO;

	public String getVC_DISTRITO() {
		return VC_DISTRITO;
	}

	public void setVC_DISTRITO(String vC_DISTRITO) {
		VC_DISTRITO = vC_DISTRITO;
	}
}
