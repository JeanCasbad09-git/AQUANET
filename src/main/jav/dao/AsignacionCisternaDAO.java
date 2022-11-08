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
	public int actualizarAsignacionCisterna(AsignacionCisterna obj) {
		int resultado =-1;
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;		
		try {
			cn = conexion.conectar();
			
			String sql = "UPDATE ASIGNACION_CISTERNA SET IN_ID_CISTERNA = ?,IN_ID_CHOFER = ?,IN_ID_AGUATERO = ?,DT_FEC_ASIGNADO = ?,DT_FEC_REGISTRO = CURDATE(),VC_PARADA_1 = ?,VC_PARADA_2 = ?,VC_PARADA_3 = ? \r\n"
					+ "WHERE IN_ID_ASIG_CISTERNA = ?;";
			pstm=cn.prepareStatement(sql);
			pstm.setInt(1, obj.getIN_ID_CISTERNA());
			pstm.setInt(2, obj.getIN_ID_CHOFER());
			pstm.setInt(3, obj.getIN_ID_AGUATERO());
			pstm.setString(4, obj.getDT_FEC_ASIGNADO());
			pstm.setString(5, obj.getVC_PARADA_1());
			pstm.setString(6, obj.getVC_PARADA_2());
			pstm.setString(7, obj.getVC_PARADA_3());
			pstm.setInt(8, obj.getIN_ID_ASIG_CISTERNA());
			pstm.execute();
		} catch (Exception e) {
			System.out.println("ERROR AL ACTUALIZAR: "+e.getMessage());
		}finally {
			try {
				if(cn!=null)cn.close();
				if(pstm!=null)pstm.close();
				if(rs!=null)rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		return resultado;
	}

	@Override
	public int eliminarAsignacionCisterna(int IN_ID_ASIG_CISTERNA) {
		int result =-1;
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;		
		try {
			cn = conexion.conectar();
			
			String sql = "DELETE FROM ASIGNACION_CISTERNA WHERE IN_ID_ASIG_CISTERNA = ?;";
			pstm=cn.prepareStatement(sql);
			pstm.setInt(1, IN_ID_ASIG_CISTERNA);
			pstm.execute();

		} catch (Exception e) {
			System.out.println("ERROR AL ELIMINAR: "+e.getMessage());
		}finally {
			try {
				if(cn!=null)cn.close();
				if(pstm!=null)pstm.close();
				if(rs!=null)rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		return result;
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
					+ " where ac.IN_ID_CISTERNA = cc.IN_ID_CISTERNA and  ac.IN_ID_CHOFER = dt.IN_ID_TRABAJADOR;";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			AsignacionCisterna obj = new AsignacionCisterna();
			while(rs.next()) {		
				obj=new AsignacionCisterna();
				obj.setIN_ID_ASIG_CISTERNA(rs.getInt(1));
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

	@Override
	public AsignacionCisterna BuscarID(int IN_ID_ASIG_CISTERNA) {
		AsignacionCisterna asg= new AsignacionCisterna();
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		cn = conexion.conectar();
		try {
			
			String sql="SELECT ac.IN_ID_ASIG_CISTERNA, cc.VC_PLACA_COCHE,dt.VC_NOMBRES as chofer,dt.VC_NOMBRES as aguatero,ac.DT_FEC_ASIGNADO,ac.VC_PARADA_1,ac.VC_PARADA_2,ac.VC_PARADA_3\r\n"
					+ " FROM ASIGNACION_CISTERNA ac, COCHE_CISTERNA cc,DATOS_TRABAJADOR dt \r\n"
					+ " where ac.IN_ID_CISTERNA = cc.IN_ID_CISTERNA and  ac.IN_ID_CHOFER = dt.IN_ID_TRABAJADOR and IN_ID_ASIG_CISTERNA = ?";
			pstm=cn.prepareStatement(sql);
			 pstm.setInt(1, IN_ID_ASIG_CISTERNA);
			rs=pstm.executeQuery();
			
			while(rs.next()) {
				asg =new AsignacionCisterna();
				asg.setIN_ID_ASIG_CISTERNA(rs.getInt(1));
				asg.getCisterna().setVC_PLACA_COCHE(rs.getString(2));
				asg.getTrabajador().setVC_NOMBRES(rs.getString(3));
				asg.getTrabajador().setVC_NOMBRES(rs.getString(4));
				asg.setDT_FEC_ASIGNADO(rs.getString(5));
				asg.setVC_PARADA_1(rs.getString(6));
				asg.setVC_PARADA_2(rs.getString(7));
				asg.setVC_PARADA_3(rs.getString(8));
			}
		}catch(Exception e) {
			System.out.println("ERROR EN BUSCAR: "+e.getMessage());
		}finally {
			try {
				if(cn!=null)cn.close();
				if(pstm!=null)pstm.close();
				if(rs!=null)rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return asg;
	}

}
