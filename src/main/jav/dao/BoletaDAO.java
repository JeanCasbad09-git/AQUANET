package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import conexion.Conexion;
import entities.Boleta;
import interfaces.BoletaInterfaces;

public class BoletaDAO implements BoletaInterfaces {

	@Override
	public int registrarBoleta(String COD_MEDIDOR, Double MONTO) {
		int resultado=-1;
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		CallableStatement cbsm = null;
		try {	
				cn= conexion.conectar();
				
				String sql="CALL SP_INSERTAR_BOLETA(?,?)";
				cbsm = cn.prepareCall(sql);
				cbsm.setString(1, COD_MEDIDOR);
				cbsm.setDouble(2, MONTO);
				resultado = cbsm.executeUpdate();
				
			
		} catch (Exception e) {
			System.out.println("Error al registrar" + e.getMessage());
			e.printStackTrace();
		}finally {
			try {
				if(cn!=null)cn.close();
				if(pstm!=null)pstm.close();
				if(rs!=null)rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}	
		
		return resultado;
	}

	@Override
	public int eliminarBoleta(int IN_ID_BOLETA) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int actualizarBoleta(Boleta obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Boleta> listado(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boleta BuscarID(int IN_ID_BOLETA) {
		// TODO Auto-generated method stub
		return null;
	}

}
