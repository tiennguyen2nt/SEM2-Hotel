CREATE DATABASE [dbHotel]

GO
USE [dbHotel]
GO
/****** Object:  Table [dbo].[DichVu]    Script Date: 6/2/2016 9:56:53 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DichVu](
	[MaDV] [bigint] IDENTITY(1,1) NOT NULL,
	[TenDichVu] [nvarchar](50) NULL,
	[DonGia] [bigint] NULL,
	[TrangThai] [int] NULL,
	[GhiChu] [nvarchar](2000) NULL,
 CONSTRAINT [PK_DichVu] PRIMARY KEY CLUSTERED 
(
	[MaDV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[HoaDon]    Script Date: 6/2/2016 9:56:53 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[HoaDon](
	[MaHD] [bigint] IDENTITY(1,1) NOT NULL,
	[TenKH] [nvarchar](50) NOT NULL,
	[CMTND] [varchar](12) NOT NULL,
	[DienthoaiKH] [varchar](14) NULL,
	[MaPhong] [nvarchar](10) NOT NULL,
	[NgayNhan] [bigint] Not NULL,
	[NgayTra] [bigint] NULL,
	[MaTk] [bigint] NOT NULL,
	[ThanhToan] [money] DEFAULT 0
 CONSTRAINT [PK_HoaDon] PRIMARY KEY CLUSTERED 
(
	[MaHD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[MapDichVu_HoaDon]    Script Date: 6/2/2016 9:56:53 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MapDichVu_HoaDon](
	[ID] [bigint] IDENTITY(1,1)NOT NULL,
	[MaHD] [bigint] NOT NULL,
	[MaDV] [bigint] NOT NULL,
 CONSTRAINT [PK_MapDichVu_HoaDon_1] PRIMARY KEY CLUSTERED 
(
	[ID] ASC,
	[MaHD] ASC,
	[MaDV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 6/2/2016 9:56:53 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[_TaiKhoan](
	[MaTk] [bigint] IDENTITY(1,1) NOT NULL,
	[Ten] [nvarchar](200) NULL,
	[_user] [nvarchar](50) Not NULL,
	[_pass] [nvarchar](50) not null,
	[Quyen] int not null,
 CONSTRAINT [PK_NhanVien] PRIMARY KEY CLUSTERED 
(
	[MaTk] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Phong]    Script Date: 6/2/2016 9:56:53 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Phong](
	[MaPhong] [nvarchar](10) NOT NULL,
	[TenPhong] [nvarchar](50) NULL,
	[LoaiPhong] [int] NULL,
	[TrangThai] [int] NULL,
	[DonGia] [money] NULL,
	[ViTri] [nvarchar](50) null,
	[GhiChu] [nvarchar](2000) null,
	
	
 CONSTRAINT [PK_Phong] PRIMARY KEY CLUSTERED 
(
	[MaPhong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[TongSap]    Script Date: 6/2/2016 9:56:53 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TongSap](
	[MaTS] [bigint] IDENTITY(1,1) NOT NULL,
	[TongGiaPhong] [money] NULL,
	[TongDichVu] [money] NULL,
	[MaHD] [bigint] NULL,
 CONSTRAINT [PK_TongSap] PRIMARY KEY CLUSTERED 
(
	[MaTS] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FK_HoaDon_TaiKhoan] FOREIGN KEY([MaTk])
REFERENCES [dbo].[_TaiKhoan] ([MaTk])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FK_HoaDon_TaiKhoan]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FK_HoaDon_Phong] FOREIGN KEY([MaPhong])
REFERENCES [dbo].[Phong] ([MaPhong])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FK_HoaDon_Phong]
GO
ALTER TABLE [dbo].[MapDichVu_HoaDon]  WITH CHECK ADD  CONSTRAINT [FK_MapDichVu_HoaDon_DichVu] FOREIGN KEY([MaDV])
REFERENCES [dbo].[DichVu] ([MaDV])
GO
ALTER TABLE [dbo].[MapDichVu_HoaDon] CHECK CONSTRAINT [FK_MapDichVu_HoaDon_DichVu]
GO
ALTER TABLE [dbo].[MapDichVu_HoaDon]  WITH CHECK ADD  CONSTRAINT [FK_MapDichVu_HoaDon_HoaDon] FOREIGN KEY([MaHD])
REFERENCES [dbo].[HoaDon] ([MaHD])
GO
ALTER TABLE [dbo].[MapDichVu_HoaDon] CHECK CONSTRAINT [FK_MapDichVu_HoaDon_HoaDon]
GO
ALTER TABLE [dbo].[TongSap]  WITH CHECK ADD  CONSTRAINT [FK_TongSap_HoaDon] FOREIGN KEY([MaHD])
REFERENCES [dbo].[HoaDon] ([MaHD])
GO
ALTER TABLE [dbo].[TongSap] CHECK CONSTRAINT [FK_TongSap_HoaDon]
GO
USE [master]
GO
ALTER DATABASE [dbHotel] SET  READ_WRITE 
GO
use dbHotel
go

----------------------------
-- insert
insert into _TaiKhoan values('admin','admin','admin',0);
insert into _TaiKhoan values('user','user','user',1);


insert into dbo.Phong values('205','phong',1,1,100000,'balaba','')
insert into dbo.Phong values('201','phong 1',1,1,100000,'balaba','')
insert into Phong values('202','phong 2',2,1,200000,'balaba','')
insert into Phong values('203','phong 3',1,1,100000,'balaba','')
insert into Phong values('206','phong 4',2,1,200000,'balaba','')
insert into Phong values('207','phong 5',1,1,100000,'balaba','')
insert into Phong values('208','phong 7',2,1,200000,'balaba','')
insert into Phong values('209','phong 8',1,1,100000,'balaba','')

----------------------------------------------------------------
--- SElect
	Select p.MaPhong, p.TenPhong,p.LoaiPhong, p.DonGia,p.ViTri , h.NgayNhan,h.NgayTra, p.GhiChu From dbo.Phong p INNER JOIN dbo.HoaDon h ON p.MaPhong = h.MaPhong


	-- trả về thời gian đặt phòng
	Select p.MaPhong , h.NgayNhan,h.NgayTra From dbo.Phong p INNER JOIN dbo.HoaDon h ON p.MaPhong = h.MaPhong where h.ThanhToan != 0


	UPDATE HoaDon SET NgayTra=1467734446597,ThanhToan=9000 where MaHD=1

	
------------------------------------------------------------------
--- Procedure

use dbHotel
go
Create procedure nn_login
	@user varchar(50),
	@pass varchar(50),
	@return int Output,
	@ma int Output
as
begin
	declare @u varchar(50), @p varchar(50), @q int, @m int, @n varchar(200);
	select @u = tu._user, @p = tu._pass, @q = tu.Quyen, @m = tu.MaTk
	From dbo._TaiKhoan tu
	Where tu._user = @user
	SELECT @u
	if @u is not null
		begin
			if @pass = @p
				begin
				select @ma = @m
				
				if @q = 0
					select @return = 0
				if @q = 1
					select @return = 1
				
				
				end
			else
				select @return = 2
		end
	else
		select @return= 3
end
go
				

