package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
				if(cbsm!=null) cbsm.close();
				if(rs!=null)rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}	
		
		return resultado;
	}

	@Override
	public int eliminarBoleta(int IN_ID_BOLETA) {
		int resultado = -1;
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		CallableStatement cbsm = null;
		try {
			cn= conexion.conectar();
			String sql = "CALL SP_ELIMINAR_BOLETA(?);";
			cbsm = cn.prepareCall(sql);
			cbsm.setInt(1, IN_ID_BOLETA);
			resultado = cbsm.executeUpdate();			
		}catch(SQLException e) {
			System.out.print("ERROR AL ELIMINAR: "+e.getMessage());
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstm!=null) pstm.close();
				if(cbsm!=null) cbsm.close();
				if(cn!=null) cn.close();
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}		
		return resultado;
	}

	@Override
	public int actualizarPagoBoleta(int ID_BOLETA) {
		int resultado = -1;
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			cn= conexion.conectar();
			String sql = "UPDATE BOLETA SET DT_FECHACANCE = CURDATE() WHERE IN_ID_BOLETA = ?;";
			ps = cn.prepareStatement(sql);
		    ps.setInt(1, ID_BOLETA);	    
		    resultado = ps.executeUpdate();					
		}catch(SQLException e) {
			System.out.print("ERROR AL ACTUALIZAR: "+e.getMessage());
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
	public ArrayList<Boleta> listado(String query) {
		ArrayList<Boleta> bol = new ArrayList<Boleta>();
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn = conexion.conectar();
			String sql= " SELECT B.IN_ID_BOLETA,M.VC_ID_MEDIDOR, B.DO_CONSUMO,M.DO_CONSUMO_ACTUAL,B.DO_MONTO, B.DT_FECHAVENC\r\n"
					+ " FROM BOLETA B JOIN MEDIDOR M ON B.VC_ID_MEDIDOR = M.VC_ID_MEDIDOR JOIN PERSONA P ON P.IN_ID_PERSONA = M.IN_ID_PERSONA WHERE DT_FECHACANCE IS NULL; ";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			Boleta obj = new Boleta();
			while(rs.next()) {		
				obj=new Boleta();
				obj.setIN_ID_BOLETA(rs.getInt(1));
				obj.getMedidor().setVC_ID_MEDIDOR(rs.getString(2));
				obj.setDO_CONSUMO(rs.getDouble(3));
				obj.getMedidor().setDO_CONSUMO_ACTUAL(rs.getDouble(4));
				obj.setDO_MONTO(rs.getDouble(5));
				obj.setDT_FECHAVENC(rs.getDate(6));
				bol.add(obj);

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
		return bol;
	}

	@Override
	public ArrayList<Boleta> listadoxUsuario(int Codigo,String query) {
		ArrayList<Boleta> bol = new ArrayList<Boleta>();
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn = conexion.conectar();
			String sql= "SELECT B.IN_ID_BOLETA,M.VC_ID_MEDIDOR, B.DO_CONSUMO,M.DO_CONSUMO_ACTUAL,B.DO_MONTO, B.DT_FECHAVENC\r\n"
					+ " FROM BOLETA B JOIN MEDIDOR M ON B.VC_ID_MEDIDOR = M.VC_ID_MEDIDOR JOIN PERSONA P ON P.IN_ID_PERSONA = M.IN_ID_PERSONA WHERE P.IN_ID_PERSONA = ? AND DT_FECHACANCE IS NULL";
			pstm=cn.prepareStatement(sql);
			pstm.setInt(1, Codigo);
			rs=pstm.executeQuery();
			Boleta obj = new Boleta();
			while(rs.next()) {		
				obj=new Boleta();
				obj.setIN_ID_BOLETA(rs.getInt(1));
				obj.getMedidor().setVC_ID_MEDIDOR(rs.getString(2));
				obj.setDO_CONSUMO(rs.getDouble(3));
				obj.getMedidor().setDO_CONSUMO_ACTUAL(rs.getDouble(4));
				obj.setDO_MONTO(rs.getDouble(5));
				obj.setDT_FECHAVENC(rs.getDate(6));
				bol.add(obj);

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
		return bol;
	}
	
	@Override
	public ArrayList<Boleta> listadoxDNI(String VC_DNI,String query) {
		ArrayList<Boleta> bol = new ArrayList<Boleta>();
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn = conexion.conectar();
			String sql= " SELECT B.IN_ID_BOLETA,M.VC_ID_MEDIDOR, B.DO_CONSUMO,M.DO_CONSUMO_ACTUAL,B.DO_MONTO, B.DT_FECHAVENC\r\n"
					+ " FROM BOLETA B JOIN MEDIDOR M ON B.VC_ID_MEDIDOR = M.VC_ID_MEDIDOR JOIN PERSONA P ON P.IN_ID_PERSONA = M.IN_ID_PERSONA WHERE P.VC_DNI = ? AND DT_FECHACANCE IS NULL; ";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1, VC_DNI);
			rs=pstm.executeQuery();
			Boleta obj = new Boleta();
			while(rs.next()) {		
				obj=new Boleta();
				obj.setIN_ID_BOLETA(rs.getInt(1));
				obj.getMedidor().setVC_ID_MEDIDOR(rs.getString(2));
				obj.setDO_CONSUMO(rs.getDouble(3));
				obj.getMedidor().setDO_CONSUMO_ACTUAL(rs.getDouble(4));
				obj.setDO_MONTO(rs.getDouble(5));
				obj.setDT_FECHAVENC(rs.getDate(6));
				bol.add(obj);

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
		return bol;
	}

	@Override
	public Boleta BuscarID(int IN_ID_BOLETA) {
		// TODO Auto-generated method stub
		return null;
	}

}
