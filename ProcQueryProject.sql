use dbHotel
go
CREATE PROC getRoom(
@id NVARCHAR(50))
AS
BEGIN
SELECT TenPhong,LoaiPhong,DonGia,ViTri,GhiChu FROM Phong WHERE MaPhong=@id
END
GO
CREATE PROC updateRoom(
@id NVARCHAR(50),
@name NVARCHAR(50),
@type INT,
@price INT,
@role NVARCHAR(50),
@note NVARCHAR(2000))
AS
BEGIN
UPDATE dbo.Phong SET TenPhong=@name, LoaiPhong=@type, DonGia=@price, ViTri=@role, GhiChu=@note WHERE MaPhong=@id
END
GO
CREATE PROC deleteRoom(
@id NVARCHAR(50))
AS
DELETE FROM Phong WHERE MaPhong=@id
GO
CREATE PROC addService(
@name NVARCHAR(50),
@price INT,
@note NVARCHAR(2000))
AS
INSERT INTO dbo.DichVu
        ( TenDichVu ,
          DonGia ,
          TrangThai ,
          GhiChu
        )
VALUES  ( @name , -- TenDichVu - nvarchar(50)
          @price , -- DonGia - money
          1, -- TrangThai - int
          @note  -- GhiChu - nvarchar(2000)
        )
GO
CREATE PROC collectServiceData
AS
SELECT * FROM dbo.DichVu
GO
CREATE PROC editService(
@id BIGINT,
@name NVARCHAR(50),
@price BIGINT,
@status INT,
@note NVARCHAR(2000))
AS
UPDATE dbo.DichVu SET TenDichVu=@name,DonGia=@price,TrangThai=@status,GhiChu=@note WHERE MaDV=@id
GO
CREATE PROC deleteService(
@id BIGINT)
AS
DELETE FROM dbo.DichVu WHERE MaDV=@id
GO


