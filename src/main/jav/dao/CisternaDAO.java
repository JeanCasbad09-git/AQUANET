package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexion.Conexion;
import entities.Cisterna;
import interfaces.CocheCisternaInterfaces;

public class CisternaDAO implements CocheCisternaInterfaces{

	@Override
	public int registrarCocheCisterna(Cisterna obj) {
		int resultado = -1;
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			cn= conexion.conectar();
			String sql = "insert into COCHE_CISTERNA VALUES(NULL,?,current_date(),?,?,?);";
			ps = cn.prepareStatement(sql);
		    ps.setString(1, obj.getVC_PLACA_COCHE());
		    ps.setDate(2, obj.getDT_FEC_MANTENIMIENTO());
		    ps.setString(3, obj.getVC_ESTADO_OPER_MALO());
		    ps.setString(4, obj.getVC_ESTADO_ASIG_LIBRE());
		    
		    resultado = ps.executeUpdate();					
		}catch(SQLException e) {
			System.out.print("ERROR AL REGISTRAR: "+e.getMessage());
		}finally {
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(cn!=null) cn.close();
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}		
		return resultado;
	}

	@Override
	public int actualizarCocheCisterna(int IN_ID_CISTERNA) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int eliminarCocheCisterna(int IN_ID_CISTERNA) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Cisterna> listadoCisterna(Cisterna trabajador) {
		// TODO Auto-generated method stub
		return null;
	}

}
