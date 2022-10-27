package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import conexion.Conexion;
import entities.Usuario;
import interfaces.UsuarioInterface;

public class UsuarioDAO implements UsuarioInterface{

	public ArrayList<Usuario> listado(Usuario usuario) {
		ArrayList<Usuario> usu=new ArrayList<Usuario>();
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn = conexion.conectar();
			String sql="";
			if(usuario.getVC_NOMBRE() != "" && usuario.getVC_USER()!="") {
				sql="select u.IN_ID_USUARIO,u.VC_USER,u.DT_FEC_REG,(select VC_NOMBRE from Persona p where p.IN_ID_PERSONA = u.IN_ID_PERSONA AND p.VC_ESTADO ='ACT') as 'Nombre' ,(select VC_DNI from Persona p where p.IN_ID_PERSONA = u.IN_ID_PERSONA AND p.VC_ESTADO ='ACT') as 'DNI',u.IN_ID_PERSONA from Usuario u WHERE u.VC_ESTADO ='ACT' AND ((select VC_NOMBRE from Persona p where p.IN_ID_PERSONA = u.IN_ID_PERSONA AND p.VC_ESTADO ='ACT') = ?) AND (VC_USER = ?)";
				pstm=cn.prepareStatement(sql);
				pstm.setString(1, usuario.getVC_NOMBRE());
	        	pstm.setString(2, usuario.getVC_USER());
			}else if(usuario.getVC_NOMBRE()!="") {
				sql="select u.IN_ID_USUARIO,u.VC_USER,u.DT_FEC_REG,(select VC_NOMBRE from Persona p where p.IN_ID_PERSONA = u.IN_ID_PERSONA AND p.VC_ESTADO ='ACT') as 'Nombre' ,(select VC_DNI from Persona p where p.IN_ID_PERSONA = u.IN_ID_PERSONA AND p.VC_ESTADO ='ACT') as 'DNI',u.IN_ID_PERSONA from Usuario u WHERE u.VC_ESTADO ='ACT' AND ((select VC_NOMBRE from Persona p where p.IN_ID_PERSONA = u.IN_ID_PERSONA AND p.VC_ESTADO ='ACT') = ?)";
				pstm=cn.prepareStatement(sql);
				pstm.setString(1, usuario.getVC_NOMBRE());
			}
			else if(usuario.getVC_USER()!="") {
				sql="select u.IN_ID_USUARIO,u.VC_USER,u.DT_FEC_REG,(select VC_NOMBRE from Persona p where p.IN_ID_PERSONA = u.IN_ID_PERSONA AND p.VC_ESTADO ='ACT') as 'Nombre' ,(select VC_DNI from Persona p where p.IN_ID_PERSONA = u.IN_ID_PERSONA AND p.VC_ESTADO ='ACT') as 'DNI',u.IN_ID_PERSONA from Usuario u WHERE u.VC_ESTADO ='ACT' AND (VC_USER = ?)";
				pstm=cn.prepareStatement(sql);
	        	pstm.setString(1, usuario.getVC_USER());
			}
			else {
				sql="select u.IN_ID_USUARIO,u.VC_USER,u.DT_FEC_REG,(select VC_NOMBRE from Persona p where p.IN_ID_PERSONA = u.IN_ID_PERSONA AND p.VC_ESTADO ='ACT') as 'Nombre' ,(select VC_DNI from Persona p where p.IN_ID_PERSONA = u.IN_ID_PERSONA AND p.VC_ESTADO ='ACT') as 'DNI',u.IN_ID_PERSONA from Usuario u WHERE u.VC_ESTADO ='ACT'";
				pstm=cn.prepareStatement(sql);
			}
			rs=pstm.executeQuery();
			Usuario obj = new Usuario();
			while(rs.next()) {
				obj=new Usuario();
				obj.setIN_ID_USUARIO(rs.getInt(1));
				obj.setVC_USER(rs.getString(2));
				obj.setDT_FEC_REG(rs.getDate(3));
				obj.setVC_NOMBRE(rs.getString(4));
				obj.setVC_DNI(rs.getString(5));
				obj.setIN_ID_PERSONA(rs.getInt(6));
				usu.add(obj);
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

	@Override
	public String verificarCredenciales(String user, String password) {
		String existe="NO";
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		cn = conexion.conectar();
		try {
			
			String sql="select*from usuario WHERE VC_USER = ? AND VC_PASSWORD = ? ";
			pstm=cn.prepareStatement(sql);
			 pstm.setString(1, user);
	        pstm.setString(2, password);
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

	@SuppressWarnings("resource")
	@Override
	public int registrarUsuario(Usuario obj) {
		int resultado=-1;
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn = conexion.conectar();

			String sql="insert into usuario(IN_ID_PERSONA,VC_USER,VC_PASSWORD,DT_FEC_REG,VC_ESTADO) values (?,?,?,now(),'ACT')";
			pstm=cn.prepareStatement(sql);
			pstm.setInt(1, obj.getIN_ID_PERSONA());
            pstm.setString(2, obj.getVC_USER());
            pstm.setString(3, obj.getVC_PASSWORD());
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
	public String validacionExistenciaUsuario(String user) {
		String resultado="0";
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn = conexion.conectar();
			String sql ="select VC_PASSWORD from usuario where VC_USER =?";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1, user);
			rs=pstm.executeQuery();
			
			while(rs.next()) {
				resultado = rs.getString(1);
			}

			
		}catch(Exception e) {
			System.out.println("ERROR AL VALIDAR: "+e.getMessage());
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
	public int eliminarUsuario(int IN_ID_USUARIO) {
		int resultado=-1;
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn = conexion.conectar();

			String sql="update usuario set VC_ESTADO = 'ELIM' where IN_ID_USUARIO=?";
			pstm=cn.prepareStatement(sql);
			pstm.setInt(1, IN_ID_USUARIO);

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
	public Usuario obtenerUsuario(int IN_ID_USUARIO, int IN_ID_PERSONA) {
		Usuario usu= new Usuario();
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		cn = conexion.conectar();
		try {
			
			String sql="select u.IN_ID_USUARIO,u.VC_USER,u.DT_FEC_REG,(select VC_NOMBRE from Persona p where p.IN_ID_PERSONA = u.IN_ID_PERSONA AND p.VC_ESTADO ='ACT') as 'Nombre' ,(select VC_DNI from Persona p where p.IN_ID_PERSONA = u.IN_ID_PERSONA AND p.VC_ESTADO ='ACT') as 'DNI',u.IN_ID_PERSONA,u.VC_PASSWORD,(select VC_DIRECCION from Persona p where p.IN_ID_PERSONA = u.IN_ID_PERSONA AND p.VC_ESTADO ='ACT') as 'Direccion',(select VC_DISTRITO from Persona p where p.IN_ID_PERSONA = u.IN_ID_PERSONA AND p.VC_ESTADO ='ACT') as 'Distrito',(select VC_PROVINCIA from Persona p where p.IN_ID_PERSONA = u.IN_ID_PERSONA AND p.VC_ESTADO ='ACT') as 'Provincia',(select VC_DEPARTAMENTO from Persona p where p.IN_ID_PERSONA = u.IN_ID_PERSONA AND p.VC_ESTADO ='ACT') as 'Departamento' from Usuario u WHERE u.VC_ESTADO ='ACT' AND IN_ID_USUARIO = ? AND IN_ID_PERSONA = ?";
			pstm=cn.prepareStatement(sql);
			 pstm.setInt(1, IN_ID_USUARIO);
	        pstm.setInt(2, IN_ID_PERSONA);
			rs=pstm.executeQuery();
			
			while(rs.next()) {
				usu=new Usuario();
				usu.setIN_ID_USUARIO(rs.getInt(1));
				usu.setVC_USER(rs.getString(2));
				usu.setDT_FEC_REG(rs.getDate(3));
				usu.setVC_NOMBRE(rs.getString(4));
				usu.setVC_DNI(rs.getString(5));
				usu.setIN_ID_PERSONA(rs.getInt(6));
				usu.setVC_PASSWORD(rs.getString(7));
				usu.setVC_DIRECCION(rs.getString(8));
				usu.setVC_DISTRITO(rs.getString(9));
				usu.setVC_PROVINCIA(rs.getString(10));
				usu.setVC_DEPARTAMENTO(rs.getString(11));
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
		return usu;
	}

	@Override
	public int actualizarUsuario(Usuario obj) {
		int resultado=-1;
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn = conexion.conectar();

			String sql="update Usuario set VC_USER = ? , VC_PASSWORD = ? where IN_ID_USUARIO = ?";
			pstm=cn.prepareStatement(sql);
			
            pstm.setString(1, obj.getVC_USER());
            pstm.setString(2, obj.getVC_PASSWORD());
            pstm.setInt(3, obj.getIN_ID_USUARIO());
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
	
	public int obtenerIdUsuarioXUser(String user) {
		int codigo=0;
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn = conexion.conectar();
			String sql="select IN_ID_USUARIO from usuario where VC_ESTADO NOT IN ('ELIM') AND VC_USER =? order by 1 desc limit 1";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1, user);
			rs=pstm.executeQuery();
			
			while(rs.next()) {
				codigo = rs.getInt(1);

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
		return codigo;
	}

	@Override
	public String obtenerTipoUsuarioXUser(String user) {
		String tipo="";
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn = conexion.conectar();
			String sql="SELECT (select VC_TIPO from persona p where p.IN_ID_PERSONA=u.IN_ID_PERSONA) as 'TIPO' FROM usuario u where u.VC_USER=? order by 1 desc limit 1";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1, user);
			rs=pstm.executeQuery();
			
			while(rs.next()) {
				tipo = rs.getString(1);

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
		return tipo;
	}
	
}
