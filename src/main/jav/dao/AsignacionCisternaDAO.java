package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexion.Conexion;
import entities.AsignacionCisterna;
import interfaces.AsignacionCisternaInterfaces;

public class AsignacionCisternaDAO implements AsignacionCisternaInterfaces{

	@Override
	public int registrarAsignacionCisterna(AsignacionCisterna obj) {
		int resultado = -1;
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			cn= conexion.conectar();
			String sql = "insert into ASIGNACION_CISTERNA VALUES(NULL,?,?,?,?,CURRENT_DATE(),?,?,?);";
			ps = cn.prepareStatement(sql);
		    ps.setInt(1, obj.getIN_ID_CISTERNA());
		    ps.setInt(2, obj.getIN_ID_CHOFER());
		    ps.setInt(3, obj.getIN_ID_AGUATERO());
		    ps.setString(4, obj.getDT_FEC_ASIGNADO());
		    ps.setString(5, obj.getVC_PARADA_1());
		    ps.setString(6, obj.getVC_PARADA_2());
		    ps.setString(7, obj.getVC_PARADA_3());
		    
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
	public int actualizarAsignacionCisterna(int IN_ID_ASIG_CISTERNA) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int eliminarAsignacionCisterna(int IN_ID_ASIG_CISTERNA) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<AsignacionCisterna> listado(AsignacionCisterna Asigcisterna) {
		// TODO Auto-generated method stub
		return null;
	}

}
