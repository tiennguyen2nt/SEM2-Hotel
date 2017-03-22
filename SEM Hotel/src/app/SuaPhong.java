/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import entity.Room;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import model.DataProcess;

/**
 *
 * @author FIA
 */
public class SuaPhong extends javax.swing.JFrame {

    /**
     * Creates new form SuaPhong
     */
    function.Function f = Hotel.f;

    public SuaPhong() {
        initComponents();
    }

    public SuaPhong(int x, int y) {
        initComponents();
        setLocation(x - getWidth() / 2, y - getHeight() / 2);
        setSize(406, 450);
    }

    public void getMaphong(String maphong) {

        String id = maphong;
        for (int i = 0; i < f.getRoom().size(); i++) {
            Room r = f.getRoom().get(i);
            if (r.getId().equalsIgnoreCase(maphong)) {
                txtId.setText(r.getId());
                txtName.setEditable(true);
                txtName.setText(r.getPhong());
                cbbType.setSelectedIndex(r.getLoaiphong());
                txtPrice.setEditable(true);
                if (r.getTrangThai() == 0) {
                    chbProgress.setSelected(false);
                } else {
                    chbProgress.setSelected(true);
                }
                txtPrice.setText(r.getTien() + "");
                txtRole.setEditable(true);
                txtRole.setText(r.getVitri());
                txtNote.setEditable(true);
                txtNote.setText(r.getVitri());
                txtId.setEditable(false);
                cbbType.setEnabled(true);
                btnok.setEnabled(true);
            }
        }
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        txtPrice = new javax.swing.JTextField();
        txtRole = new javax.swing.JTextField();
        txtNote = new javax.swing.JTextField();
        btnok = new javax.swing.JButton();
        btnDeny = new javax.swing.JButton();
        cbbType = new javax.swing.JComboBox<>();
        chbProgress = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();

        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setText("Cập nhật thông tin phòng");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 11, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Mã phòng");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 73, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Tên phòng");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 107, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Loại phòng");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 174, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Đơn giá");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 212, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Vị trí");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 255, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Ghi chú");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 298, -1, -1));

        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });
        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdKeyReleased(evt);
            }
        });
        getContentPane().add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 73, 171, -1));

        txtName.setEditable(false);
        getContentPane().add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 104, 171, -1));

        txtPrice.setEditable(false);
        getContentPane().add(txtPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 212, 171, -1));

        txtRole.setEditable(false);
        getContentPane().add(txtRole, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 255, 171, -1));

        txtNote.setEditable(false);
        getContentPane().add(txtNote, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 298, 171, -1));

        btnok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/tick.png"))); // NOI18N
        btnok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnokActionPerformed(evt);
            }
        });
        getContentPane().add(btnok, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 350, 68, -1));

        btnDeny.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/cross.png"))); // NOI18N
        btnDeny.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDenyActionPerformed(evt);
            }
        });
        getContentPane().add(btnDeny, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 350, 66, -1));

        cbbType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Phòng đơn", "Phòng đôi", "Phòng ba" }));
        getContentPane().add(cbbType, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 174, 171, -1));

        chbProgress.setText("Có thể hoạt động");
        getContentPane().add(chbProgress, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Trạng thái:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here

    }//GEN-LAST:event_txtIdActionPerformed

    private void btnDenyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDenyActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnDenyActionPerformed

    private void btnokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnokActionPerformed
        // TODO add your handling code here:
        DataProcess data = new DataProcess();
        int result = 0;
        String id = txtId.getText();
        String name = txtName.getText();
        int type = cbbType.getSelectedIndex();
        int stt = 0;
        if (chbProgress.isSelected()) {
            stt = 1;
        }
        int price = Integer.parseInt(txtPrice.getText());
        String role = txtRole.getText();
        String note = null;
        note = txtNote.getText();
        String sql = "EXEC dbo.updateRoom @id = N'" + id + "',@name = N'" + name + "',@type = " + type + ",@stt = " + stt + ",@price = " + price + ",@role = N'" + role + "',@note = N'" + note + "'";
        try {
            Statement st = data.getConnection().createStatement();
            result = st.executeUpdate(sql);
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Sửa thành công");
                txtId.setEditable(true);
                txtName.setEditable(false);
                cbbType.setEnabled(false);
                txtPrice.setEditable(false);
                txtRole.setEditable(false);
                txtNote.setEditable(false);
                txtId.setText("");
                txtName.setText("");
                txtPrice.setText("");
                txtRole.setText("");
                txtNote.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Sửa thất bại");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Sửa thất bại, lỗi " + ex.toString());
        }


    }//GEN-LAST:event_btnokActionPerformed

    private void txtIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyReleased


    }//GEN-LAST:event_txtIdKeyReleased

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained

    }//GEN-LAST:event_formFocusGained

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        // TODO add your handling code here:
        btnok.setEnabled(false);
    }//GEN-LAST:event_formWindowGainedFocus

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
            java.util.logging.Logger.getLogger(SuaPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SuaPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SuaPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SuaPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SuaPhong().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeny;
    private javax.swing.JButton btnok;
    private javax.swing.JComboBox<String> cbbType;
    private javax.swing.JCheckBox chbProgress;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNote;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtRole;
    // End of variables declaration//GEN-END:variables
}