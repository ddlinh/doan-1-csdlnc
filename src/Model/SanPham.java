package Model;

public class SanPham {

	public int MaSP;
	public String TenSP;
	public int SoLuongTon;
	public String MoTa;
	public int Gia;
	
	
	
	public int getMaSP() {
		return MaSP;
	}
	public void setMaSP(int maSP) {
		MaSP = maSP;
	}
	public String getTenSP() {
		return TenSP;
	}
	public void setTenSP(String tenSP) {
		TenSP = tenSP;
	}
	public int getSoLuongTon() {
		return SoLuongTon;
	}
	public void setSoLuongTon(int soLuongTon) {
		SoLuongTon = soLuongTon;
	}
	public String getMoTa() {
		return MoTa;
	}
	public void setMoTa(String moTa) {
		MoTa = moTa;
	}
	public int getGia() {
		return Gia;
	}
	public void setGia(int gia) {
		Gia = gia;
	}
	public SanPham(int maSP, String tenSP, int soLuongTon, String moTa, int gia) {
		super();
		MaSP = maSP;
		TenSP = tenSP;
		SoLuongTon = soLuongTon;
		MoTa = moTa;
		Gia = gia;
	}
	
}
