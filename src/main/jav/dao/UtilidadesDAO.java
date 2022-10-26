package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conexion.Conexion;
import entities.Cargos;
import interfaces.UtilidadesInterfaces;

public class UtilidadesDAO implements UtilidadesInterfaces{

	@Override
	public List<Cargos> listaCargos(String query) {
		ArrayList<Cargos> list = new ArrayList<Cargos>();
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn = conexion.conectar();
			String sql = "select * from cargos";
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			Cargos obj = new Cargos();
			while(rs.next()) {
				obj = new Cargos();
				obj.setIN_COD_CARGO(rs.getInt(1));
				obj.setVC_DESCRIPCION_CARGO(rs.getString(2));
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
