package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import conexion.Conexion;
import entities.Usuario;
import interfaces.UsuarioInterface;

public class UsuarioDAO implements UsuarioInterface{

	public Usuario listado() {
		Usuario usu=new Usuario();
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn = conexion.conectar();
			String sql="select*from Usuario";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			
			while(rs.next()) {
				usu=new Usuario();
				usu.setIN_ID_USUARIO(rs.getInt(1));
				usu.setVC_USER(rs.getString(3));
				usu.setVC_PASSWORD(rs.getString(4));

			}
			
		}catch(Exception e) {
			System.out.println("ERROR EN LISTAR: "+e.getMessage());
		}finally {
			try {
				if(cn!=null)cn.close();
				if(pstm!=null)pstm.close();
				if(rs!=null)rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return usu;
	}

}
