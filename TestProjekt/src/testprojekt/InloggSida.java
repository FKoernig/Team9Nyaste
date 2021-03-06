/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprojekt;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import oru.inf.InfDB;
import oru.inf.InfException;

/**
 *
 * @author Mollyyyyy
 */
public class InloggSida extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    
        private InfDB idb;
    /**
     * 
     * Creates new form InlogSida
     */
    public InloggSida(InfDB idb) {
       this.idb=idb;
       initComponents();
       setLocationRelativeTo(this);
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtanvandarnamn = new javax.swing.JTextField();
        lblanvandarnamn = new javax.swing.JLabel();
        lbllosen = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnloggain = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        passPassword = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        txtanvandarnamn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtanvandarnamnActionPerformed(evt);
            }
        });

        lblanvandarnamn.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblanvandarnamn.setText("Användarnamn:");

        lbllosen.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lbllosen.setText("Lösenord:");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/testprojekt/Images/Logga1.png"))); // NOI18N
        jLabel1.setFocusTraversalPolicyProvider(true);

        btnloggain.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        btnloggain.setText("Logga in");
        btnloggain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnloggainActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jButton1.setText("Registrera");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(128, 128, 128)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblanvandarnamn)
                                    .addComponent(lbllosen)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton1)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtanvandarnamn)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(btnloggain))
                            .addComponent(passPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel1)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblanvandarnamn)
                    .addComponent(txtanvandarnamn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbllosen)
                    .addComponent(passPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnloggain)
                    .addComponent(jButton1))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtanvandarnamnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtanvandarnamnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtanvandarnamnActionPerformed

    private void btnloggainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnloggainActionPerformed
        // TODO add your handling code here:
        String anvandaren = this.txtanvandarnamn.getText();
        String losen = new String(passPassword.getPassword());
        if (Validator.tfIsNotEmpty(txtanvandarnamn) && Validator.passwordIsNotEmpty(passPassword)) {
            //Validering.passwordIsNotEmpty(this.txtlosenord, (JTextField)this.alienLosenTf) && Validering.KollaTextFaltOchSifferFalt(this.alienTf))
            System.out.println("test");
            try {
                String an = this.idb.fetchSingle("select ANVANDAR_ID from ANVANDARE where ANVANDAR_NAMN = '" + anvandaren + "'");
                String lo = this.idb.fetchSingle("select LOSEN from ANVANDARE where ANVANDAR_ID =" + an);
                String he = this.idb.fetchSingle("Select ANVANDAR_NAMN from anvandare where ANVANDAR_ID =" + an);
                String ads = this.idb.fetchSingle("select ADMINJANEJ from ANVANDARE where ANVANDAR_ID=" + an);

                if (anvandaren.equals(he) && losen.equals(lo) && ads.equals("N")) {
                    this.setVisible(false);
                    new BlogV2(an).setVisible(true);
                    //new MainWindow(idb).setVisible(true);
                    //ValkommenAlien enAliensida = new ValkommenAlien(anvandaren,idb);
                    //enAliensida.setVisible(true);

                } else if (anvandaren.equals(he) && losen.equals(lo) && ads.equals("J")) {
                    this.setVisible(false);
                    new MainWindow(idb, an).setVisible(true);

                } else {

                    JOptionPane.showMessageDialog(null, "Kunde inte hitta anvandaren");
                }
            } catch (InfException e) {
                JOptionPane.showMessageDialog(null, "Ett fel uppstod.");
                System.out.println("Internt felmeddelande:" + e.getMessage());
            }
        }
    }//GEN-LAST:event_btnloggainActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new Registration().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnloggain;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblanvandarnamn;
    private javax.swing.JLabel lbllosen;
    private javax.swing.JPasswordField passPassword;
    private javax.swing.JTextField txtanvandarnamn;
    // End of variables declaration//GEN-END:variables
}
