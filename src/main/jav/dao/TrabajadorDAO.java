package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conexion.Conexion;
import entities.Cargos;
import entities.Trabajador;
import interfaces.TrabajadorInterfaces;

public class TrabajadorDAO implements TrabajadorInterfaces{

	@Override
	public int registrarTrabajador(Trabajador obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int actualizarTrabajador(int IN_ID_TRABAJADOR) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int eliminarTrabajador(int IN_ID_TRABAJADOR) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Trabajador> listadoTrabajador(Trabajador trabajador) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Trabajador> ListarChoferes(String query) {
		ArrayList<Trabajador> list = new ArrayList<Trabajador>();
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn = conexion.conectar();
			String sql = "SELECT X.IN_ID_TRABAJADOR, X.VC_NOMBRES FROM DATOS_TRABAJADOR X WHERE IN_CARGO = 1";
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			Trabajador obj = new Trabajador();
			while(rs.next()) {
				obj = new Trabajador();
				obj.setIN_ID_TRABAJADOR(rs.getInt(1));
				obj.setVC_NOMBRES(rs.getString(2));
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
	

	@Override
	public List<Trabajador> ListarAguateros(String query) {
		ArrayList<Trabajador> list = new ArrayList<Trabajador>();
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn = conexion.conectar();
			String sql = "SELECT X.IN_ID_TRABAJADOR, X.VC_NOMBRES FROM DATOS_TRABAJADOR X WHERE IN_CARGO = 2";
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			Trabajador obj = new Trabajador();
			while(rs.next()) {
				obj = new Trabajador();
				obj.setIN_ID_TRABAJADOR(rs.getInt(1));
				obj.setVC_NOMBRES(rs.getString(2));
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
