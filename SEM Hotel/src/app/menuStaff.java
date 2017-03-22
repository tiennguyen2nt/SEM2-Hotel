/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import com.toedter.calendar.JDateChooser;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import model.DataProcess;

/**
 *
 * @author Tien Nguyen
 */
public class menuStaff extends javax.swing.JPanel {

    private Timer timer;
    private long NgayNhan = 0, NgayTra = 0;
    /**
     * Creates new form menuStaff
     */
    Function f = new Function();

    public menuStaff() {
        initComponents();
        setSize(1000, 600);
        jTabbedPane1.setSize(1050, 600);
        jScrollPane1.setSize(1050, 600);
        jPanel1.setSize(1050, 600);
        pnCenter.setSize(1050, 600);
        pnCenter.setLayout(new GridLayout(0, 5, 5, 5));
        getToDay();
        timer.start();
        room();
        new reloadThread();

    }

    class reloadThread extends Thread {

        public reloadThread() {
            start();
        }

        public void run() {

            while (true) {
                if (f.reSTT()) {
                    reRoom();

                    NgayNhan = 0;
                    NgayTra = 0;
                }

                try {
                    sleep(1500);
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
        } else if (f.getRoom().get(i).getLoaiphong() == 2) {
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

                    Dphong dp = new Dphong(getX() + getWidth() / 2, getY() + getHeight() / 2);
                    dp.setVisible(true);
                    String str = l.getText().trim();
                    String c[] = str.split(" ");
                    dp.getPhong(c[1], NgayNhan, NgayTra);

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

//                    datphong dp = new datphong(getX() + getWidth() / 2, getY() + getHeight() / 2);
//                    dp.setVisible(true);
//                    String str = l.getText().trim();
//                    String c[] = str.split(" ");
//                    dp.getPhong(Integer.parseInt(c[1]));
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
//                long traphong = 0;
//                for (int j = 0; j < f.getHoaDon().size(); j++) {
//                    if (f.getHoaDon().get(i).getMaPhong().equalsIgnoreCase(f.getRoom().get(i).getId())) {
//                        if (traphong == 0) {
//                            traphong = f.getHoaDon().get(i).getNgayTra();
//                        } else if (traphong > f.getHoaDon().get(i).getNgayTra()) {
//                            traphong = f.getHoaDon().get(i).getNgayTra();
//                        }
//                    }
//                }
//                JLabel l3 = new JLabel("    " + getDate(traphong));
//                p.add(l3);
                p.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (!find) {
                            ThanhToan t = new ThanhToan(getX() + getWidth() / 2, getY() + getHeight() / 2);
                            t.setVisible(true);
                            String str = l.getText().trim();
                            String c[] = str.split(" ");
                            t.ThanhToan(c[1], 1);
                        }
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
                        if (!find) {
                            ThanhToan t = new ThanhToan(getX() + getWidth() / 2, getY() + getHeight() / 2);
                            t.setVisible(true);
                            String str = l.getText().trim();
                            String c[] = str.split(" ");
                            t.ThanhToan(c[1], 1);
                        }

                    }
                });
                pnCenter.add(p);
            }
        }

    }

    public String getDate(long date) {
//        Date d = new Date();
        SimpleDateFormat dinhDangThoiGian = new SimpleDateFormat("HH:mm dd/MM/yyyy");

        return dinhDangThoiGian.format(date);
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
           

        }

        lbPhongBan.setText("Phòng bận: " + phongBan);
        lbPhongTrong.setText("Phòng trống: " + phongTrong);

        lbDT.setText("Phòng đặt trước(<30p): " + phongDatTruoc);

        jPanel11.setBackground(Color.decode("#EEEE00"));
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

        lbDT.setText("");

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
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        lbNoti = new javax.swing.JLabel();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jButton3 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        lbTime = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        lbPhongBan = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        lbPhongTrong = new javax.swing.JLabel();
        lbDT = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnCenter = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();

        Find.setTitle("Tìm Phòng");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Từ:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Đến:");

        jDateChooser1.setDateFormatString(" dd/MM/yyyy");
        jDateChooser1.setPreferredSize(new java.awt.Dimension(300, 30));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("TÌM PHÒNG");

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

        jDateChooser3.setDateFormatString("dd/MM/yyyy");
        jDateChooser3.setPreferredSize(new java.awt.Dimension(300, 30));

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
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbNoti, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(55, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FindLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(201, 201, 201))
        );

        FindLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jDateChooser1, jDateChooser3});

        FindLayout.setVerticalGroup(
            FindLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FindLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(FindLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FindLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel2))
                    .addGroup(FindLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23)
                .addGroup(FindLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbNoti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        FindLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jDateChooser1, jDateChooser3});

        setPreferredSize(new java.awt.Dimension(1000, 600));
        setLayout(new java.awt.GridLayout(1, 1));

        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1000, 600));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 600));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jToolBar1.setFloatable(false);
        jToolBar1.setAlignmentY(1.0F);

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

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Add List Filled-64.png"))); // NOI18N
        jButton8.setText("Thêm Dịch Vụ");
        jButton8.setFocusable(false);
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton8);

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
        jToolBar1.add(jSeparator1);

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
                .addGap(179, 179, 179)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbPhongTrong)
                .addGap(295, 295, 295)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbDT)
                .addContainerGap(572, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDT)
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
        jScrollPane1.setPreferredSize(new java.awt.Dimension(1000, 600));

        pnCenter.setBackground(new java.awt.Color(255, 255, 255));
        pnCenter.setAutoscrolls(true);
        pnCenter.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnCenter.setMaximumSize(new java.awt.Dimension(1000, 700));
        pnCenter.setLayout(new java.awt.GridLayout(5, 6, 5, 5));
        jScrollPane1.setViewportView(pnCenter);

        jPanel6.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel6, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Hệ Thống", jPanel1);

        jPanel2.setPreferredSize(new java.awt.Dimension(1000, 600));

        jPanel3.setPreferredSize(new java.awt.Dimension(1280, 100));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1266, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 1266, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 325, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Trợ Giúp", jPanel2);

        add(jTabbedPane1);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        CardLayout c = (CardLayout) getParent().getLayout();
        c.show(getParent(), "wel");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Find.setVisible(true);
        Find.setSize(552, 300);
        Find.setAlwaysOnTop(true);
        Find.setLocation(getX() + getWidth() / 2 - Find.getWidth() / 2, getY() + getHeight() / 2 - Find.getHeight() / 2);
        Find.setResizable(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        themdichvu t = new themdichvu(getX() + getWidth() / 2, getY() + getHeight() / 2);
        t.setVisible(true);

    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        reRoom();
        NgayNhan = 0;
        NgayTra = 0;

        jPanel11.setBackground(Color.decode("#EEEE00"));
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        lbNoti.setText("");
        NgayNhan = 0;
        NgayTra = 0;
        long tFrom = 0;
        long tTo = 0;

        try {
            tFrom = getDate(jDateChooser1);
        } catch (Exception e) {
            lbNoti.setText("Thời gian không được bỏ trống!");
        }
        try {
            tTo = getDate(jDateChooser3);

        } catch (Exception e) {
            lbNoti.setText("Thời gian không được bỏ trống!");
        }
        if (pareDayNow(tFrom)) {
            tFrom = getDate();

        } else {
//            tFrom = pareDay(tFrom) + 12 * 60 * 60 * 1000;
        }
//        tTo = pareDay(tTo) + 12 * 60 * 60 * 1000;
        if (tFrom > 0) {
            if (tFrom > (getDate() - 5 * 60 * 1000)) {
                if (tTo > 0) {
                    if (tTo < tFrom) {
                        lbNoti.setText("Thời gian đến phải nhỏ hơn thời gian trả!");
                    } else {
                        Findroom(tFrom, tTo);
                        NgayNhan = tFrom;
                        NgayTra = tTo;
                        jDateChooser1.setCalendar(null);
                        jDateChooser3.setCalendar(null);
                        Find.setVisible(false);
                    }
                }
            } else {
                lbNoti.setText("Thời gian đến phải lớn hơn thời gian hiện tại!");
            }
        }
    }//GEN-LAST:event_jButton9ActionPerformed
    public long getDate(JDateChooser date) {

        long time = date.getDate().getTime();

        return time;
    }

    public long getDate() {
        Date d = new Date();
//        SimpleDateFormat dinhDangThoiGian = new SimpleDateFormat("yyyy-MM-dd");
//        Date d2 = new Date(dinhDangThoiGian.format(d.getTime()));
        long time = d.getTime();
        return time;
    }

    private boolean pareDayNow(long d1) {
        Date d = new Date();
        SimpleDateFormat dinhDangThoiGian = new SimpleDateFormat("dd/MM/yyyy");
        String t1 = dinhDangThoiGian.format(d.getTime());
        String t2 = dinhDangThoiGian.format(d1);
        if (t1.equalsIgnoreCase(t2)) {
            return true;
        }

        return false;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame Find;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lbDT;
    private javax.swing.JLabel lbNoti;
    private javax.swing.JLabel lbPhongBan;
    private javax.swing.JLabel lbPhongTrong;
    private javax.swing.JLabel lbTime;
    public javax.swing.JPanel pnCenter;
    // End of variables declaration//GEN-END:variables
}
