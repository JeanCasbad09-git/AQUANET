package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexion.Conexion;
import entities.AsignacionCisterna;
import entities.Cisterna;
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
	public ArrayList<AsignacionCisterna> listado(String query) {
		ArrayList<AsignacionCisterna> coxm = new ArrayList<AsignacionCisterna>();
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn = conexion.conectar();
			String sql= "SELECT ac.IN_ID_ASIG_CISTERNA, cc.VC_PLACA_COCHE,dt.VC_NOMBRES as chofer,dt.VC_NOMBRES as aguatero,ac.DT_FEC_ASIGNADO,ac.VC_PARADA_1,ac.VC_PARADA_2,ac.VC_PARADA_3\r\n"
					+ " FROM ASIGNACION_CISTERNA ac, COCHE_CISTERNA cc,DATOS_TRABAJADOR dt \r\n"
					+ " where ac.IN_ID_CISTERNA = cc.IN_ID_CISTERNA and  ac.IN_ID_CHOFER = dt.IN_ID_TRABAJADOR";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			AsignacionCisterna obj = new AsignacionCisterna();
			Cisterna cisterna = new Cisterna();
			while(rs.next()) {		
				obj=new AsignacionCisterna();
				obj.setIN_ID_CISTERNA(rs.getInt(1));
				obj.getCisterna().setVC_PLACA_COCHE(rs.getString(2));
				obj.getTrabajador().setVC_NOMBRES(rs.getString(3));
				obj.getTrabajador().setVC_NOMBRES(rs.getString(4));
				obj.setDT_FEC_ASIGNADO(rs.getString(5));
				obj.setVC_PARADA_1(rs.getString(6));
				obj.setVC_PARADA_2(rs.getString(7));
				obj.setVC_PARADA_3(rs.getString(8));
				coxm.add(obj);

			}
		}catch(Exception e) {
			System.out.println("ERROR EN LISTAR: "+e.getMessage());
		}finally {
			try {
				if(cn!=null)cn.close();
				if(pstm!=null)pstm.close();
				if(rs!=null)rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return coxm;

	}

}
