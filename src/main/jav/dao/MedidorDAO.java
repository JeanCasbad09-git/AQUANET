package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import conexion.Conexion;
import entities.Boleta;
import entities.Medidor;
import interfaces.MedidorInterfaces;

public class MedidorDAO implements MedidorInterfaces{

	@Override
	public int registrarMedidor(String obj) {
		int resultado=-1;
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		CallableStatement cbsm = null;
		try {	
				cn= conexion.conectar();
				
				String sql="call SP_REGISTRO_MEDIDOR (?)";
				cbsm = cn.prepareCall(sql);
				cbsm.setString(1, obj);
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
	public int validarExistenciaMedidor(String DNI) {
		int resultado=-1;
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
	try {
		cn = conexion.conectar();
		String sql2 = "SELECT M.IN_ID_MEDIDOR,P.VC_NOMBRE,M.DO_CONSUMO_ACTUAL FROM MEDIDOR M JOIN PERSONA P ON M.IN_ID_PERSONA = P.IN_ID_PERSONA WHERE VC_DNI = ?";
		pstm= cn.prepareStatement(sql2);
		pstm.setString(1, DNI);
		rs=pstm.executeQuery();
		while(rs.next()) {
			resultado = 1;

		}
		
	} catch (Exception e) {
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
	public ArrayList<Medidor> listarxDNI(String DNI) {
		ArrayList<Medidor> med = new ArrayList<Medidor>();
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn = conexion.conectar();
			String sql = "SELECT M.IN_ID_MEDIDOR,P.VC_NOMBRE,M.DO_CONSUMO_ACTUAL FROM MEDIDOR M JOIN PERSONA P ON M.IN_ID_PERSONA = P.IN_ID_PERSONA WHERE VC_DNI = ?";
			pstm=cn.prepareStatement(sql);
			 pstm.setString(1, DNI);
			 Medidor obj = new Medidor();
			 rs=pstm.executeQuery();
				while(rs.next()) {
					obj = new Medidor();
					obj.getPersona().setVC_NOMBRE(rs.getString(2));
					obj.setDO_CONSUMO_ACTUAL(rs.getDouble(3));
					med.add(obj);

				}
			
			
		} catch (Exception e) {
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
		return med;
	}

	@Override
	public int actualizarMedidor(Medidor IN_ID_MEDIDOR) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int eliminarMedidor(int IN_ID_MEDIDOR) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Medidor> listado(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Medidor BuscarID(String VC_DNI) {
		Medidor med = new Medidor();
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		cn = conexion.conectar();
		try {
			
			String sql="SELECT M.IN_ID_MEDIDOR,P.VC_NOMBRE,M.DO_CONSUMO_ACTUAL \r\n"
					+ "FROM MEDIDOR M JOIN PERSONA P ON M.IN_ID_PERSONA = P.IN_ID_PERSONA WHERE VC_DNI = ?";
			pstm=cn.prepareStatement(sql);
			 pstm.setString(1, VC_DNI);
			rs=pstm.executeQuery();
			
			while(rs.next()) {
				med =new Medidor();
				med.setIN_ID_MEDIDOR(rs.getInt(1));
				med.getPersona().setVC_NOMBRE(rs.getString(2));;
				med.setDO_CONSUMO_ACTUAL(rs.getDouble(3));
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
		return med;
	}
	

}
