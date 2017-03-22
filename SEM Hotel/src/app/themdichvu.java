/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import function.Function;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import model.DataProcess;

/**
 *
 * @author Tien Nguyen
 */
public class themdichvu extends javax.swing.JFrame {

    /**
     * Creates new form themdichvu
     */
    private Function f = Hotel.f;
    private boolean b = true;
    private static int makh;
    private ArrayList<Integer> maDV = new ArrayList<>();

    public themdichvu() {
        initComponents();
        tfCM.setEnabled(false);
        DefaultComboBoxModel boxModel = new DefaultComboBoxModel();
        for (int i = 0; i < f.getDichVu().size(); i++) {
            boxModel.addElement(f.getDichVu().get(i).getName());

        }
        cb1.setModel(boxModel);
    }

    public themdichvu(int x, int y) {
        initComponents();
        tfCM.setEnabled(false);
        System.out.println(f.mpBan + "< mb");
        if (f.mpBan + f.mpDTruoc == 0) {
            lbNofi.setText("Không có phòng phù hợp!");
            tfCM.setEnabled(false);
            tfName.setEnabled(false);
            tfPhong.setEnabled(false);
        }
        cb1.setEnabled(false);
        btDy.setEnabled(false);
        btnThem.setEnabled(false);
        btnXoa.setEnabled(false);

        setLocation(x - getWidth() / 2, y - getHeight() / 2);
        DefaultComboBoxModel boxModel = new DefaultComboBoxModel();
        for (int i = 0; i < f.getDichVu().size(); i++) {
            boxModel.addElement(f.getDichVu().get(i).getName());

        }
        cb1.setModel(boxModel);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbNofi = new javax.swing.JLabel();
        tfPhong = new javax.swing.JTextField();
        tfName = new javax.swing.JTextField();
        tfCM = new javax.swing.JTextField();
        btDy = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstDV = new javax.swing.JList<>();
        cb1 = new javax.swing.JComboBox<>();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();

        setTitle("Thêm dịch vụ");
        setAlwaysOnTop(true);
        setFocusCycleRoot(false);
        setFocusTraversalPolicyProvider(true);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Thêm dịch vụ");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Phòng:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Tên:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("CMND:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 2, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 255));
        jLabel5.setText("Dịch vụ");

        lbNofi.setBackground(new java.awt.Color(255, 0, 0));
        lbNofi.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        lbNofi.setForeground(new java.awt.Color(255, 0, 0));

        tfPhong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfPhongKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfPhongKeyReleased(evt);
            }
        });

        tfName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNameActionPerformed(evt);
            }
        });
        tfName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfNameKeyReleased(evt);
            }
        });

        tfCM.setEditable(false);
        tfCM.setAutoscrolls(false);

        btDy.setText("Đồng ý");
        btDy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDyActionPerformed(evt);
            }
        });

        jButton3.setText("Thoát");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        lstDV.setToolTipText("Các dịch vụ đã chọn");
        jScrollPane1.setViewportView(lstDV);

        cb1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jSeparator1)
                .addGap(77, 77, 77))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(24, 24, 24))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfCM, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)))
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(jButton3)
                        .addGap(67, 67, 67)
                        .addComponent(btDy, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(72, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lbNofi, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cb1, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnThem)
                            .addComponent(btnXoa))
                        .addGap(40, 40, 40))))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {tfCM, tfName, tfPhong});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btDy, jButton3});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnThem, btnXoa});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbNofi, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tfCM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnThem)
                        .addGap(11, 11, 11)
                        .addComponent(btnXoa))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(cb1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btDy)
                    .addComponent(jButton3))
                .addGap(21, 21, 21))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {tfCM, tfName, tfPhong});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btDy, jButton3});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnThem, btnXoa});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNameActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        String dv = cb1.getSelectedItem().toString().trim();
        DefaultListModel<String> model;
        if (lstDV.getModel().getSize() == 0) {
            model = new DefaultListModel<>();
            b = !b;
        } else {
            model = (DefaultListModel) lstDV.getModel();
        }

        model.addElement(dv);
        for (int i = 0; i < f.getDichVu().size(); i++) {
            if (dv.equalsIgnoreCase(f.getDichVu().get(i).getName())) {
                maDV.add(f.getDichVu().get(i).getId());
                lstDV.setModel(model);
            }
        }


    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // get model of Jlist
        DefaultListModel<String> model = (DefaultListModel<String>) lstDV
                .getModel();
        // delete item is selected
        if (!model.isEmpty() && lstDV.getSelectedIndex() >= 0) {
            maDV.remove(lstDV.getSelectedIndex());
            model.remove(lstDV.getSelectedIndex());
            for (int i = 0; i < maDV.size(); i++) {
                System.out.println(maDV.get(i) + " < mdv");
            }
        }
        // set model for JList
        lstDV.setModel(model);
    }//GEN-LAST:event_btnXoaActionPerformed

    private void tfPhongKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPhongKeyPressed


    }//GEN-LAST:event_tfPhongKeyPressed

    private void tfPhongKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPhongKeyReleased
        String n = tfPhong.getText();
        lbNofi.setText("");
        if (!n.equals("")) {

            for (int i = 0; i < f.getHoaDon().size(); i++) {
                if (f.getHoaDon().get(i).getMaPhong().equals(n) && f.getHoaDon().get(i).getThanhtoan() == 0 && f.getHoaDon().get(i).getNgayNhan() < getDate()) {
                    tfName.setText(f.getHoaDon().get(i).getTenKH());
                    tfCM.setText(f.getHoaDon().get(i).getCMND());
                    makh = f.getHoaDon().get(i).getId();
                    lbNofi.setText("");
                    cb1.setEnabled(true);
                    btDy.setEnabled(true);
                    btnThem.setEnabled(true);
                    btnXoa.setEnabled(true);
                    break;
                } else {
                    cb1.setEnabled(false);
                    btDy.setEnabled(false);
                    btnThem.setEnabled(false);
                    btnXoa.setEnabled(false);
                    lbNofi.setText("Không tìm thấy mã phòng!");
                    tfName.setText("");
                    tfCM.setText("");
                    makh = 0;
                }
            }
        } else {
            lbNofi.setText("");
        }
    }//GEN-LAST:event_tfPhongKeyReleased

    public long getDate() {
        Date d = new Date();
       
        //parse ngay thang sang dinh dang va chuyen thanh long.
        long time = d.getTime();
//        
        return time;
    }
    private void tfNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfNameKeyReleased
        String n = tfName.getText();
        lbNofi.setText("");
        if (!n.equals("")) {

            for (int i = 0; i < f.getHoaDon().size(); i++) {
                if (f.getHoaDon().get(i).getTenKH().equals(n) && f.getHoaDon().get(i).getThanhtoan() == 0 && f.getHoaDon().get(i).getNgayNhan() < getDate()) {
                    tfPhong.setText(f.getHoaDon().get(i).getMaPhong());
                    tfCM.setText(f.getHoaDon().get(i).getCMND());
                    makh = f.getHoaDon().get(i).getId();
                    lbNofi.setText("");
                    cb1.setEnabled(true);
                    btDy.setEnabled(true);
                    btnThem.setEnabled(true);
                    btnXoa.setEnabled(true);
                    break;
                } else {
                    cb1.setEnabled(false);
                    btDy.setEnabled(false);
                    btnThem.setEnabled(false);
                    btnXoa.setEnabled(false);
                    lbNofi.setText("Không tìm thấy tên khách hàng!");
                    tfPhong.setText("");
                    tfCM.setText("");
                    makh = 0;
                }
            }
        } else {
            lbNofi.setText("");
        }
    }//GEN-LAST:event_tfNameKeyReleased

    private void btDyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDyActionPerformed
//        ListModel model = lstDV.getModel();
        DataProcess db = new DataProcess();

        for (int i = 0; i < maDV.size(); i++) {      
            db.addDichVu(makh, maDV.get(i));
        }
        JOptionPane.showMessageDialog(this,"Thêm dịch vụ thành công!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
        this.dispose();
    }//GEN-LAST:event_btDyActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(themdichvu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(themdichvu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(themdichvu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(themdichvu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new themdichvu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btDy;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cb1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbNofi;
    private javax.swing.JList<String> lstDV;
    private javax.swing.JTextField tfCM;
    private javax.swing.JTextField tfName;
    private javax.swing.JTextField tfPhong;
    // End of variables declaration//GEN-END:variables
}