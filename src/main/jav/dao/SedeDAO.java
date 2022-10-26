package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import conexion.Conexion;
import entities.Sede;

import interfaces.SedeInterface;


public class SedeDAO implements SedeInterface{

	@SuppressWarnings("resource")
	@Override
	public int registrarSede(Sede obj) {
		int resultado=-1;
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn = conexion.conectar();
			String sql2 ="select * from SEDE where VC_ESTADO not in('ELIM') AND VC_SEDE =? AND VC_DISTRITO=? AND VC_PROVINCIA=? AND VC_DEPARTAMENTO=?";
			pstm=cn.prepareStatement(sql2);
			pstm.setString(1, obj.getVC_SEDE());
			pstm.setString(2, obj.getVC_DISTRITO());
			pstm.setString(3, obj.getVC_PROVINCIA());
			pstm.setString(4, obj.getVC_DEPARTAMENTO());
			rs=pstm.executeQuery();
			
			while(rs.next()) {
				resultado = 2;
			}
			if(resultado != 2) {
				String sql="insert into SEDE(IN_ID_USUARIO,VC_ESTADO,VC_SEDE,VC_DISTRITO,VC_PROVINCIA,VC_DEPARTAMENTO) values (?,'PEN',?,?,?,?)";
				pstm=cn.prepareStatement(sql);
				pstm.setInt(1, obj.getIN_ID_USUARIO());
            	pstm.setString(2, obj.getVC_SEDE());
            	pstm.setString(3, obj.getVC_DISTRITO());
            	pstm.setString(4, obj.getVC_PROVINCIA());
            	pstm.setString(5, obj.getVC_DEPARTAMENTO());
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
	public int actualizarEstadoSede(int IN_ID_SEDE) {
		int resultado=-1;
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn = conexion.conectar();

			String sql="update SEDE set VC_ESTADO = 'ACT'  where IN_ID_SEDE = ?";
			pstm=cn.prepareStatement(sql);
			
            pstm.setInt(1, IN_ID_SEDE);

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

	@Override
	public int eliminarSede(int IN_ID_SEDE) {
		int resultado=-1;
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn = conexion.conectar();

			String sql="update SEDE set VC_ESTADO = 'ELIM' where IN_ID_SEDE=?";
			pstm=cn.prepareStatement(sql);
			pstm.setInt(1, IN_ID_SEDE);

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
	public ArrayList<Sede> listado(Sede solicitud) {
		ArrayList<Sede> solSer=new ArrayList<Sede>();
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn = conexion.conectar();
			String sql="";
			if(solicitud.getVC_DEPARTAMENTO() != "" && solicitud.getVC_DISTRITO()!="" && solicitud.getVC_PROVINCIA()!="") {
				sql="SELECT s.IN_ID_SEDE,(select VC_USER from USUARIO u where u.IN_ID_USUARIO = s.IN_ID_USUARIO) as 'User',s.VC_SEDE,s.VC_DISTRITO,s.VC_PROVINCIA,s.VC_DEPARTAMENTO,s.VC_ESTADO from bdaquanet.SEDE s where s.VC_ESTADO = 'PEN' AND s.VC_DISTRITO=? AND s.VC_PROVINCIA=? AND s.VC_DEPARTAMENTO=?";
				pstm=cn.prepareStatement(sql);
				pstm.setString(1, solicitud.getVC_DISTRITO());
	        	pstm.setString(2, solicitud.getVC_PROVINCIA());
	        	pstm.setString(3, solicitud.getVC_DEPARTAMENTO());
			}else if(solicitud.getVC_DEPARTAMENTO() != "" && solicitud.getVC_DISTRITO()!="") {
				sql="SELECT s.IN_ID_SEDE,(select VC_USER from USUARIO u where u.IN_ID_USUARIO = s.IN_ID_USUARIO) as 'User',s.VC_SEDE,s.VC_DISTRITO,s.VC_PROVINCIA,s.VC_DEPARTAMENTO,s.VC_ESTADO from bdaquanet.SEDE s where s.VC_ESTADO = 'PEN' AND s.VC_SEDE=? AND s.VC_DEPARTAMENTO=?";
				pstm=cn.prepareStatement(sql);
				pstm.setString(1, solicitud.getVC_DISTRITO());
	        	pstm.setString(2, solicitud.getVC_DEPARTAMENTO());
			}
			else if(solicitud.getVC_DEPARTAMENTO() != "" && solicitud.getVC_PROVINCIA()!="") {
				sql="SELECT s.IN_ID_SEDE,(select VC_USER from USUARIO u where u.IN_ID_USUARIO = s.IN_ID_USUARIO) as 'User',s.VC_SEDE,s.VC_DISTRITO,s.VC_PROVINCIA,s.VC_DEPARTAMENTO,s.VC_ESTADO from bdaquanet.SEDE s where s.VC_ESTADO = 'PEN' AND s.VC_PROVINCIA=? AND s.VC_DEPARTAMENTO=?";
				pstm=cn.prepareStatement(sql);
	        	pstm.setString(1, solicitud.getVC_PROVINCIA());
	        	pstm.setString(2, solicitud.getVC_DEPARTAMENTO());
			}
			else if(solicitud.getVC_DISTRITO()!="" && solicitud.getVC_PROVINCIA()!="") {
				sql="SELECT s.IN_ID_SEDE,(select VC_USER from USUARIO u where u.IN_ID_USUARIO = s.IN_ID_USUARIO) as 'User',s.VC_SEDE,s.VC_DISTRITO,s.VC_PROVINCIA,s.VC_DEPARTAMENTO,s.VC_ESTADO from bdaquanet.SEDE s where s.VC_ESTADO = 'PEN' AND s.VC_PROVINCIA=? AND s.VC_DISTRITO=?";
				pstm=cn.prepareStatement(sql);
	        	pstm.setString(1, solicitud.getVC_PROVINCIA());
	        	pstm.setString(2, solicitud.getVC_DISTRITO());
			}
			else if(solicitud.getVC_DEPARTAMENTO() != "") {
				sql="SELECT s.IN_ID_SEDE,(select VC_USER from USUARIO u where u.IN_ID_USUARIO = s.IN_ID_USUARIO) as 'User',s.VC_SEDE,s.VC_DISTRITO,s.VC_PROVINCIA,s.VC_DEPARTAMENTO,s.VC_ESTADO from bdaquanet.SEDE s where s.VC_ESTADO = 'PEN' AND s.VC_DEPARTAMENTO=?";
				pstm=cn.prepareStatement(sql);
	        	pstm.setString(1, solicitud.getVC_DEPARTAMENTO());
			}
			else if(solicitud.getVC_DISTRITO() != "") {
				sql="SELECT s.IN_ID_SEDE,(select VC_USER from USUARIO u where u.IN_ID_USUARIO = s.IN_ID_USUARIO) as 'User',s.VC_SEDE,s.VC_DISTRITO,s.VC_PROVINCIA,s.VC_DEPARTAMENTO,s.VC_ESTADO from bdaquanet.SEDE s where s.VC_ESTADO = 'PEN' AND s.VC_DISTRITO=?";
				pstm=cn.prepareStatement(sql);
	        	pstm.setString(1, solicitud.getVC_DISTRITO());
			}
			else if(solicitud.getVC_PROVINCIA() != "") {
				sql="SELECT s.IN_ID_SEDE,(select VC_USER from USUARIO u where u.IN_ID_USUARIO = s.IN_ID_USUARIO) as 'User',s.VC_SEDE,s.VC_DISTRITO,s.VC_PROVINCIA,s.VC_DEPARTAMENTO,s.VC_ESTADO from bdaquanet.SEDE s where s.VC_ESTADO = 'PEN' AND s.VC_PROVINCIA=?";
				pstm=cn.prepareStatement(sql);
	        	pstm.setString(1, solicitud.getVC_PROVINCIA());
			}
			else {
				sql="SELECT s.IN_ID_SEDE,(select VC_USER from USUARIO u where u.IN_ID_USUARIO = s.IN_ID_USUARIO) as 'User',s.VC_SEDE,s.VC_DISTRITO,s.VC_PROVINCIA,s.VC_DEPARTAMENTO,s.VC_ESTADO from bdaquanet.SEDE s where s.VC_ESTADO = 'PEN'";
				pstm=cn.prepareStatement(sql);
			}
			rs=pstm.executeQuery();
			Sede obj = new Sede();
			while(rs.next()) {
				obj=new Sede();
				obj.setIN_ID_SEDE(rs.getInt(1));
				obj.setVC_USER(rs.getString(2));
				obj.setVC_SEDE(rs.getString(3));
				obj.setVC_DISTRITO(rs.getString(4));
				obj.setVC_PROVINCIA(rs.getString(5));
				obj.setVC_DEPARTAMENTO(rs.getString(6));
				obj.setVC_ESTADO(rs.getString(7));

				solSer.add(obj);
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
		return solSer;
	}

	

}
