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
public class mapDichVu {
    private int id;
    private int maHD;
    private int maDV;

    public mapDichVu() {
    }

    public mapDichVu(int id, int maHD, int maDV) {
        this.id = id;
        this.maHD = maHD;
        this.maDV = maDV;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public int getMaDV() {
        return maDV;
    }

    public void setMaDV(int maDV) {
        this.maDV = maDV;
    }

   
    
}
