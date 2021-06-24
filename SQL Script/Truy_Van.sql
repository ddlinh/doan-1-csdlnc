USE QuanLyBanHang
GO

/*Danh sách Các hóa đơn trong năm 2020*/
DROP INDEX Ngay_Index ON HoaDon
SELECT * FROM HoaDon H WHERE NgayLap BETWEEN '2020-01-01' AND '2020-12-31'

/*Danh sách Khách hàng ở TPHCM*/
SELECT * FROM KhachHang K WHERE K.Tpho LIKE N'%HCM%'
SELECT * FROM KhachHang WHERE Ma_TPho = 29

/*Danh sách Khách hàng ở Hà Nội*/
SELECT * FROM KhachHang K WHERE K.Tpho LIKE N'%Hà Nội%'
SELECT * FROM KhachHang WHERE Ma_TPho = 24


/*Danh sách sản phẩm bán chạy nhất*/
SELECT TOP 50 MaSP, SUM(SoLuong) AS SoLuongBan FROM CT_HoaDon C GROUP BY C.MaSP ORDER BY SUM(SoLuong) DESC

/*Danh sách các sản phẩm có doanh thu cao nhất*/
SELECT TOP 50 MaSP, SUM(ThanhTien) AS DoanhThu FROM CT_HoaDon C GROUP BY C.MaSP ORDER BY SUM(ThanhTien) DESC
