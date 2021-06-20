package Model;

public class ChiTietHoaDon {

	public int MaHD;
	public int MaSP;
	public int SoLuong;
	public int GiaBan;
	public int GiaGiam;
	public int ThanhTien;
	public int getMaHD() {
		return MaHD;
	}
	public void setMaHD(int maHD) {
		MaHD = maHD;
	}
	public int getMaSP() {
		return MaSP;
	}
	public void setMaSP(int maSP) {
		MaSP = maSP;
	}
	public int getSoLuong() {
		return SoLuong;
	}
	public void setSoLuong(int soLuong) {
		SoLuong = soLuong;
	}
	public int getGiaBan() {
		return GiaBan;
	}
	public void setGiaBan(int giaBan) {
		GiaBan = giaBan;
	}
	public int getGiaGiam() {
		return GiaGiam;
	}
	public void setGiaGiam(int giaGiam) {
		GiaGiam = giaGiam;
	}
	public int getThanhTien() {
		return ThanhTien;
	}
	public void setThanhTien(int thanhTien) {
		ThanhTien = thanhTien;
	}
	public ChiTietHoaDon(int maHD, int maSP, int soLuong, int giaBan, int giaGiam, int thanhTien) {
		super();
		MaHD = maHD;
		MaSP = maSP;
		SoLuong = soLuong;
		GiaBan = giaBan;
		GiaGiam = giaGiam;
		ThanhTien = thanhTien;
	}
	public ChiTietHoaDon(int maSP, int soLuong, int giaBan, int giaGiam, int thanhTien) {
		super();
		MaSP = maSP;
		SoLuong = soLuong;
		GiaBan = giaBan;
		GiaGiam = giaGiam;
		ThanhTien = thanhTien;
	}
	
	
	
}
