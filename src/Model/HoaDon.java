package Model;

public class HoaDon {
	
	public int MaKH;
	public String NgayLap;
	public int TongTien;
	
	public HoaDon() {
		
	}
	
	public HoaDon(String ngayLap, int tongTien, int maHD) {
		super();
		NgayLap = ngayLap;
		TongTien = tongTien;
		MaHD = maHD;
	}
	public HoaDon(int maKH, String ngayLap, int tongTien, int maHD) {
		super();
		MaKH = maKH;
		NgayLap = ngayLap;
		TongTien = tongTien;
		MaHD = maHD;
	}
	public int MaHD;
	public int getMaHD() {
		return MaHD;
	}
	public void setMaHD(int maHD) {
		MaHD = maHD;
	}
	public int getMaKH() {
		return MaKH;
	}
	public void setMaKH(int maKH) {
		MaKH = maKH;
	}
	public String getNgayLap() {
		return NgayLap;
	}
	public void setNgayLap(String ngayLap) {
		NgayLap = ngayLap;
	}
	public int getTongTien() {
		return TongTien;
	}
	public void setTongTien(int tongTien) {
		TongTien = tongTien;
	}
	
	

}
