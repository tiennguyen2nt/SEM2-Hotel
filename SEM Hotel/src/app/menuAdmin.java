/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import com.toedter.calendar.JDateChooser;
import entity.DichVu;
import entity.HoaDon;
import function.Function;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static java.lang.Thread.sleep;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import model.DataProcess;

/**
 *
 * @author Tien Nguyen
 */
public class menuAdmin extends javax.swing.JPanel {

    private Timer timer;
    private Function f = Hotel.f;

    /**
     * Creates new form menuAdmin
     */
    public menuAdmin() {
        initComponents();
        setSize(1000, 600);
        getToDay();
        pnCenter.setLayout(new GridLayout(0, 5, 5, 5));
        timer.start();
        room();
        new reloadThread();
        loadData(collectService());
        loadDataHoaDon(f.getHoaDon());
        btnEdit.setEnabled(false);
        btnadd.setEnabled(true);
        btnDelete.setEnabled(false);
        txtId.setEnabled(false);
        setSize(1000, 600);
        jPanel16.setSize(1000, 600);
        btnHdXoa.setVisible(false);
    }

    class reloadThread extends Thread {

        public reloadThread() {
            start();
        }

        public void run() {

            while (true) {
                if (f.reSTT()) {
                    reRoom();
                    loadDataHoaDon(f.getHoaDon());
                }

                try {
                    sleep(500);
                } catch (InterruptedException ex) {
                }
            }
        }
    }

    private void phong(int i, int stt, boolean find) {
        String strSTT = null;
        String color = null;
        String loaiphong = null;
        if (f.getRoom().get(i).getLoaiphong() == 0) {
            loaiphong = "Phòng đơn";
        } else if (f.getRoom().get(i).getLoaiphong() == 1) {
            loaiphong = "Phòng đôi";
        }
         else if (f.getRoom().get(i).getLoaiphong() == 2) {
            loaiphong = "Phòng ba";
        }
        if (stt == 0) {
            color = "#00FF00";
            strSTT = "";
            JPanel p = new JPanel();
            p.setPreferredSize(new Dimension(200, 100));
            p.setBackground(Color.decode(color));
            p.setLayout(new GridLayout(3, 1));
            JLabel l = new JLabel("    Phòng: " + f.getRoom().get(i).getId());
            p.add(l);
            JLabel l1 = new JLabel("    Loại phòng: " + loaiphong);
            p.add(l1);
            JLabel l3 = new JLabel("    Giá Tiền: " + f.getRoom().get(i).getTien() + " VND");
            p.add(l3);

            p.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    SuaPhong s = new SuaPhong(getX() + getWidth() / 2, getY() + getHeight() / 2);
                    s.setVisible(true);
                    s.getMaphong(f.getRoom().get(i).getId());

                }
            });
            pnCenter.add(p);
        }
        if (!find) {
            if (stt == 1) {
                color = "#FAF205";
                strSTT = "Trạng thái: Phòng đặt trước";
                JPanel p = new JPanel();
                p.setPreferredSize(new Dimension(200, 100));
                p.setBackground(Color.decode(color));
                p.setLayout(new GridLayout(3, 1));
                JLabel l = new JLabel("    Phòng: " + f.getRoom().get(i).getId());
                p.add(l);

                JLabel l2 = new JLabel("    " + strSTT);
                p.add(l2);
                p.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                    }
                });
                pnCenter.add(p);
            } else if (stt == 2) {
                color = "#1e28fd";
                strSTT = "Trạng thái: Đang có khách";
                JPanel p = new JPanel();
                p.setPreferredSize(new Dimension(200, 100));
                p.setBackground(Color.decode(color));
                p.setLayout(new GridLayout(3, 1));
                JLabel l = new JLabel("    Phòng: " + f.getRoom().get(i).getId());
                p.add(l);

                JLabel l2 = new JLabel("    " + strSTT);
                p.add(l2);
                p.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                    }
                });
                pnCenter.add(p);
            } else if (stt == 3) {
                color = "#CD0000";
                strSTT = "Trạng thái: Phòng hết thời gian";
                JPanel p = new JPanel();
                p.setPreferredSize(new Dimension(200, 100));
                p.setBackground(Color.decode(color));
                p.setLayout(new GridLayout(3, 1));
                JLabel l = new JLabel("    Phòng: " + f.getRoom().get(i).getId());
                p.add(l);

                JLabel l2 = new JLabel("    " + strSTT);
                p.add(l2);
                p.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                    }
                });
                pnCenter.add(p);
            }
        }

    }

    public void reRoom() {
        pnCenter.removeAll();
        room();
        pnCenter.updateUI();

    }

    private void room() {

        int phongBan = 0;
        int phongTrong = 0;
        int phongDatTruoc = 0;
        int phongHetHan = 0;

        for (int i = 0; i < f.getRoom().size(); i++) {
            int stt = f.getStatesRoom(f.getRoom().get(i).getId());

            phong(i, stt, false);
            if (stt == 0) {
                phongTrong++;
            }
            if (stt == 1) {
                phongDatTruoc++;
            }
            if (stt == 2) {
                phongBan++;
            }
            if (stt == 3) {
                phongHetHan++;
            }

        }

        lbPhongBan.setText("Phòng bận: " + phongBan);
        lbPhongTrong.setText("Phòng trống: " + phongTrong);
        this.phongHetHan.setText("Phòng hết hạn(>20p): " + phongHetHan);
        lbDT.setText("Phòng đặt trước(<30p): " + phongDatTruoc);

    }

//    public void reFindRoom(long tFrom, long tTo) {
//        pnCenter.removeAll();
//        Findroom(tFrom, tTo);
//        pnCenter.updateUI();
//        
//    }
    private void Findroom(long tFrom, long tTo) {
        pnCenter.removeAll();
        int phongBan = 0;
        int phongTrong = 0;

        for (int i = 0; i < f.getRoom().size(); i++) {
            int stt = f.FindStatesRoom(f.getRoom().get(i).getId(), tFrom, tTo);
            phong(i, stt, true);
            if (stt == 0) {
                phongTrong++;
            } else if (stt == 2) {
                phongBan++;
            }

        }
        pnCenter.updateUI();
        lbPhongBan.setText("Phòng bận: " + phongBan);
        lbPhongTrong.setText("Phòng trống: " + phongTrong);
        this.phongHetHan.setText("");
        lbDT.setText("");
        jPanel10.setBackground(Color.decode("#DCDCDC"));
        jPanel11.setBackground(Color.decode("#DCDCDC"));

    }

    private void getToDay() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                String stringDate = dateFormat.format(new Date());
                lbTime.setText("Hiện Tại: " + stringDate);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Find = new javax.swing.JFrame();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        lbNoti = new javax.swing.JLabel();
        pnlService = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jButton13 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        lbTime = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        lbPhongBan = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        lbPhongTrong = new javax.swing.JLabel();
        phongHetHan = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        lbDT = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnCenter = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        lbCTName = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        tfHDFind = new javax.swing.JTextField();
        cbHDFind = new javax.swing.JComboBox<>();
        btnHDFind2 = new javax.swing.JButton();
        jComboBox4 = new javax.swing.JComboBox<>();
        jSeparator7 = new javax.swing.JSeparator();
        pnPhai = new javax.swing.JPanel();
        pnChiTrietHD = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        tfMaHD = new javax.swing.JTextField();
        tfMaPhong = new javax.swing.JTextField();
        tfTenKH = new javax.swing.JTextField();
        tfCMND = new javax.swing.JTextField();
        tfSDT = new javax.swing.JTextField();
        tfNgayNhan = new javax.swing.JTextField();
        tfNgayTra = new javax.swing.JTextField();
        btnCtDV = new javax.swing.JButton();
        lbTienDv = new javax.swing.JLabel();
        lbTienPhong = new javax.swing.JLabel();
        lbThanhToan = new javax.swing.JLabel();
        btnHdSua = new javax.swing.JButton();
        btnHdXoa = new javax.swing.JButton();
        pnChitrietDV = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbChiTietDV = new javax.swing.JTable();
        lbTTienDV = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbHoaDon = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtNote = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        txtPrice = new javax.swing.JTextField();
        btnEdit = new javax.swing.JButton();
        btnadd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        tfFindDV = new javax.swing.JTextField();
        btnFindDV = new javax.swing.JButton();
        chbProgress = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblService = new javax.swing.JTable();

        Find.setTitle("Tìm Phòng");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Từ:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Đến:");

        jDateChooser2.setDateFormatString("dd/MM/yyyy");

        jDateChooser1.setDateFormatString("dd/MM/yyyy");
        jDateChooser1.setPreferredSize(new java.awt.Dimension(300, 25));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Tìm phòng");

        jButton9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton9.setText("Tìm");
        jButton9.setPreferredSize(new java.awt.Dimension(90, 30));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        lbNoti.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        lbNoti.setForeground(new java.awt.Color(255, 0, 0));
        lbNoti.setPreferredSize(new java.awt.Dimension(390, 20));

        javax.swing.GroupLayout FindLayout = new javax.swing.GroupLayout(Find.getContentPane());
        Find.getContentPane().setLayout(FindLayout);
        FindLayout.setHorizontalGroup(
            FindLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FindLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(FindLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(FindLayout.createSequentialGroup()
                        .addGroup(FindLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(FindLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbNoti, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(55, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FindLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(201, 201, 201))
        );

        FindLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jDateChooser1, jDateChooser2});

        FindLayout.setVerticalGroup(
            FindLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FindLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(FindLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(FindLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbNoti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        FindLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jDateChooser1, jDateChooser2});

        setLayout(new java.awt.GridLayout(1, 0));

        pnlService.setPreferredSize(new java.awt.Dimension(1000, 1168));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jToolBar1.setFloatable(false);
        jToolBar1.setAlignmentY(1.0F);

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/file add.png"))); // NOI18N
        jButton13.setText("Thêm phòng");
        jButton13.setFocusable(false);
        jButton13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton13.setMargin(new java.awt.Insets(2, 20, 2, 14));
        jButton13.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton13);

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/file_delete.png"))); // NOI18N
        jButton11.setText("Xóa phòng");
        jButton11.setFocusable(false);
        jButton11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton11.setMargin(new java.awt.Insets(2, 20, 2, 14));
        jButton11.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton11);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Search.png"))); // NOI18N
        jButton3.setText("Tìm Phòng");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setMargin(new java.awt.Insets(2, 20, 2, 20));
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton3);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Restart-64.png"))); // NOI18N
        jButton1.setText("Cập nhật");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setMargin(new java.awt.Insets(2, 20, 2, 14));
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);
        jToolBar1.add(jSeparator2);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Exit-64.png"))); // NOI18N
        jButton6.setText("Đăng xuất");
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setMargin(new java.awt.Insets(2, 20, 2, 20));
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton6);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Delete-64.png"))); // NOI18N
        jButton7.setText("Thoát");
        jButton7.setFocusable(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setMargin(new java.awt.Insets(2, 20, 2, 20));
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton7);

        lbTime.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbTime.setForeground(new java.awt.Color(0, 0, 255));
        lbTime.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbTime.setText("jLabel2");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbTime, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 75, Short.MAX_VALUE)
                .addComponent(lbTime, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 951, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(jPanel4, java.awt.BorderLayout.PAGE_START);

        jPanel5.setPreferredSize(new java.awt.Dimension(1275, 40));

        jPanel7.setBackground(new java.awt.Color(0, 0, 255));
        jPanel7.setPreferredSize(new java.awt.Dimension(30, 30));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        lbPhongBan.setText("jLabel1");

        jPanel9.setBackground(new java.awt.Color(0, 255, 15));
        jPanel9.setPreferredSize(new java.awt.Dimension(30, 30));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        lbPhongTrong.setText("jLabel1");

        phongHetHan.setText("jLabel1");

        jPanel10.setBackground(new java.awt.Color(255, 51, 51));
        jPanel10.setPreferredSize(new java.awt.Dimension(30, 30));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        lbDT.setText("jLabel1");

        jPanel11.setBackground(new java.awt.Color(255, 255, 51));
        jPanel11.setPreferredSize(new java.awt.Dimension(30, 30));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbPhongBan)
                .addGap(214, 214, 214)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbPhongTrong)
                .addGap(221, 221, 221)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(phongHetHan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 398, Short.MAX_VALUE)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbDT)
                .addGap(152, 152, 152))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phongHetHan)
                    .addComponent(lbDT)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPhongTrong)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPhongBan)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1.add(jPanel5, java.awt.BorderLayout.PAGE_END);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setAutoscrolls(true);
        jScrollPane1.setFocusCycleRoot(true);
        jScrollPane1.setHorizontalScrollBar(null);
        jScrollPane1.setMaximumSize(new java.awt.Dimension(1275, 1000));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(1280, 1000));

        pnCenter.setBackground(new java.awt.Color(255, 255, 255));
        pnCenter.setAutoscrolls(true);
        pnCenter.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnCenter.setMaximumSize(new java.awt.Dimension(1000, 1000));
        pnCenter.setLayout(new java.awt.GridLayout(5, 6, 5, 5));
        jScrollPane1.setViewportView(pnCenter);

        jPanel6.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel6, java.awt.BorderLayout.CENTER);

        pnlService.addTab("Hệ Thống", jPanel1);

        jPanel14.setPreferredSize(new java.awt.Dimension(1000, 562));
        jPanel14.setLayout(new java.awt.GridLayout(1, 0));

        jPanel15.setLayout(new java.awt.BorderLayout());

        jPanel16.setBackground(new java.awt.Color(248, 248, 248));
        jPanel16.setToolTipText("");
        jPanel16.setPreferredSize(new java.awt.Dimension(1000, 90));
        jPanel16.setLayout(new java.awt.GridLayout(1, 0));

        jPanel20.setLayout(new java.awt.BorderLayout());

        jPanel21.setBackground(new java.awt.Color(248, 248, 248));
        jPanel21.setPreferredSize(new java.awt.Dimension(310, 90));

        lbCTName.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbCTName.setForeground(new java.awt.Color(0, 51, 255));
        lbCTName.setText("CHI TIẾT HÓA ĐƠN");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(lbCTName)
                .addContainerGap(83, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addComponent(lbCTName)
                .addContainerGap())
        );

        jPanel20.add(jPanel21, java.awt.BorderLayout.LINE_END);

        jPanel22.setBackground(new java.awt.Color(248, 248, 248));

        tfHDFind.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfHDFindKeyReleased(evt);
            }
        });

        cbHDFind.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã hóa đơn", "Mã phòng", "Tên khách hàng", "CMND", "SĐT" }));
        cbHDFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbHDFindActionPerformed(evt);
            }
        });

        btnHDFind2.setText("Tìm kiếm");
        btnHDFind2.setEnabled(false);
        btnHDFind2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHDFind2ActionPerformed(evt);
            }
        });
        btnHDFind2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnHDFind2KeyReleased(evt);
            }
        });

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Trạng thái tất cả", "Đã thanh toán", "Chưa thanh toán" }));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });
        jComboBox4.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jComboBox4PropertyChange(evt);
            }
        });

        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(tfHDFind, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(cbHDFind, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btnHDFind2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 246, Short.MAX_VALUE)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfHDFind, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbHDFind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHDFind2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jSeparator7, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jPanel22Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnHDFind2, cbHDFind, tfHDFind});

        jPanel20.add(jPanel22, java.awt.BorderLayout.CENTER);

        jPanel16.add(jPanel20);

        jPanel15.add(jPanel16, java.awt.BorderLayout.PAGE_START);

        pnPhai.setBackground(new java.awt.Color(248, 248, 248));
        pnPhai.setPreferredSize(new java.awt.Dimension(320, 472));
        pnPhai.setLayout(new java.awt.CardLayout());

        pnChiTrietHD.setBackground(new java.awt.Color(248, 248, 248));
        pnChiTrietHD.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Mã HD:");
        pnChiTrietHD.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Mã Phòng:");
        pnChiTrietHD.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Tên KH:");
        pnChiTrietHD.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("CMND:");
        pnChiTrietHD.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("SĐT:");
        pnChiTrietHD.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Ngày nhận:");
        pnChiTrietHD.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("Ngày Trả:");
        pnChiTrietHD.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("Dịch vụ:");
        pnChiTrietHD.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, -1, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("Tiền dịch vụ:");
        pnChiTrietHD.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, -1, -1));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("Tiền Phòng:");
        pnChiTrietHD.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, -1, -1));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setText("Thanh Toán:");
        pnChiTrietHD.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, -1, -1));

        tfMaHD.setEditable(false);
        tfMaHD.setEnabled(false);
        pnChiTrietHD.add(tfMaHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 180, 25));

        tfMaPhong.setEditable(false);
        tfMaPhong.setEnabled(false);
        tfMaPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfMaPhongActionPerformed(evt);
            }
        });
        pnChiTrietHD.add(tfMaPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 180, 25));

        tfTenKH.setEditable(false);
        tfTenKH.setPreferredSize(new java.awt.Dimension(6, 25));
        pnChiTrietHD.add(tfTenKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 180, 25));

        tfCMND.setEditable(false);
        pnChiTrietHD.add(tfCMND, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 180, 25));
        tfCMND.getAccessibleContext().setAccessibleName("");

        tfSDT.setEditable(false);
        pnChiTrietHD.add(tfSDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 180, 25));

        tfNgayNhan.setEditable(false);
        tfNgayNhan.setEnabled(false);
        pnChiTrietHD.add(tfNgayNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, 180, 25));

        tfNgayTra.setEditable(false);
        tfNgayTra.setEnabled(false);
        pnChiTrietHD.add(tfNgayTra, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 240, 180, 25));

        btnCtDV.setText("Xem chi tiết dịch vụ");
        btnCtDV.setEnabled(false);
        btnCtDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCtDVActionPerformed(evt);
            }
        });
        pnChiTrietHD.add(btnCtDV, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 280, 180, -1));

        lbTienDv.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        lbTienDv.setForeground(new java.awt.Color(0, 0, 255));
        lbTienDv.setText("0 VND");
        pnChiTrietHD.add(lbTienDv, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 320, 190, -1));

        lbTienPhong.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        lbTienPhong.setForeground(new java.awt.Color(0, 0, 255));
        lbTienPhong.setText("0 VND");
        pnChiTrietHD.add(lbTienPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 350, 200, -1));

        lbThanhToan.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        lbThanhToan.setForeground(new java.awt.Color(255, 0, 51));
        lbThanhToan.setText("0");
        pnChiTrietHD.add(lbThanhToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 380, 210, -1));

        btnHdSua.setText("Sửa");
        btnHdSua.setEnabled(false);
        btnHdSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHdSuaActionPerformed(evt);
            }
        });
        pnChiTrietHD.add(btnHdSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 410, 74, -1));

        btnHdXoa.setText("Xóa");
        btnHdXoa.setEnabled(false);
        btnHdXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHdXoaActionPerformed(evt);
            }
        });
        pnChiTrietHD.add(btnHdXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 410, 74, -1));

        pnPhai.add(pnChiTrietHD, "hd");

        pnChitrietDV.setBackground(new java.awt.Color(248, 248, 248));

        tbChiTietDV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Dịch vụ", "Tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbChiTietDV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbChiTietDVMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbChiTietDV);

        lbTTienDV.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        lbTTienDV.setForeground(new java.awt.Color(255, 0, 51));
        lbTTienDV.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbTTienDV.setText("Tổng tiền dịch vụ:");

        jButton10.setText("< Back");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnChitrietDVLayout = new javax.swing.GroupLayout(pnChitrietDV);
        pnChitrietDV.setLayout(pnChitrietDVLayout);
        pnChitrietDVLayout.setHorizontalGroup(
            pnChitrietDVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnChitrietDVLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnChitrietDVLayout.createSequentialGroup()
                .addGap(0, 52, Short.MAX_VALUE)
                .addGroup(pnChitrietDVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton10)
                    .addComponent(lbTTienDV, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnChitrietDVLayout.setVerticalGroup(
            pnChitrietDVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnChitrietDVLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbTTienDV)
                .addGap(29, 29, 29)
                .addComponent(jButton10)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        pnPhai.add(pnChitrietDV, "dv");

        jPanel15.add(pnPhai, java.awt.BorderLayout.LINE_END);

        jPanel18.setLayout(new java.awt.GridLayout(1, 0));

        tbHoaDon.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tbHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã HD", "Phòng", "Tên Khách Hàng", "CMND", "SDT", "Ngày Nhận", "Ngày Trả", "Thanh Toán (VND)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbHoaDon.getTableHeader().setReorderingAllowed(false);
        tbHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHoaDonMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbHoaDon);
        if (tbHoaDon.getColumnModel().getColumnCount() > 0) {
            tbHoaDon.getColumnModel().getColumn(0).setPreferredWidth(1);
            tbHoaDon.getColumnModel().getColumn(1).setPreferredWidth(1);
            tbHoaDon.getColumnModel().getColumn(3).setPreferredWidth(1);
            tbHoaDon.getColumnModel().getColumn(4).setPreferredWidth(1);
        }

        jPanel18.add(jScrollPane2);

        jPanel15.add(jPanel18, java.awt.BorderLayout.CENTER);

        jPanel14.add(jPanel15);

        pnlService.addTab("Quản lý hóa đơn", jPanel14);

        jPanel12.setLayout(new java.awt.GridLayout(1, 0));

        jPanel13.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(248, 248, 248));
        jPanel2.setPreferredSize(new java.awt.Dimension(340, 447));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNote.setEditable(false);
        txtNote.setColumns(14);
        txtNote.setRows(5);
        jScrollPane4.setViewportView(txtNote);

        jPanel2.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 330, -1, -1));

        jLabel11.setText("Ghi chú");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, -1, -1));

        jLabel12.setText("Trạng thái");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, -1, -1));

        jLabel13.setText("Đơn giá");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, -1, -1));

        jLabel14.setText("Tên");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, -1, -1));

        jLabel15.setText("ID");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));
        jPanel2.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, 166, -1));

        txtName.setEditable(false);
        jPanel2.add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 210, 166, -1));

        txtPrice.setEditable(false);
        jPanel2.add(txtPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 250, 166, -1));

        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/edit.png"))); // NOI18N
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        jPanel2.add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 450, 67, 64));

        btnadd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Add List Filled-64.png"))); // NOI18N
        btnadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddActionPerformed(evt);
            }
        });
        jPanel2.add(btnadd, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, 67, 64));

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/delete.png"))); // NOI18N
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel2.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 450, -1, 64));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 255));
        jLabel16.setText("CHI TIẾT DỊCH VỤ");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, -1, -1));
        jPanel2.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 260, 10));
        jPanel2.add(tfFindDV, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 290, 30));

        btnFindDV.setText("Tìm kiếm");
        btnFindDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindDVActionPerformed(evt);
            }
        });
        jPanel2.add(btnFindDV, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, -1, -1));

        chbProgress.setText("Khả dụng");
        jPanel2.add(chbProgress, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 290, -1, -1));

        jPanel13.add(jPanel2, java.awt.BorderLayout.LINE_END);

        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        tblService.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tên", "Đơn giá", "Trạng thái", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblService.getTableHeader().setReorderingAllowed(false);
        tblService.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tblServiceFocusGained(evt);
            }
        });
        tblService.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblServiceMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblService);

        jPanel3.add(jScrollPane5);

        jPanel13.add(jPanel3, java.awt.BorderLayout.CENTER);

        jPanel12.add(jPanel13);

        pnlService.addTab("Quản lý dịch vụ", jPanel12);

        add(pnlService);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Find.setVisible(true);
        Find.setSize(552, 300);
        Find.setAlwaysOnTop(true);
        Find.setLocation(getX() + getWidth() / 2 - Find.getWidth() / 2, getY() + getHeight() / 2 - Find.getHeight() / 2);
        Find.setResizable(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        CardLayout c = (CardLayout) getParent().getLayout();
        c.show(getParent(), "wel");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        lbNoti.setText("");

        long tFrom = 0;
        long tTo = 0;

        try {
            tFrom = getDate(jDateChooser1);
        } catch (Exception e) {
            lbNoti.setText("Thời gian không được bỏ trống!");
        }
        try {
            tTo = getDate(jDateChooser2) + 12 * 60 * 60 * 1000;
        } catch (Exception e) {
            lbNoti.setText("Thời gian không được bỏ trống!");
        }
        if (pareDayNow(tFrom)) {
            tFrom = getDate();

        } else {
            tFrom += 12 * 60 * 60 * 1000;
        }

        if (tFrom > 0) {
            if (tFrom > (getDate() - 5 * 60 * 1000)) {
                if (tTo > 0) {
                    if (tTo < tFrom) {
                        lbNoti.setText("Thời gian đến phải nhỏ hơn thời gian trả!");
                    } else {
                        Findroom(tFrom, tTo);
                        jDateChooser1.setCalendar(null);
                        jDateChooser2.setCalendar(null);
                        Find.setVisible(false);
                    }
                }
            } else {
                lbNoti.setText("Thời gian đến phải lớn hơn thời gian hiện tại!");
            }
        }

    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        ThemPhong t = new ThemPhong(getX() + getWidth() / 2, getY() + getHeight() / 2);
        t.setVisible(true);
    }//GEN-LAST:event_jButton13ActionPerformed
    boolean a = true;
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        reRoom();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        XoaPhong x = new XoaPhong();
        x.setVisible(true);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void tblServiceFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tblServiceFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tblServiceFocusGained

    private void tblServiceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblServiceMouseClicked
        txtName.setEditable(true);
        txtPrice.setEditable(true);
        txtNote.setEditable(true);
        btnEdit.setEnabled(true);
        btnadd.setEnabled(false);
        btnDelete.setEnabled(true);
        int row = tblService.getSelectedRow();
        ArrayList<DichVu> list = collectService();
        txtId.setText(list.get(row).getId() + "");
        txtName.setText(list.get(row).getName());
        txtPrice.setText(list.get(row).getMoney() + "");
        if (list.get(row).getStatus() == 1) {
            chbProgress.setSelected(true);
        } else {
            chbProgress.setSelected(false);
        }
        txtNote.setText(list.get(row).getNote());
    }//GEN-LAST:event_tblServiceMouseClicked

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        DataProcess dat = new DataProcess();
        int id = Integer.parseInt(txtId.getText());
        String name = txtName.getText();
        float price = Float.parseFloat(txtPrice.getText());
        int status = 0;
        if (chbProgress.isSelected()) {
            status = 1;
        }
        String note = txtNote.getText();
        int result = 0;
        String sql = "EXEC dbo.editService @id = " + id + ",@name = N'" + name + "',@price = " + price + ",@status = " + status + ",@note = N'" + note + "'";
        try {
            Statement st = dat.getConnection().createStatement();
            result = st.executeUpdate(sql);
            if (result > 0) {
                loadData(collectService());
                JOptionPane.showMessageDialog(this, "Thành công");
                txtName.setText("");
                txtPrice.setText("");
                txtNote.setText("");
                txtId.setText("");
                btnEdit.setEnabled(false);
                btnadd.setEnabled(true);
                btnDelete.setEnabled(false);
                txtName.setEditable(false);
                txtPrice.setEditable(false);
                txtNote.setEditable(false);
            } else {
                JOptionPane.showMessageDialog(this, "Thất bại");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Thất bại, lỗi " + ex.toString());
        }
        a = true;
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
        if (a) {
            txtId.setEnabled(false);
            txtName.setEditable(true);
            txtPrice.setEditable(true);
            txtNote.setEditable(true);
            a = false;
        } else {
            try {
                DataProcess data = new DataProcess();
                int result = 0;
                String name = txtName.getText();
                float price = Float.parseFloat(txtPrice.getText());
                String note = null;
                note = txtNote.getText();
                String sql = "INSERT INTO DichVu VALUES(?,?,?,?)";

                PreparedStatement ps = data.getConnection().prepareStatement(sql);
                ps.setString(1, name);
                ps.setFloat(2, price);
                int ready=0;
                if(chbProgress.isSelected()) ready=1;
                ps.setInt(3, ready);
                ps.setString(4, note);
                result = ps.executeUpdate();
                ps.close();
                if (result > 0) {
                    JOptionPane.showMessageDialog(this, "Thêm thành công");
                    txtName.setText("");
                    txtPrice.setText("");
                    txtNote.setText("");
                    chbProgress.setSelected(false);
                    loadData(collectService());
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm thất bại");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Thêm thất bại, lỗi" + ex.toString());
            }
            txtName.setEditable(false);
            txtPrice.setEditable(false);
//            txtId.setEnabled(true);
            txtId.setEditable(false);
            txtNote.setEditable(false);
            a = true;
        }
    }//GEN-LAST:event_btnaddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        a = true;
        int result = 0;
        int id = Integer.parseInt(txtId.getText());
        DataProcess dat = new DataProcess();
        String sql = "EXEC dbo.deleteService @id = " + id;
        try {
            Statement st = dat.getConnection().createStatement();
            result = st.executeUpdate(sql);
            if (result > 0) {
                loadData(collectService());
                JOptionPane.showMessageDialog(this, "Thành công");
                txtName.setText("");
                txtPrice.setText("");
                txtNote.setText("");
                txtId.setText("");
                btnEdit.setEnabled(false);
                btnadd.setEnabled(true);
                btnDelete.setEnabled(false);
                txtName.setEditable(false);
                txtPrice.setEditable(false);
                txtNote.setEditable(false);
            } else {
                JOptionPane.showMessageDialog(this, "Thất bại");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Thất bại, lỗi " + ex.toString());
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnFindDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindDVActionPerformed
        ArrayList<DichVu> dv = collectService();
        ArrayList<DichVu> fdv = new ArrayList<>();
        String find = tfFindDV.getText();
        int fd = -1;
        try {
            fd = Integer.parseInt(find);
        } catch (Exception e) {

        }
        for (int i = 0; i < dv.size(); i++) {
            DichVu get = dv.get(i);
            if (f.findString(get.getName(), find)) {
                fdv.add(get);
            }
        }
        if (fd != -1) {
            for (int i = 0; i < dv.size(); i++) {
                DichVu get = dv.get(i);
                if (get.getId() == fd) {
                    fdv.add(get);
                }
            }
        }
        if (fdv.size() > 0) {
            loadData(fdv);
        } else {
            loadData(collectService());
            JOptionPane.showMessageDialog(this, "Không tìm thấy \"" + find + "\" trong dịch vụ");
        }
        txtName.setText("");
        txtPrice.setText("");
        txtNote.setText("");

        txtId.setText("");
        btnEdit.setEnabled(false);
        btnadd.setEnabled(true);
        btnDelete.setEnabled(false);
        txtName.setEditable(false);
        txtPrice.setEditable(false);

        txtNote.setEditable(false);
    }//GEN-LAST:event_btnFindDVActionPerformed

    private void tfMaPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfMaPhongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfMaPhongActionPerformed

    private void tbHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHoaDonMouseClicked
        int row = tbHoaDon.getSelectedRow();
        tfCMND.setText(f.getHoaDon().get(row).getCMND());
        tfMaHD.setText(f.getHoaDon().get(row).getId() + "");
        tfMaPhong.setText(f.getHoaDon().get(row).getMaPhong());
        tfNgayNhan.setText(getDate(f.getHoaDon().get(row).getNgayNhan()));
        tfNgayTra.setText(getDate(f.getHoaDon().get(row).getNgayTra()));
        tfSDT.setText(f.getHoaDon().get(row).getSDT());
        tfTenKH.setText(f.getHoaDon().get(row).getTenKH());
        if (f.getHoaDon().get(row).getThanhtoan() == 0) {
            lbThanhToan.setText("Chưa thanh toán");
        } else {
            lbThanhToan.setText(f.getHoaDon().get(row).getThanhtoan() + " VND");
        }
        btnHdSua.setEnabled(true);
        btnCtDV.setEnabled(true);
        btnHdXoa.setEnabled(true);
        tfTenKH.setEditable(true);
        tfCMND.setEditable(true);
        tfSDT.setEditable(true);
        long tien = (long) f.tongTienPhong(f.getHoaDon().get(row).getMaPhong(), getDate() - f.getHoaDon().get(row).getNgayNhan());
        if(tien < 0)
            tien = 0;
        lbTienPhong.setText( tien+ " VND");

        ArrayList<DichVu> dv = f.listDV(f.getHoaDon().get(row).getId());
        long tiendichvu = 0;
        for (int i = 0; i < dv.size(); i++) {
            DichVu get = dv.get(i);
            tiendichvu = (long) (tiendichvu + get.getMoney());
        }
        lbTienDv.setText(tiendichvu + " VND");
        lbTTienDV.setText("Tổng tiền: " + tiendichvu + " VND");
        loadDataChiTietDV(dv);
        if (dv.size() == 0) {

            tbChiTietDV.removeAll();
        }

    }//GEN-LAST:event_tbHoaDonMouseClicked

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        CardLayout c = (CardLayout) pnPhai.getLayout();
        c.show(pnPhai, "hd");
        lbCTName.setText("CHI TIẾT HÓA ĐƠN");
    }//GEN-LAST:event_jButton10ActionPerformed

    private void btnCtDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCtDVActionPerformed
        CardLayout c = (CardLayout) pnPhai.getLayout();
        c.show(pnPhai, "dv");
        lbCTName.setText("CHI TIẾT DỊCH VỤ");
    }//GEN-LAST:event_btnCtDVActionPerformed

    private void tfHDFindKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfHDFindKeyReleased
        if (tfHDFind.getText().equals("")) {
            btnHDFind2.setEnabled(false);
        } else {
            btnHDFind2.setEnabled(true);
        }
    }//GEN-LAST:event_tfHDFindKeyReleased

    private void btnHDFind2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHDFind2ActionPerformed
        String pattern = (String) cbHDFind.getSelectedItem();
        String find = tfHDFind.getText().trim();
        if (find != null) {
            loadDataHoaDon(f.findInHoaDon(find, pattern));
        }
        btnHDFind2.setEnabled(false);
    }//GEN-LAST:event_btnHDFind2ActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        int n = jComboBox4.getSelectedIndex();
        if (n == 0) {
            loadDataHoaDon(f.getHoaDon());
        } else if (n == 1) {
            ArrayList<HoaDon> hd = new ArrayList<>();
            for (int i = 0; i < f.getHoaDon().size(); i++) {
                HoaDon get = f.getHoaDon().get(i);
                if (get.getThanhtoan() > 0) {
                    hd.add(get);
                }
            }
            loadDataHoaDon(hd);
        } else if (n == 2) {
            ArrayList<HoaDon> hd = new ArrayList<>();
            for (int i = 0; i < f.getHoaDon().size(); i++) {
                HoaDon get = f.getHoaDon().get(i);
                if (get.getThanhtoan() == 0) {
                    hd.add(get);
                }
            }
            loadDataHoaDon(hd);
        }
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jComboBox4PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jComboBox4PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox4PropertyChange

    private void cbHDFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbHDFindActionPerformed
        btnHDFind2.setEnabled(true);
    }//GEN-LAST:event_cbHDFindActionPerformed

    private void btnHDFind2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnHDFind2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHDFind2KeyReleased

    private void btnHdSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHdSuaActionPerformed
        DataProcess d = new DataProcess();
        if (d.updateItemHD(Integer.parseInt(tfMaHD.getText().trim()), tfTenKH.getText(), tfCMND.getText(), tfSDT.getText())) {
            JOptionPane.showMessageDialog(this, "Sửa hóa đơn thành công");
            loadDataHoaDon(f.getHoaDon());
        } else {
            JOptionPane.showMessageDialog(this, "Lỗi - Không thể xóa hóa đơn", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnHdSuaActionPerformed

    private void tbChiTietDVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbChiTietDVMouseClicked

        int rowdv = tbHoaDon.getSelectedRow();


    }//GEN-LAST:event_tbChiTietDVMouseClicked

    private void btnHdXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHdXoaActionPerformed
        DataProcess d = new DataProcess();
        if (d.deleteItemHD(Integer.parseInt(tfMaHD.getText().trim()))) {
            JOptionPane.showMessageDialog(this, "Xóa thành công");
            loadDataHoaDon(f.getHoaDon());
        } else {
            JOptionPane.showMessageDialog(this, "Lỗi - Không thể xóa", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnHdXoaActionPerformed
    public long getDate(JDateChooser date) {

        //parse ngay thang sang dinh dang va chuyen thanh long.
        long time = date.getDate().getTime();
        return time;
    }

    public String getDate(long date) {
//        Date d = new Date();
        SimpleDateFormat dinhDangThoiGian = new SimpleDateFormat("HH:mm dd/MM/yyyy");

        return dinhDangThoiGian.format(date);
    }

    private void loadDataHoaDon(ArrayList<HoaDon> list) {
        DefaultTableModel model = (DefaultTableModel) tbHoaDon.getModel();
        model.getDataVector().clear();
        for (HoaDon p : list) {
            Vector v = new Vector();
            v.add(p.getId());
            v.add(p.getMaPhong());
            v.add(p.getTenKH());
            v.add(p.getCMND());
            v.add(p.getSDT());
            v.add(getDate(p.getNgayNhan()));
            v.add(getDate(p.getNgayTra()));
            if (p.getThanhtoan() == 0) {
                v.add("Chưa thanh toán");
            } else {
                v.add(p.getThanhtoan());
            }

            model.addRow(v);
        }
        tbHoaDon.setModel(model);
    }

    private void loadDataChiTietDV(ArrayList<DichVu> list) {
        DefaultTableModel model = (DefaultTableModel) tbChiTietDV.getModel();
        model.getDataVector().clear();
        for (DichVu p : list) {
            Vector v = new Vector();
            v.add(p.getName());
            v.add(p.getMoney());

            model.addRow(v);
        }
        tbChiTietDV.setModel(model);
    }

    public long getDate() {
        Date d = new Date();
//        SimpleDateFormat dinhDangThoiGian = new SimpleDateFormat("yyyy-MM-dd");
//        Date d2 = new Date(dinhDangThoiGian.format(d.getTime()));
        long time = d.getTime();
        return time;
    }

    private static ArrayList<DichVu> collectService() {
        DataProcess data = new DataProcess();
        String sql = "EXEC dbo.collectServiceData";
        ArrayList<DichVu> list = new ArrayList();
        try {
            Statement st = data.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                DichVu dv = new DichVu();
                dv.setId(rs.getInt(1));
                dv.setName(rs.getString(2));
                dv.setMoney(rs.getInt(3));
                dv.setStatus(rs.getInt(4));
                dv.setNote(rs.getString(5));
                list.add(dv);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(menuAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    private void loadData(ArrayList<DichVu> list) {
        DefaultTableModel model = (DefaultTableModel) tblService.getModel();
        model.getDataVector().clear();
        for (DichVu p : list) {
            Vector v = new Vector();
            v.add(p.getId());
            v.add(p.getName());
            v.add(p.getMoney());
            String stt = null;
            if(p.getStatus() ==0)
                stt = "Không sẵn sàng";
           else if(p.getStatus() ==1)
                stt = "Sẵn sàng";
            
            v.add(stt);
            v.add(p.getNote());
            model.addRow(v);
        }
        tblService.setModel(model);
    }

    private boolean pareDayNow(long d1) {
        Date d = new Date();
        SimpleDateFormat dinhDangThoiGian = new SimpleDateFormat("dd/MM/yyyy");
        String t1 = dinhDangThoiGian.format(d.getTime());
        String t2 = dinhDangThoiGian.format(d1);
//        System.out.println(t1 + " - " + t2 + " kkkkkkkkkkkkkkkkkk");
        if (t1.equalsIgnoreCase(t2)) {
            return true;
        }

        return false;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame Find;
    private javax.swing.JButton btnCtDV;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnFindDV;
    private javax.swing.JButton btnHDFind2;
    private javax.swing.JButton btnHdSua;
    private javax.swing.JButton btnHdXoa;
    private javax.swing.JButton btnadd;
    private javax.swing.JComboBox<String> cbHDFind;
    private javax.swing.JCheckBox chbProgress;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox4;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lbCTName;
    private javax.swing.JLabel lbDT;
    private javax.swing.JLabel lbNoti;
    private javax.swing.JLabel lbPhongBan;
    private javax.swing.JLabel lbPhongTrong;
    private javax.swing.JLabel lbTTienDV;
    private javax.swing.JLabel lbThanhToan;
    private javax.swing.JLabel lbTienDv;
    private javax.swing.JLabel lbTienPhong;
    private javax.swing.JLabel lbTime;
    private javax.swing.JLabel phongHetHan;
    public javax.swing.JPanel pnCenter;
    private javax.swing.JPanel pnChiTrietHD;
    private javax.swing.JPanel pnChitrietDV;
    private javax.swing.JPanel pnPhai;
    private javax.swing.JTabbedPane pnlService;
    private javax.swing.JTable tbChiTietDV;
    private javax.swing.JTable tbHoaDon;
    private javax.swing.JTable tblService;
    private javax.swing.JTextField tfCMND;
    private javax.swing.JTextField tfFindDV;
    private javax.swing.JTextField tfHDFind;
    private javax.swing.JTextField tfMaHD;
    private javax.swing.JTextField tfMaPhong;
    private javax.swing.JTextField tfNgayNhan;
    private javax.swing.JTextField tfNgayTra;
    private javax.swing.JTextField tfSDT;
    private javax.swing.JTextField tfTenKH;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextArea txtNote;
    private javax.swing.JTextField txtPrice;
    // End of variables declaration//GEN-END:variables
}
