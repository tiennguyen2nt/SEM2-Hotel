/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import entity.DichVu;
import entity.HoaDon;
import entity.Room;
import entity.StatesRoom;
import entity.mapDichVu;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DataProcess;

/**
 *
 * @author Tien Nguyen
 */
public class Function {

    private static DataProcess dp = new DataProcess();
    private static int idTK;
    private static String nameTK = null;
    private static ArrayList<Room> room = dp.getDataRoom();
    public static ArrayList<StatesRoom> sroom = dp.getDataStatesRoom();
    private static ArrayList<HoaDon> hoadon = dp.getDataHoaDon();
    private static ArrayList<DichVu> dicvu = dp.getDataDichVu();
    public int rSize = 0, cSize = 0, mpBan = 0, mpTrong = 0, mpDTruoc = 0, mpETime = 0;

    public int login(String id, String pass) {
        try {
            Connection conn = dp.getConnection();
            CallableStatement cas = conn.prepareCall("{call dbo.nn_login(?,?,?,?)}");
            cas.setString(1, id);
            cas.setString(2, pass);
            cas.registerOutParameter(3, java.sql.Types.INTEGER);
            cas.registerOutParameter(4, java.sql.Types.INTEGER);

            cas.execute();
            idTK = cas.getInt(4);

            return cas.getInt(3);
        } catch (SQLException ex) {
            Logger.getLogger(DataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public ArrayList<HoaDon> getHoaDon() {
        return hoadon;
    }

    public boolean reSTT() {
        boolean a = false;
        sroom = dp.getDataStatesRoom();
        room = dp.getDataRoom();
        hoadon = dp.getDataHoaDon();
        dicvu = dp.getDataDichVu();

        int pBan = 0, pTrong = 0, pDTruoc = 0, pETime = 0;
        for (int i = 0; i < room.size(); i++) {
            int stt = getStatesRoom(room.get(i).getId());
            if (stt == 0) {
                pTrong++;
            }

            if (stt == 1) {
                pDTruoc++;
            }
            if (stt == 2) {
                pBan++;
            }
            if (stt == 3) {
                pETime++;
            }

        }

        if (rSize != room.size() || cSize != sroom.size() || mpBan != pBan || mpDTruoc != pDTruoc || mpETime != mpETime || mpTrong != mpTrong) {
            rSize = room.size();
            cSize = sroom.size();
            mpBan = pBan;
            mpDTruoc = pDTruoc;
            mpTrong = pTrong;
            mpETime = pETime;

            a = true;
        }
        return a;
    }

    public ArrayList<Room> getRoom() {
        return room;
    }

    public ArrayList<DichVu> getDichVu() {
        return dicvu;
    }

    private int SttR(int j, long date) {

        if (sroom.get(j).getNgayNhan() > date && sroom.get(j).getNgayNhan() - date < 30 * 60 * 1000) {
            return 1;
        }
        if (sroom.get(j).getNgayNhan() < date && sroom.get(j).getNgayTra() > date) {
            return 2;
        }
        if (sroom.get(j).getNgayTra() > date && sroom.get(j).getNgayTra() - date < 20 * 60 * 1000) {
            return 3;
        }

        return 0;
    }

    public ArrayList<DichVu> listDV(int maHD) {
        ArrayList<DichVu> dv = new ArrayList<>();
        ArrayList<mapDichVu> map = dp.getDataMapDichVu();

        for (int i = 0; i < map.size(); i++) {

            if (map.get(i).getMaHD() == maHD) {

                for (int j = 0; j < dicvu.size(); j++) {
                    if (map.get(i).getMaDV() == dicvu.get(j).getId()) {
                        dv.add(dicvu.get(j));
                    }

                }
            }

        }

        return dv;
    }

    public float tongTienPhong(String maphong, long time) {

        float day = time / 1000 / 60 / 60 / 24;

        int iDay = (int) day;
        if (iDay < day || day == 0 || (time % (1000 * 60 * 60 * 24)) > 0) {
            iDay++;
        }

        float gia = 0;
        for (int i = 0; i < room.size(); i++) {
            Room get = room.get(i);
            if (get.getId().equalsIgnoreCase(maphong)) {
                gia = get.getTien();
            }
        }

        if (gia > 0) {
            return gia * iDay;
        }
        return 0;
    }

    //Trả về trạng thái của phòng (Đặt trước 30 phút, Đang bận, Hết thời gian trong 20 phút sau 20p chuyen sang phòng trong, phng trống)
    public int getStatesRoom(String maphong) {
        int res = 0;

        int multi = 0;
        for (int j = 0; j < sroom.size(); j++) {

            if (maphong.equalsIgnoreCase(sroom.get(j).getIdRoom())) {
                multi++;

                //Lấy ra thời gian đặt va trả phòng nhỏ nhất
                if (multi == 1) {
                    res = SttR(j, getDate());
                } else if (multi > 1 && res == 0) {
                    res = SttR(j, getDate());
                }

            }

        }

        return res;
    }

    public void setDateHoaDon(String TenKH, String CMND, String SDT, String maPhong, long ngayNhan, long ngayTra) {

        HoaDon h = new HoaDon(0, TenKH, CMND, SDT, maPhong, ngayNhan, ngayTra, idTK, 0);
        dp.addHoaDon(h);

        //get lại dữ liệu đặt phòng từ database
        sroom = dp.getDataStatesRoom();

    }

    public boolean findString(String str3, String find3) {
        boolean result = false;
        String str2 = str3.trim();
        String find2 = find3.trim();
        String str = str2.toLowerCase();
        String find = find2.toLowerCase();

        char[] charStr = str.toCharArray();
        char[] charFind = find.toCharArray();

        for (int i = 0; i < charStr.length; i++) {

            if (charStr[i] == charFind[0]) {
                result = true;
                if (i <= charStr.length - charFind.length) {
                    for (int j = 1; j < charFind.length; j++) {

                        if (charStr[i + j] == charFind[j]) {
                            if (charFind.length - 1 == j) {
                                result = true;

                            }
                        } else {
                            result = false;
                        }

                    }
                    break;
                } else {

                    result = false;
                    break;
                }
            }

        }
        return result;
    }

    public int FindStatesRoom(String maphong, long tFrom, long tTo) {
        int res = 0;

        int multi = 0;

        for (int j = 0; j < sroom.size(); j++) {

            if (maphong.equalsIgnoreCase(sroom.get(j).getIdRoom())) {
                multi++;

                if (multi == 1) {
                    res = sttRoomFind(tFrom, tTo, j);
                }
                if (multi > 1 && res == 0) {
                    res = sttRoomFind(tFrom, tTo, j);
                }
            }

        }

        return res;
    }

    private int sttRoomFind(long tFrom, long tTo, int j) {
        int res = 0;
        if (tTo != 0) {
            if (sroom.get(j).getNgayTra() > tTo && sroom.get(j).getNgayNhan() < tTo) {
                res = 2;

            }
            if (sroom.get(j).getNgayTra() > tFrom && sroom.get(j).getNgayNhan() < tFrom) {
                res = 2;

            }
            if (sroom.get(j).getNgayTra() < tTo && sroom.get(j).getNgayNhan() > tFrom) {
                res = 2;

            }

//            if (sroom.get(j).getNgayTra() < tFrom + n && sroom.get(j).getNgayTra() > tFrom) {
//                res = 3;
//            }
//            if (sroom.get(j).getNgayNhan() > tTo - n && sroom.get(j).getNgayNhan() < tTo) {
//                res = 1;
//            }
        } else {
            res = SttR(j, tFrom);
        }
        return res;
    }

    public HoaDon findPrintHoadon(String id, String name, int n) {
        HoaDon res = new HoaDon();
        for (HoaDon hoadon1 : hoadon) {
            if (hoadon1.getThanhtoan() == 0) {
                if (hoadon1.getNgayNhan() < getDate()) {
                    if (n == 1) {
                        if (hoadon1.getMaPhong().equalsIgnoreCase(id)) {
                            res = hoadon1;
                        }
                    } else if (n == 1) {
                        if (hoadon1.getTenKH().equalsIgnoreCase(name)) {
                            res = hoadon1;
                        }
                    }

                }
            }
        }

        return res;
    }
public ArrayList<HoaDon> findInHoaDon(String find, String pattern) {
        ArrayList<HoaDon> result = null;
        if (pattern.equalsIgnoreCase("Mã hóa đơn")) {
            result = new ArrayList<>();
            for (int i = 0; i < hoadon.size(); i++) {
                if (hoadon.get(i).getId() == Integer.parseInt(find)) {
                    result.add(hoadon.get(i));

                }
            }
        } else if (pattern.equalsIgnoreCase("Tên khách hàng")) {
            result = new ArrayList<>();
            for (int i = 0; i < hoadon.size(); i++) {
                if (findString(hoadon.get(i).getTenKH(), find)) {
                    result.add(hoadon.get(i));

                }
            }
        } else if (pattern.equalsIgnoreCase("Mã phòng")) {
            result = new ArrayList<>();
            for (int i = 0; i < hoadon.size(); i++) {
                if (findString(hoadon.get(i).getMaPhong(), find)) {
                    result.add(hoadon.get(i));

                }
            }
        } else if (pattern.equalsIgnoreCase("CMND")) {
            result = new ArrayList<>();
            for (int i = 0; i < hoadon.size(); i++) {
                if (findString(hoadon.get(i).getCMND(), find)) {
                    result.add(hoadon.get(i));

                }
            }
        }else if (pattern.equalsIgnoreCase("SĐT")) {
            result = new ArrayList<>();
            for (int i = 0; i < hoadon.size(); i++) {
                if (findString(hoadon.get(i).getSDT(), find)) {
                    result.add(hoadon.get(i));

                }
            }
        }
        
        return result;

    }
    public int timeFree(String maPhong) {
        long a = 0;
        for (int i = 0; i < sroom.size(); i++) {
            if (sroom.get(i).getIdRoom().equalsIgnoreCase(maPhong)) {
                
                if (a == 0) {
                    a = sroom.get(i).getNgayNhan();
                } else
                 if (a > sroom.get(i).getNgayNhan()) {
                        a = sroom.get(i).getNgayNhan();
                    }
            }

        }
        int b = -1;
        if (a != 0) {

            if (-(a - getDate()) < (45 * 24 * 60 * 60 * 1000)) {
                b = 0;
            } else {

                b = (int) ((a - getDate() - 5 * 60 * 1000) / 24 / 60 / 60 / 1000);
                if (b <= 0) {
                    b = -1;
                }
            }

        } else {
            b = 0;
        }

        return b;
    }

    public long getDate() {
        Date d = new Date();

        //parse ngay thang sang dinh dang va chuyen thanh long.
        long time = d.getTime();
        return time;
    }

}
