package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import conexion.Conexion;
import entities.CortesMantenimiento;
import interfaces.CortesMantenimientoInterface;

public class CortesMantenimientoDAO implements CortesMantenimientoInterface {

	@Override
	public int createCorte_Mantenimiento(CortesMantenimiento obj) {
		int result =-1;
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;		
		try {
			cn = conexion.conectar();
			
			String sql = "insert into cortexmantenimiento(VC_DISTRITO,VC_PROVINCIA,VC_DEPARTAMENTO,VC_COMENTARIO,DT_FECHA) "
					+ "		values (?,?,?,?,?)";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1, obj.getVC_DISTRITO());
			pstm.setString(2, obj.getVC_PROVINCIA());
			pstm.setString(3, obj.getVC_DEPARTAMENTO());
			pstm.setString(4, obj.getVC_COMENTARIO());
			pstm.setDate(5, (Date) obj.getDT_FECHA());
			pstm.execute();
		} catch (Exception e) {
			System.out.println("ERROR AL REGISTRAR: "+e.getMessage());
		}finally {
			try {
				if(cn!=null)cn.close();
				if(pstm!=null)pstm.close();
				if(rs!=null)rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			} {}
		}
		return result;
	}

	@Override
	public int updateCorte_Mantenimiento(CortesMantenimiento obj) {
		int result =-1;
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;		
		try {
			cn = conexion.conectar();
			
			String sql = "update cortexmantenimiento set VC_DISTRITO = ?,VC_PROVINCIA = ?,VC_DEPARTAMENTO = ?,VC_COMENTARIO = ?,DT_FECHA = ? "
					+ "	where IN_ID_CORTXMAN = ?";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1, obj.getVC_DISTRITO());
			pstm.setString(2, obj.getVC_PROVINCIA());
			pstm.setString(3, obj.getVC_DEPARTAMENTO());
			pstm.setString(4, obj.getVC_COMENTARIO());
			pstm.setDate(5, obj.getDT_FECHA());
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
			} {}
		}
		return result;
	}

	@Override
	public int deleteCorte_Mantenimiento(int IN_ID_CORTXMAN) {
		int result =-1;
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;		
		try {
			cn = conexion.conectar();
			
			String sql = "DELETE FROM cortexmantenimiento where in_id_cortxman=?;";
			pstm=cn.prepareStatement(sql);
			pstm.setInt(1, IN_ID_CORTXMAN);
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
			} {}
		}
		return result;
	}

	@Override
	public ArrayList<CortesMantenimiento> listCorte(String query) {
		ArrayList<CortesMantenimiento> coxm = new ArrayList<CortesMantenimiento>();
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn = conexion.conectar();
			String sql= "Select IN_ID_CORTXMAN as ID, VC_DISTRITO as Distrito, VC_PROVINCIA as Provincia"
					+ ", VC_DEPARTAMENTO as Departamento, VC_COMENTARIO as Comentario, DT_FECHA as FechaCorte from cortexmantenimiento ORDER BY DT_FECHA desc";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			CortesMantenimiento obj = new CortesMantenimiento();
			while(rs.next()) {		
				obj=new CortesMantenimiento();
				obj.setIN_ID_CORTXMAN(rs.getInt(1));
				obj.setVC_DISTRITO(rs.getString(2));
				obj.setVC_PROVINCIA(rs.getString(3));
				obj.setVC_DEPARTAMENTO(rs.getString(4));
				obj.setVC_COMENTARIO(rs.getString(5));
				obj.setDT_FECHA(rs.getDate(6));
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
	public CortesMantenimiento getCorteById(int IN_ID_CORTXMAN) {
		CortesMantenimiento cor= new CortesMantenimiento();
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		cn = conexion.conectar();
		try {
			
			String sql="Select IN_ID_CORTXMAN as ID, VC_DISTRITO as Distrito, VC_PROVINCIA as Provincia, VC_DEPARTAMENTO as Departamento, VC_COMENTARIO as Comentario,  DT_FECHA as FechaCorte from cortexmantenimiento where IN_ID_CORTXMAN = ?";
			pstm=cn.prepareStatement(sql);
			 pstm.setInt(1, IN_ID_CORTXMAN);
			rs=pstm.executeQuery();
			
			while(rs.next()) {
				cor=new CortesMantenimiento();
				cor.setIN_ID_CORTXMAN(rs.getInt(1));
				cor.setVC_DISTRITO(rs.getString(2));
				cor.setVC_PROVINCIA(rs.getString(3));
				cor.setVC_DEPARTAMENTO(rs.getString(4));
				cor.setVC_COMENTARIO(rs.getString(4));
				cor.setDT_FECHA(rs.getDate(6));
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
		return cor;
	}
}
