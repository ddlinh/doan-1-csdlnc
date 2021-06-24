package Controller;

import java.sql.Statement;

import DAO.ConnectionDB;
import Model.ChiTietHoaDon;

public class APIChiTietHoaDon {
	
	
	public void ThemChiTietHoaDon(ChiTietHoaDon cthd) {
		Statement stm = null;
		try {
			if(ConnectionDB.connect()) {
				String sql = "INSERT INTO CT_HoaDon VALUES (" + cthd.MaHD + ", " + cthd.MaSP + ", " + cthd.SoLuong + ", " + 
			cthd.GiaBan + ", " + cthd.GiaGiam + ", " + cthd.ThanhTien + ")";
				stm = ConnectionDB.conn.createStatement();
				stm.executeUpdate(sql);
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
