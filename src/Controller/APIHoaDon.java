package Controller;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import DAO.ConnectionDB;
import Model.ChiTietHoaDon;
import Model.HoaDon;

public class APIHoaDon {
	public ArrayList<HoaDon> list_hd = new ArrayList<HoaDon>();
	
	public ArrayList<HoaDon> list_result = new ArrayList<HoaDon>();
	
	
	public void GetAll() {
		Statement stm = null;
		list_hd.clear();
		try {
			if(ConnectionDB.connect_()) {
				String sql = "SELECT * FROM HoaDon";
				stm = ConnectionDB.conn.createStatement();
				ResultSet rs = stm.executeQuery(sql);
				while (rs.next()) {
					int mahd = rs.getInt("MaHD");
					int makh = rs.getInt("MaKH");
					String ngaylap = rs.getString("NgayLap");
					
					int tongtien = rs.getInt("TongTien");
					HoaDon tmp = new HoaDon(makh,ngaylap,tongtien,mahd);
					list_hd.add(tmp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void TimKiemTheoMaKH_Ngay(int MaKH, String NgayBD, String NgayKT) {
		Statement stm = null;
		list_result.clear();
		try {
			if(ConnectionDB.connect_()) {
				String sql = "SELECT * FROM HoaDon WHERE MaKH = " + MaKH + " AND NgayLap >= '" + NgayBD + 
						"' AND NgayLap <= '" + NgayKT + "'";
				stm = ConnectionDB.conn.createStatement();
				ResultSet rs = stm.executeQuery(sql);
				while (rs.next()) {
					int mahd = rs.getInt("MaHD");
					int makh = rs.getInt("MaKH");
					String ngaylap = rs.getString("NgayLap");
					
					int tongtien = rs.getInt("TongTien");
					HoaDon tmp = new HoaDon(makh,ngaylap,tongtien,mahd);
					list_result.add(tmp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void TimKiemTheoMaKH(int MaKH) {
		Statement stm = null;
		list_result.clear();
		try {
			if(ConnectionDB.connect_() && MaKH > 0) {
				String sql = "SELECT * FROM HoaDon WHERE MaKH = " + MaKH;
				stm = ConnectionDB.conn.createStatement();
				ResultSet rs = stm.executeQuery(sql);
				while (rs.next()) {
					int mahd = rs.getInt("MaHD");
					int makh = rs.getInt("MaKH");
					String ngaylap = rs.getString("NgayLap");
					
					int tongtien = rs.getInt("TongTien");
					HoaDon tmp = new HoaDon(makh,ngaylap,tongtien,mahd);
					list_result.add(tmp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void TimKiemTheoNgay(String NgayBD, String NgayKT) {
		Statement stm = null;
		list_result.clear();
		try {
			if(ConnectionDB.connect_() && NgayBD.length() > 0 && NgayKT.length() > 0) {
				String sql = "SELECT * FROM HoaDon WHERE NgayLap >= '" + NgayBD + "' AND NgayLap <= '" + NgayKT + "'";
				stm = ConnectionDB.conn.createStatement();
				ResultSet rs = stm.executeQuery(sql);
				while (rs.next()) {
					int mahd = rs.getInt("MaHD");
					int makh = rs.getInt("MaKH");
					String ngaylap = rs.getString("NgayLap");
					
					int tongtien = rs.getInt("TongTien");
					HoaDon tmp = new HoaDon(makh,ngaylap,tongtien,mahd);
					list_result.add(tmp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void Edit(int MaHD, int MaKH, String Ngay, int TongTien) {
		Statement stm = null;
		
		try {
			if(ConnectionDB.connect_() && MaKH > 0) {
				String sql = "UPDATE HoaDon Set MaKH = " + MaKH + ", NgayLap = '" + Ngay + "', TongTien = " + TongTien + 
						" Where MaHD = " + MaHD;
				stm = ConnectionDB.conn.createStatement();
				stm.executeUpdate(sql);
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Delete_CTHD(int MaHD) {
		Statement stm = null;
		
		try {
			if(ConnectionDB.connect_() && MaHD > 0) {
				String sql = "DELETE FROM CT_HoaDon Where MaHD = " + MaHD;
				stm = ConnectionDB.conn.createStatement();
				stm.executeUpdate(sql);
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Delete(int MaHD) {
		
		this.Delete_CTHD(MaHD);
		Statement stm = null;
		
		try {
			if(ConnectionDB.connect_() && MaHD > 0) {
				String sql = "DELETE FROM HoaDon Where MaHD = " + MaHD;
				stm = ConnectionDB.conn.createStatement();
				stm.executeUpdate(sql);
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void ThemHD(int MaKH, String Ngay, int TongTien, ArrayList<ChiTietHoaDon> list_cthd) {
		Statement stm = null;
		this.GetAll();
		int MaHD = 1 + this.list_hd.size();
		boolean check_listSP = false;
		if(list_cthd.size() > 0) {
			check_listSP = true;
			for(int i = 0; i < list_cthd.size(); i++) {
				list_cthd.get(i).setMaHD(MaHD);
			}
		}
		try {
			if(ConnectionDB.connect_() && MaKH > 0) {
				String sql = "INSERT INTO HoaDon VALUES (" + MaHD + ", " + MaKH + ", '" + Ngay + "', " + TongTien + ")";
				stm = ConnectionDB.conn.createStatement();
				stm.executeUpdate(sql);
				}
			for(int i = 0; i < list_cthd.size(); i++) {
				APIChiTietHoaDon cthd = new APIChiTietHoaDon();
				cthd.ThemChiTietHoaDon(list_cthd.get(i));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
