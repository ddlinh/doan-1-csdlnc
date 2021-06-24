USE QuanLyBanHang
GO

EXECUTE sp_helpindex HoaDon

/*Tạo chỉ mục bảng hóa đơn*/
DROP INDEX Ngay_Index ON HoaDon
CREATE NONCLUSTERED INDEX Ngay_Index ON HoaDon(NgayLap) INCLUDE (MaKH, TongTien)
SELECT * FROM HoaDon H WHERE NgayLap BETWEEN '2020-01-01' AND '2020-12-31'

/*Tạo chỉ mục bảng KhachHang*/
DROP INDEX Tpho_Index ON KhachHang
CREATE NONCLUSTERED INDEX Tpho_Index ON KhachHang(Tpho) INCLUDE (Ho, Ten, NgSinh, SoNha, Duong, Phuong, Quan, DienThoai)
SELECT * FROM KhachHang K WHERE K.Tpho LIKE N'%HCM%'
SELECT * FROM KhachHang K WHERE K.Tpho LIKE N'%Hà Nội%'

DROP INDEX Ma_TPho_Index ON KhachHang
CREATE NONCLUSTERED INDEX Ma_TPho_Index ON KhachHang(Ma_TPho) INCLUDE (Ho, Ten, NgSinh, SoNha, Duong, Phuong, Quan, TPho, DienThoai)
SELECT * FROM KhachHang WHERE Ma_TPho = 29
SELECT * FROM KhachHang WHERE Ma_TPho = 24

/*Tạo chỉ mục bảng CT_HoaDon*/
DROP INDEX MaSP_SoLuong_Index ON CT_HoaDon
CREATE NONCLUSTERED INDEX MaSP_SoLuong_Index ON CT_HoaDon(MaSP) INCLUDE (SoLuong)

DROP INDEX MaSP_ThanhTien_Index ON CT_HoaDon
CREATE NONCLUSTERED INDEX MaSP_ThanhTien_Index ON CT_HoaDon(MaSP) INCLUDE (ThanhTien)

SELECT TOP 50 MaSP, SUM(SoLuong) AS SoLuongBan FROM CT_HoaDon C GROUP BY C.MaSP ORDER BY SUM(SoLuong) DESC
SELECT TOP 50 MaSP, SUM(ThanhTien) AS DoanhThu FROM CT_HoaDon C GROUP BY C.MaSP ORDER BY SUM(ThanhTien) DESC
