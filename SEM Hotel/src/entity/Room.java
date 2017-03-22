/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

/**
 *
 * @author Tien Nguyen
 */
public class Room {
    private String id;
    private String phong;
    private int loaiphong;
    private int trangThai;
    private long tien;
    private String Vitri;
    private String GhiChu;

    public Room() {
    }

    public Room(String id, String phong, int loaiphong, int trangThai, long tien, String Vitri, String GhiChu) {
        this.id = id;
        this.phong = phong;
        this.loaiphong = loaiphong;
        this.trangThai = trangThai;
        this.tien = tien;
        this.Vitri = Vitri;
        this.GhiChu = GhiChu;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhong() {
        return phong;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }

    public int getLoaiphong() {
        return loaiphong;
    }

    public void setLoaiphong(int loaiphong) {
        this.loaiphong = loaiphong;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public long getTien() {
        return tien;
    }

    public void setTien(long tien) {
        this.tien = tien;
    }

    public String getVitri() {
        return Vitri;
    }

    public void setVitri(String Vitri) {
        this.Vitri = Vitri;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }
   
 

   

}
