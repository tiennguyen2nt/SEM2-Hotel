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
public class StatesRoom {
    private String idRoom;
    private long ngayNhan;
    private long ngayTra;

    public String getIdRoom() {
        return idRoom;
    }

    public long getNgayNhan() {
        return ngayNhan;
    }

    public long getNgayTra() {
        return ngayTra;
    }

    public StatesRoom(String idRoom, long ngayNhan, long ngayTra) {
        this.idRoom = idRoom;
        this.ngayNhan = ngayNhan;
        this.ngayTra = ngayTra;
    }
    
    
}
