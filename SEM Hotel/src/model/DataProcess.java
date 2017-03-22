/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.DichVu;
import entity.HoaDon;
import entity.Room;
import entity.StatesRoom;
import entity.mapDichVu;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tien Nguyen
 */
public class DataProcess {

    public Connection getConnection() {
        Connection conn = null;
        try {
            //nap trinh dieu khien
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //khai bao thong so ket noi
            String user = "sa";
            String pass = "sa";
            String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=dbHotel";
            try {
                conn = DriverManager.getConnection(url, user, pass);
            } catch (SQLException ex) {
                Logger.getLogger(DataProcess.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

    public ArrayList<Room> getDataRoom() {
        ArrayList<Room> list = new ArrayList<Room>();

        String sql = "Select * from Phong";
        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {

                Room r = new Room(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getLong(5), rs.getString(6), rs.getString(7));
                list.add(r);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public boolean updateItemHD(int id, String name, String cmnt, String sdt) {
        int result = 0;
        String sql = "UPDATE HoaDon SET TenKH=?,CMTND=?,DienthoaiKH=? WHERE MaHD=?";
        try {
            PreparedStatement prst = getConnection().prepareStatement(sql);
            prst.setString(1, name);
            prst.setString(2, cmnt);
            prst.setString(3, sdt);
            prst.setInt(4, id);
            result = prst.executeUpdate();
            prst.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result > 0;
    }

    public boolean deleteItemHD(int id) {
        int result = 0;
        String sql = "DELETE FROM HoaDon WHERE MaHD=?";
        try {
            PreparedStatement prst = getConnection().prepareStatement(sql);
            prst.setInt(1, id);
            result = prst.executeUpdate();
            prst.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result > 0;
    }

    public ArrayList<StatesRoom> getDataStatesRoom() {
        ArrayList<StatesRoom> list = new ArrayList<StatesRoom>();

        String sql = "Select p.MaPhong , h.NgayNhan,h.NgayTra From dbo.Phong p INNER JOIN dbo.HoaDon h ON p.MaPhong = h.MaPhong where h.ThanhToan = 0";
        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {

                StatesRoom r = new StatesRoom(rs.getString(1), rs.getLong(2), rs.getLong(3));
                list.add(r);
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("--------------- LỖi getDataStatesRoom");
            Logger.getLogger(DataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<HoaDon> getDataHoaDon() {
        ArrayList<HoaDon> list = new ArrayList<HoaDon>();

        String sql = "Select * from HoaDon";
        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {

                HoaDon r = new HoaDon(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getLong(6), rs.getLong(7), rs.getInt(8), rs.getLong(9));

                list.add(r);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public boolean addHoaDon(HoaDon p) {
        int result = 0;
        String sql = "INSERT INTO HoaDon VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement prst = getConnection().prepareStatement(sql);

            prst.setString(1, p.getTenKH());
            prst.setString(2, p.getCMND());
            prst.setString(3, p.getSDT());
            prst.setString(4, p.getMaPhong());
            prst.setLong(5, p.getNgayNhan());
            prst.setLong(6, p.getNgayTra());
            prst.setInt(7, p.getMaTkTao());
            prst.setFloat(8, p.getThanhtoan());

            result = prst.executeUpdate();

            prst.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result > 0;
    }

    public boolean addDichVu(int maHD, int maDV) {
        int result = 0;
        String sql = "INSERT INTO MapDichVu_HoaDon VALUES(?,?)";
        try {
            PreparedStatement prst = getConnection().prepareStatement(sql);

            prst.setInt(1, maHD);
            prst.setInt(2, maDV);

            result = prst.executeUpdate();

            prst.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result > 0;
    }

    public boolean updateThanhToanHoaDon(int maHD, long money, long ngaytra) {
        int result = 0;
        String sql = "UPDATE HoaDon SET NgayTra=?,ThanhToan=? where MaHD=?";
        try {
            PreparedStatement prst = getConnection().prepareStatement(sql);
            prst.setLong(1, ngaytra);
            prst.setLong(2, money);

            prst.setInt(3, maHD);
            result = prst.executeUpdate();
            prst.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result > 0;
    }

    public ArrayList<DichVu> getDataDichVu() {
        ArrayList<DichVu> list = new ArrayList<DichVu>();

        String sql = "Select * from DichVu where TrangThai !=0";  //lấy ra tất cả dịch vụ còn (còn dịch vụ) 0 là hết,1 là còn
        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {

                DichVu r = new DichVu(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5));

                list.add(r);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<mapDichVu> getDataMapDichVu() {
        ArrayList<mapDichVu> list = new ArrayList<mapDichVu>();

        String sql = "Select * from MapDichVu_HoaDon ";  //lấy ra tất cả dịch vụ còn (còn dịch vụ) 0 là hết,1 là còn
        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {

                mapDichVu r = new mapDichVu(rs.getInt(1), rs.getInt(2), rs.getInt(3));

                list.add(r);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
