package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import conexion.Conexion;
import entities.Persona;
import interfaces.PersonaInterface;

public class PersonaDAO implements PersonaInterface{

	@SuppressWarnings("resource")
	@Override
	public int registrarPersona(Persona obj) {
		int resultado=-1;
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn = conexion.conectar();
			String sql2 ="select * from Persona where VC_NOMBRE =? or VC_DNI=?";
			pstm=cn.prepareStatement(sql2);
			pstm.setString(1, obj.getVC_NOMBRE());
        	pstm.setString(2, obj.getVC_DNI());
			rs=pstm.executeQuery();
			
			while(rs.next()) {
				resultado = 2;

			}
			if(resultado != 2) {
				String sql="insert into Persona(VC_NOMBRE,VC_DNI,VC_ESTADO,DT_FEC_REG,VC_TIPO,VC_DIRECCION,VC_DISTRITO,VC_PROVINCIA,VC_DEPARTAMENTO) values (?,?,'ACT',now(),'CLI',?,?,?,?)";
				pstm=cn.prepareStatement(sql);
				pstm.setString(1, obj.getVC_NOMBRE());
            	pstm.setString(2, obj.getVC_DNI());
            	pstm.setString(3, obj.getVC_DIRECCION());
            	pstm.setString(4, obj.getVC_DISTRITO());
            	pstm.setString(5, obj.getVC_PROVINCIA());
            	pstm.setString(6, obj.getVC_DEPARTAMENTO());
            	resultado=pstm.executeUpdate();
			}
			
		}catch(Exception e) {
			System.out.println("ERROR AL REGISTRAR: "+e.getMessage());
		}finally {
			try {
				if(cn!=null)cn.close();
				if(pstm!=null)pstm.close();
				if(rs!=null)rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return resultado;
	}

	@Override
	public int obtenerUltimoCodigo() {
		int codigo=0;
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn = conexion.conectar();
			String sql="select IN_ID_PERSONA from Persona order by 1 desc limit 1";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			
			while(rs.next()) {
				codigo = rs.getInt(1);

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
		return codigo;
	}

	@Override
	public String validarExistencia(Persona obj) {
		String existe ="NO";
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn = conexion.conectar();
			String sql="select IN_ID_PERSONA from Persona where VC_NOMBRE =? and VC_DNI=?  order by 1 desc limit 1";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1, obj.getVC_NOMBRE());
			pstm.setString(2, obj.getVC_DNI());
			rs=pstm.executeQuery();
			
			while(rs.next()) {
				existe = "SI";

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
		return existe;
	}

	@Override
	public int eliminarPersona(int IN_ID_PERSONA) {
		int resultado=-1;
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn = conexion.conectar();

			String sql="update Persona set VC_ESTADO = 'ELIM' where IN_ID_PERSONA=?";
			pstm=cn.prepareStatement(sql);
			pstm.setInt(1, IN_ID_PERSONA);

            resultado=pstm.executeUpdate();
			
			
		}catch(Exception e) {
			System.out.println("ERROR AL REGISTRAR: "+e.getMessage());
		}finally {
			try {
				if(cn!=null)cn.close();
				if(pstm!=null)pstm.close();
				if(rs!=null)rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return resultado;
	}

	@Override
	public int actualizarPersona(Persona obj) {
		int resultado=-1;
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn = conexion.conectar();

			String sql="update Persona set VC_NOMBRE = ? , VC_DNI = ?,DT_FEC_MOD=now(),VC_DIRECCION=?,VC_DISTRITO=?,VC_PROVINCIA=?,VC_DEPARTAMENTO=? where IN_ID_PERSONA = ?";
			pstm=cn.prepareStatement(sql);
			
            pstm.setString(1, obj.getVC_NOMBRE());
            pstm.setString(2, obj.getVC_DNI());
            pstm.setString(3, obj.getVC_DIRECCION());
            pstm.setString(4, obj.getVC_DISTRITO());
            pstm.setString(5, obj.getVC_PROVINCIA());
            pstm.setString(6, obj.getVC_DEPARTAMENTO());
            pstm.setInt(7, obj.getIN_ID_PERSONA());
            resultado=pstm.executeUpdate();
			
			
		}catch(Exception e) {
			System.out.println("ERROR AL ACTUALIZAR: "+e.getMessage());
		}finally {
			try {
				if(cn!=null)cn.close();
				if(pstm!=null)pstm.close();
				if(rs!=null)rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return resultado;
	}

}
