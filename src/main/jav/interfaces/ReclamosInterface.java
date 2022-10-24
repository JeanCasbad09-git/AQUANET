package interfaces;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import entities.Reclamos;

public interface ReclamosInterface {
	public int createReclamos(Reclamos obj);
	public int updateReclamosCliente(Reclamos obj);
	public int updateReclamosAdmin(Reclamos obj);
	public int deleteReclamos(int IN_ID_RECLAMO);
	public ArrayList<Reclamos> listReclamos(String query);
	Reclamos getReclamosById(int IN_ID_RECLAMO);
	ByteArrayOutputStream exportAllData() throws IOException;
}
