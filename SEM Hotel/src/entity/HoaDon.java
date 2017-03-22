/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Tien Nguyen
 */
public class HoaDon {
    private int id;
    private String TenKH;
    private String CMND;
    private String SDT;
    private String maPhong;
    private long ngayNhan;
    private long ngayTra;
    
    private int maTkTao;
    private long Thanhtoan;

    public HoaDon() {
    }

    public HoaDon(int id, String TenKH, String CMND, String SDT, String maPhong, long ngayNhan, long ngayTra, int maTkTao, long Thanhtoan) {
        this.id = id;
        this.TenKH = TenKH;
        this.CMND = CMND;
        this.SDT = SDT;
        this.maPhong = maPhong;
        this.ngayNhan = ngayNhan;
        this.ngayTra = ngayTra;
        
        this.maTkTao = maTkTao;
        this.Thanhtoan = Thanhtoan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public long getNgayNhan() {
        return ngayNhan;
    }

    public void setNgayNhan(long ngayNhan) {
        this.ngayNhan = ngayNhan;
    }

    public long getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(long ngayTra) {
        this.ngayTra = ngayTra;
    }

   

    public int getMaTkTao() {
        return maTkTao;
    }

    public void setMaTkTao(int maTkTao) {
        this.maTkTao = maTkTao;
    }

    public long getThanhtoan() {
        return Thanhtoan;
    }

    public void setThanhtoan(long Thanhtoan) {
        this.Thanhtoan = Thanhtoan;
    }

    
    
}
