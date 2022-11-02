package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexion.Conexion;
import entities.Cisterna;
import entities.Trabajador;
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

	@Override
	public List<Cisterna> cboCisterna(String query) {
		ArrayList<Cisterna> list = new ArrayList<Cisterna>();
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn = conexion.conectar();
			String sql = "SELECT C.IN_ID_CISTERNA, C.VC_PLACA_COCHE  FROM COCHE_CISTERNA C WHERE VC_ESTADO_OPER_MALO=\"OPERATIVO\" AND VC_ESTADO_ASIG_LIBRE=\"LIBRE\"";
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			Cisterna obj = new Cisterna();
			while(rs.next()) {
				obj = new Cisterna();
				obj.setIN_ID_CISTERNA(rs.getInt(1));
				obj.setVC_PLACA_COCHE(rs.getString(2));
				list.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(cn!=null)cn.close();
				if(pstm!=null)pstm.close();
				if(rs!=null)rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return list;
	}

}
