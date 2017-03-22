package app;

import entity.DichVu;
import entity.HoaDon;
import function.Function;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.DataProcess;

public class ThanhToan extends javax.swing.JFrame {

    private String soPhong;
    private int maHD;
    private long money = 0, ngaytra = 0;
    private Function f = Hotel.f;
    private DataProcess dp = new DataProcess();

    public ThanhToan() {
        initComponents();

    }

    public ThanhToan(int x, int y) {
        initComponents();
        setSize(561, 700);
        setLocation(300, 25);
    }

    public void ThanhToan(String s, int n) {

        if (n == 1) {
            int loaiphong = -1;
            soPhong = s;
            lbSophong.setText(s);

            HoaDon hd = f.findPrintHoadon(s, "", 1);
            maHD = hd.getId();
            lbTen.setText(hd.getTenKH());
            lbNgaynhan.setText(getDate(hd.getNgayNhan()));
            lbNgaytra.setText(getDate());
            ngaytra = getDate(getDate());
            for (int i = 0; i < f.getRoom().size(); i++) {
                if (f.getRoom().get(i).getId().equalsIgnoreCase(s)) {
                    loaiphong = f.getRoom().get(i).getLoaiphong();
                    lbGia.setText(f.getRoom().get(i).getTien() + " VND/Ngày");
                }
            }
            if (loaiphong == 1) {
                lbLoaiphong.setText("Phòng đơn");
            } else if (loaiphong == 2) {
                lbLoaiphong.setText("Phòng đôi");
            }
            if ((getLDate() - hd.getNgayNhan()) / 1000 / 60 / 60 / 24 != 0) {
                lbTime.setText("Thời gian: " + (1 + ((getLDate() - hd.getNgayNhan()) / 1000 / 60 / 60 / 24)) + " ngày");
            } else if ((getLDate() - hd.getNgayNhan()) / 1000 / 60 / 60 == 0) {
                lbTime.setVisible(false);
            } else {
                lbTime.setText("Thời gian: " + (getLDate() - hd.getNgayNhan()) / 1000 / 60 / 60 + " giờ");
            }

            money = (long) f.tongTienPhong(hd.getMaPhong(), getLDate() - hd.getNgayNhan());
            lbTienphong.setText(money + " VND");
        } else {
            lbSophong.setVisible(false);
            lbTen.setVisible(false);
        }

        ArrayList<DichVu> dv = f.listDV(maHD);
        fillData(dv);
        long tiendichvu = 0;
        for (int i = 0; i < dv.size(); i++) {
            DichVu get = dv.get(i);
            tiendichvu = (long) (tiendichvu + get.getMoney());
        }
        lbTienDichvu.setText(tiendichvu + " VND");
        lbTienTong.setText(tiendichvu + money + " VND");

    }

    private void fillData(ArrayList<DichVu> dv) {
        DefaultTableModel model = (DefaultTableModel) tbldichvu.getModel();
        model.getDataVector().clear();
        for (int i = 0; i < dv.size(); i++) {
            DichVu get = dv.get(i);
            Vector row = new Vector();
            row.add(get.getId());
            row.add(get.getName());
            row.add(get.getMoney());
            model.addRow(row);
        }
        tbldichvu.setModel(model);
    }

    public String getDate(long date) {
//        Date d = new Date();
        SimpleDateFormat dinhDangThoiGian = new SimpleDateFormat("HH:mm dd/MM/yyyy");

        return dinhDangThoiGian.format(date);
    }

    public String getDate() {
        Date thoiGian = new Date();
        //Khai bao dinh dang ngay thang
        SimpleDateFormat dinhDangThoiGian = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        //parse ngay thang sang dinh dang va chuyen thanh string.
        String time = dinhDangThoiGian.format(thoiGian.getTime());
        return time;
    }

    public long getLDate() {
        Date d = new Date();

        //parse ngay thang sang dinh dang va chuyen thanh long.
        long time = d.getTime();
//        
        return time;
    }

    public long getDate(String d) {
        Date thoiGian = new Date(d);
        //Khai bao dinh dang ngay thang

        return thoiGian.getTime();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbldichvu = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnThoat = new javax.swing.JButton();
        btnthanhtoan1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lbTen = new javax.swing.JLabel();
        lbSophong = new javax.swing.JLabel();
        lbNgaynhan = new javax.swing.JLabel();
        lbNgaytra = new javax.swing.JLabel();
        lbLoaiphong = new javax.swing.JLabel();
        lbTienphong = new javax.swing.JLabel();
        lbTienDichvu = new javax.swing.JLabel();
        lbTienTong = new javax.swing.JLabel();
        lbLoiTen = new javax.swing.JLabel();
        lbLoiPhong = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbGia = new javax.swing.JLabel();
        lbTime = new javax.swing.JLabel();

        jLabel3.setText("jLabel3");

        jScrollPane1.setViewportView(jEditorPane1);

        setTitle("Thanh Toán");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 61, 370, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setText("THANH TOÁN");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 350, 40));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 0, 0));
        jLabel2.setText("THÔNG TIN PHÒNG ");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 170, 20));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 0, 0));
        jLabel4.setText("THÔNG TIN DỊCH VỤ");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 140, 40));

        tbldichvu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã dịch vụ", "Tên dịch vụ", "Giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbldichvu.getTableHeader().setReorderingAllowed(false);
        tbldichvu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbldichvuKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tbldichvu);
        if (tbldichvu.getColumnModel().getColumnCount() > 0) {
            tbldichvu.getColumnModel().getColumn(0).setPreferredWidth(2);
        }

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 500, 110));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 0, 0));
        jLabel5.setText("TỔNG CHI PHÍ");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, 120, 20));

        jLabel6.setText("CHI PHÍ TỔNG");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 550, 110, 40));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("CHI PHÍ PHÒNG");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 460, 120, 20));

        jLabel8.setText("CHI PHÍ DỊCH VỤ");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 510, 120, 30));

        btnThoat.setText("Thoát");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });
        getContentPane().add(btnThoat, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 610, 100, -1));

        btnthanhtoan1.setText("Thanh Toán");
        btnthanhtoan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthanhtoan1ActionPerformed(evt);
            }
        });
        getContentPane().add(btnthanhtoan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 610, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Tên Khách Hàng     ");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Số Phòng               ");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, 20));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Loại Phòng            ");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Ngày Nhận Phòng   ");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("Ngày Trả Phòng     ");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, 20));

        lbTen.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbTen.setForeground(new java.awt.Color(51, 51, 255));
        lbTen.setText("jLabel9");
        getContentPane().add(lbTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, -1, -1));

        lbSophong.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbSophong.setForeground(new java.awt.Color(51, 51, 255));
        lbSophong.setText("jLabel16");
        getContentPane().add(lbSophong, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, -1, -1));

        lbNgaynhan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbNgaynhan.setForeground(new java.awt.Color(51, 51, 255));
        getContentPane().add(lbNgaynhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, -1, -1));

        lbNgaytra.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbNgaytra.setForeground(new java.awt.Color(51, 51, 255));
        getContentPane().add(lbNgaytra, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, -1, -1));

        lbLoaiphong.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbLoaiphong.setForeground(new java.awt.Color(51, 51, 255));
        getContentPane().add(lbLoaiphong, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, -1, -1));

        lbTienphong.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbTienphong.setForeground(new java.awt.Color(255, 51, 51));
        lbTienphong.setText("0 VND");
        getContentPane().add(lbTienphong, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 460, -1, -1));

        lbTienDichvu.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbTienDichvu.setForeground(new java.awt.Color(255, 51, 51));
        lbTienDichvu.setText("jLabel15");
        getContentPane().add(lbTienDichvu, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 510, -1, -1));

        lbTienTong.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbTienTong.setForeground(new java.awt.Color(204, 0, 0));
        lbTienTong.setText("jLabel16");
        getContentPane().add(lbTienTong, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 560, -1, -1));

        lbLoiTen.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbLoiTen.setForeground(new java.awt.Color(255, 0, 0));
        getContentPane().add(lbLoiTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 100, 30, 20));

        lbLoiPhong.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbLoiPhong.setForeground(new java.awt.Color(255, 0, 0));
        getContentPane().add(lbLoiPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 130, 30, 20));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Giá: ");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 160, -1, -1));

        lbGia.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbGia.setForeground(new java.awt.Color(51, 51, 255));
        getContentPane().add(lbGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 160, -1, -1));

        lbTime.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbTime.setForeground(new java.awt.Color(102, 102, 255));
        getContentPane().add(lbTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 220, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnThoatActionPerformed

    private void btnthanhtoan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthanhtoan1ActionPerformed
        try {

            dp.updateThanhToanHoaDon(maHD, money, ngaytra);
            JOptionPane.showMessageDialog(this, "Thanh toán thành công!");
            dispose();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnthanhtoan1ActionPerformed

    private void tbldichvuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbldichvuKeyReleased

    }//GEN-LAST:event_tbldichvuKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ThanhToan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThanhToan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThanhToan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThanhToan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThanhToan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnthanhtoan1;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbGia;
    private javax.swing.JLabel lbLoaiphong;
    private javax.swing.JLabel lbLoiPhong;
    private javax.swing.JLabel lbLoiTen;
    private javax.swing.JLabel lbNgaynhan;
    private javax.swing.JLabel lbNgaytra;
    private javax.swing.JLabel lbSophong;
    private javax.swing.JLabel lbTen;
    private javax.swing.JLabel lbTienDichvu;
    private javax.swing.JLabel lbTienTong;
    private javax.swing.JLabel lbTienphong;
    private javax.swing.JLabel lbTime;
    private javax.swing.JTable tbldichvu;
    // End of variables declaration//GEN-END:variables

}
