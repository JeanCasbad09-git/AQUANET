package interfaces;

import java.util.ArrayList;

import entities.CortesMantenimiento;

public interface CortesMantenimientoInterface {
	public int createCorte_Mantenimiento(CortesMantenimiento obj);
	public int updateCorte_Mantenimiento(CortesMantenimiento obj);
	public int deleteCorte_Mantenimiento(int IN_ID_CORTXMAN);
	public ArrayList<CortesMantenimiento> listCorte(String query);
	CortesMantenimiento getCorteById(int IN_ID_CORTXMAN);

}
