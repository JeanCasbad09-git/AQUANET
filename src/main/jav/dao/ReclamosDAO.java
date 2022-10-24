package dao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.sql.PreparedStatement;

import conexion.Conexion;
import entities.Reclamos;
import interfaces.ReclamosInterface;

public class ReclamosDAO implements ReclamosInterface{

	@Override
	public int createReclamos(Reclamos obj) {
		int result =-1;
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn = conexion.conectar();			
			String sql = "insert into Reclamos(IN_ID_USUARIO,VC_ESTADO,VC_RECLAMO,DT_FECHA)"
					+ " values (2,'PEN',?,now())";
			pstm=cn.prepareStatement(sql);
			//pstm.setInt(1, obj.getIN_ID_USUARIO());
			//pstm.setString(2, obj.getVC_ESTADO());
			pstm.setString(1, obj.getVC_RECLAMO());
			//pstm.setDate(4, obj.getDT_FECHA());
			pstm.execute();
		} catch (Exception e) {
			System.out.println("ERROR AL REGISTRAR :"+e.getMessage());
		}finally {
			try {
				if(cn!=null)cn.close();
				if(pstm!=null)pstm.close();
				if(rs!=null)rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public int updateReclamosAdmin(Reclamos obj) {	
		int result =-1;
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn = conexion.conectar();			
			String sql = "update Reclamos set VC_ESTADO = ?, VC_RECLAMO = ?  where IN_ID_RECLAMO = ?";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1, obj.getVC_ESTADO());
			pstm.setString(2, obj.getVC_RECLAMO());
			pstm.execute();
		} catch (Exception e) {
			System.out.println("ERROR AL ACTUALIZAR :"+e.getMessage());
		}finally {
			try {
				if(cn!=null)cn.close();
				if(pstm!=null)pstm.close();
				if(rs!=null)rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	@Override
	public int updateReclamosCliente(Reclamos obj) {	
		int result =-1;
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn = conexion.conectar();			
			String sql = "update Reclamos set VC_RECLAMO = ?  where IN_ID_RECLAMO = ?";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1, obj.getVC_RECLAMO());
			pstm.execute();
		} catch (Exception e) {
			System.out.println("ERROR AL ACTUALIZAR :"+e.getMessage());
		}finally {
			try {
				if(cn!=null)cn.close();
				if(pstm!=null)pstm.close();
				if(rs!=null)rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public int deleteReclamos(int IN_ID_RECLAMO) {
		int result =-1;
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;		
		try {
			cn = conexion.conectar();
			System.out.println(IN_ID_RECLAMO);
			String sql = "DELETE FROM RECLAMOS where IN_ID_RECLAMO=?";
			pstm=cn.prepareStatement(sql);
			pstm.setInt(1, IN_ID_RECLAMO);
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
	public ArrayList<Reclamos> listReclamos(String query) {
		ArrayList<Reclamos> coxm = new ArrayList<Reclamos>();
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn = conexion.conectar();
			String sql= "select r.IN_ID_RECLAMO as ID, (select VC_USER from Usuario u where u.IN_ID_USUARIO = r.IN_ID_USUARIO) as User, r.VC_ESTADO as Estado, r.VC_RECLAMO as Reclamo, r.DT_FECHA as FechaRegistro,"
					+ " r.IN_ID_USUARIO from Reclamos r;";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			Reclamos obj = new Reclamos();
			while(rs.next()) {		
				obj=new Reclamos();
				obj.setIN_ID_RECLAMO(rs.getInt(1));
				obj.setUsuarioCreador(rs.getString(2));
				obj.setVC_ESTADO(rs.getString(3));
				obj.setVC_RECLAMO(rs.getString(4));
				obj.setDT_FECHA(rs.getDate(5));
				obj.setIN_ID_USUARIO(rs.getInt(6));
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
	public Reclamos getReclamosById(int IN_ID_RECLAMO) {		
		Reclamos rec= new Reclamos();
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		cn = conexion.conectar();
		try {
			
			String sql="Select r.IN_ID_RECLAMO as ID,(select VC_USER from Usuario u where u.IN_ID_USUARIO = r.IN_ID_USUARIO) as User, r.VC_ESTADO as Estado, r.VC_RECLAMO as Reclamo,  r.DT_FECHA as FechaRegistro from Reclamos r "
					+ "where r.IN_ID_RECLAMO = ?";
			pstm=cn.prepareStatement(sql);
			 pstm.setInt(1, IN_ID_RECLAMO);
			rs=pstm.executeQuery();
			
			while(rs.next()) {
				rec=new Reclamos();
				rec.setIN_ID_RECLAMO(rs.getInt(1));
				rec.setUsuarioCreador(rs.getString(2));
				rec.setVC_ESTADO(rs.getString(3));
				rec.setVC_RECLAMO(rs.getString(4));
				rec.setDT_FECHA(rs.getDate(5));
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
		return rec;
	}

	@Override
	public ByteArrayOutputStream exportAllData() throws IOException {
		Workbook wb = new HSSFWorkbook();
		ByteArrayOutputStream ost = new ByteArrayOutputStream();
		
		Sheet sheet = wb.createSheet("Reclamos");
		Row firstRow = sheet.createRow(0);
		
		firstRow.createCell(0).setCellValue("ID");
		firstRow.createCell(1).setCellValue("User");
		firstRow.createCell(2).setCellValue("Estado");
		firstRow.createCell(3).setCellValue("Reclamo");
		firstRow.createCell(4).setCellValue("Fecha");
		
		List<Reclamos> reclamos = this.listReclamos("");
		int initRow = 1;
		for(Reclamos reclamo : reclamos) {
			Row row = sheet.createRow(initRow);
			row.createCell(0).setCellValue(reclamo.getIN_ID_RECLAMO());
			row.createCell(1).setCellValue(reclamo.getUsuarioCreador());
			row.createCell(2).setCellValue(reclamo.getVC_ESTADO());
			row.createCell(3).setCellValue(reclamo.getVC_RECLAMO());
			row.createCell(4).setCellValue(reclamo.getDT_FECHA());
			
			initRow++;
		}		

		wb.write(ost);
		wb.close();
		
		return ost;
	}

	
}
