package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexion.Conexion;
import entities.Distrito;
import entities.Sede;
import interfaces.DistritoInterface;

public class DistritoDAO implements DistritoInterface {

	@Override
	public ArrayList<Distrito> listado(String query) {
		ArrayList<Distrito> coxm = new ArrayList<Distrito>();
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn = conexion.conectar();
			String sql= "SELECT VC_SEDE AS DISTRITO FROM SEDE";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			Distrito obj = new Distrito();
			while(rs.next()) {		
				obj=new Distrito();
				obj.setVC_DISTRITO(rs.getString(1));
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
