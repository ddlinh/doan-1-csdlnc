package Controller;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import DAO.ConnectionDB;
import Model.HoaDon;
import Model.SanPham;

public class APISanPham {

	public ArrayList<SanPham> list_sp = new ArrayList<SanPham>();
	public ArrayList<SanPham> list_result = new ArrayList<SanPham>();
	
	public APISanPham(){
		this.GetAll();
	}
	
	public void GetAll() {
		Statement stm = null;
		list_sp.clear();
		try {
			if(ConnectionDB.connect_()) {
				String sql = "SELECT * FROM SanPham";
				stm = ConnectionDB.conn.createStatement();
				ResultSet rs = stm.executeQuery(sql);
				while (rs.next()) {
					int masp = rs.getInt("MaSP");
					int soluongton = rs.getInt("SoLuongTon");
					String tensp = rs.getString("TenSP");
					String mota = rs.getString("MoTa");
					int gia = rs.getInt("Gia");
					
					SanPham tmp = new SanPham(masp,tensp,soluongton,mota,gia);
					list_sp.add(tmp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void TimKiemTheoGia(int from, int to) {
		list_result.clear();
		for(int i = 0; i < this.list_sp.size(); i++) {
			if(this.list_sp.get(i).Gia >= from && this.list_sp.get(i).Gia <= to) {
				list_result.add(this.list_sp.get(i));
			}
		}
		
	}
	
	public void TimKiemTheoTen(String tensp) {
		list_result.clear();
		for(int i = 0; i < this.list_sp.size(); i++) {
			if(this.list_sp.get(i).TenSP.toLowerCase().contains(tensp.toLowerCase())) {
				list_result.add(this.list_sp.get(i));
			}
		}
		
	}
	
	public void TimKiemTheoGiaVaTen(String tensp, int from, int to) {
		list_result.clear();
		for(int i = 0; i < this.list_sp.size(); i++) {
			if(this.list_sp.get(i).TenSP.toLowerCase().contains(tensp.toLowerCase()) && 
					this.list_sp.get(i).Gia >= from && this.list_sp.get(i).Gia <= to) {
				list_result.add(this.list_sp.get(i));
			}
		}
	}
	
	
	
}
