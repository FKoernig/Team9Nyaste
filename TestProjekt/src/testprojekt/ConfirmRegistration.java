/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprojekt;

import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import oru.inf.InfDB;
import oru.inf.InfException;

/**
 *
 * @author isakj
 */
public class ConfirmRegistration extends javax.swing.JFrame {
    
    private static InfDB idb;
    RegistrationControl registrationControl = new RegistrationControl();
    

    /**
     * Creates new form ConfirmRegistration
     */
    public ConfirmRegistration() {
        initComponents();
        idb = TestProjekt.getDB();
        lblAnvandareBorttagen.setVisible(false);
        setAnvandareCbs();
        
        setLocationRelativeTo(this);
        
        registrationControl.showRegistrations(listRequests);
        registrationControl.emptyFields(lblUserName, lblName, lblEmail, lblPhone);
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblUserName = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblPhone = new javax.swing.JLabel();
        btnConfirm = new javax.swing.JButton();
        btnDecline = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listRequests = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        btnTabort = new javax.swing.JButton();
        lblAnvandareBorttagen = new javax.swing.JLabel();
        cmboAnvandare = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Anv�ndarnamn:");

        jLabel2.setText("Namn:");

        jLabel3.setText("Email:");

        jLabel4.setText("Telefon:");

        lblUserName.setText("jLabel5");

        lblName.setText("jLabel6");

        lblEmail.setText("jLabel7");

        lblPhone.setText("jLabel8");

        btnConfirm.setText("Godk�nn");
        btnConfirm.setEnabled(false);
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        btnDecline.setText("Neka");
        btnDecline.setEnabled(false);
        btnDecline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeclineActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(46, 46, 46)
                        .addComponent(lblUserName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(btnConfirm)))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblPhone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(btnDecline, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 46, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblUserName))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblName))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblEmail))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblPhone))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfirm)
                    .addComponent(btnDecline))
                .addGap(53, 53, 53))
        );

        listRequests.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listRequestsValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(listRequests);

        jButton1.setText("Tillbaka");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnTabort.setText("Ta bort anv�ndare");
        btnTabort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTabortActionPerformed(evt);
            }
        });

        lblAnvandareBorttagen.setText("Anv�ndare borttagen");

        cmboAnvandare.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmboAnvandare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmboAnvandareActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmboAnvandare, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTabort)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblAnvandareBorttagen)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTabort)
                    .addComponent(lblAnvandareBorttagen)
                    .addComponent(cmboAnvandare, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void listRequestsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listRequestsValueChanged
        // TODO add your handling code here:
        registrationControl.showInformation(listRequests, lblUserName, lblName, lblEmail, lblPhone,
                                            btnConfirm, btnDecline);
    }//GEN-LAST:event_listRequestsValueChanged

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        // TODO add your handling code here:
        String userName = lblUserName.getText();
        registrationControl.acceptRegistration(userName);
        JOptionPane.showMessageDialog(null, "Anv�ndare registrerad!");
        registrationControl.showRegistrations(listRequests);
        registrationControl.emptyFields(lblUserName, lblName, lblEmail, lblPhone);
        setAnvandareCbs();
    }//GEN-LAST:event_btnConfirmActionPerformed

    private void btnDeclineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeclineActionPerformed
        // TODO add your handling code here:
        String userName = lblUserName.getText();
        registrationControl.denyRegistration(userName);
        JOptionPane.showMessageDialog(null, "F�rfr�gan borttagen");
        registrationControl.showRegistrations(listRequests);
        registrationControl.emptyFields(lblUserName, lblName, lblEmail, lblPhone);
    }//GEN-LAST:event_btnDeclineActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cmboAnvandareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmboAnvandareActionPerformed
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_cmboAnvandareActionPerformed

    private void btnTabortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTabortActionPerformed
        // TODO add your handling code here:
         try{
        String cmboValue = cmboAnvandare.getSelectedItem().toString();
       
        String id= idb.fetchSingle("select anvandar_id from anvandare where anvandar_namn= '" + cmboValue + "'");
        if(idb.fetchSingle("select count (*) from inlagg where anvandar_id=" + id).equals("0")){
        idb.delete("DELETE FROM anvandare WHERE anvandar_namn = '" + cmboValue + "'");
       
        }
        else{
        ArrayList<String> inlaggsid = idb.fetchColumn("select inlaggsid from inlagg where anvandar_id=" + id);
       
       for(String idLista: inlaggsid){
       idb.delete("DELETE FROM formell WHERE inlaggsid = " + idLista);
       idb.delete("DELETE FROM informell WHERE inlaggsid = " + idLista);
       idb.delete("DELETE FROM inlagg WHERE inlaggsid= " + idLista);
        
        
        }
      
  
        this.idb.delete("DELETE FROM anvandare WHERE anvandar_namn = '" + cmboValue + "'");
        
            
            
        }
        setAnvandareCbs();
        lblAnvandareBorttagen.setVisible(true);
        }
        catch(InfException e)
        {
        JOptionPane.showMessageDialog(null, "Ett fel uppstod.");
                System.out.println("Internt felmeddelande:" + e.getMessage());
        }

        
    }//GEN-LAST:event_btnTabortActionPerformed

    private void setAnvandareCbs() {
        try{
      
            if(idb.fetchSingle("SELECT count (*) FROM anvandare").equals("0")){
           
            }
            
            else{
        ArrayList<String> anvandaree = idb.fetchColumn("SELECT anvandar_namn FROM ANVANDARE");
            DefaultComboBoxModel anvandare = new DefaultComboBoxModel();
          
            anvandare.addElement("-- Anv�ndare --");
            for(String anvandareLista : anvandaree){
                anvandare.addElement(anvandareLista);
            }
            cmboAnvandare.setModel(anvandare);
            
            }
            
        }
        
        catch(InfException e)
        {
        JOptionPane.showMessageDialog(null, "Ett fel uppstod.");
                System.out.println("Internt felmeddelande:" + e.getMessage());
        }
    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirm;
    private javax.swing.JButton btnDecline;
    private javax.swing.JButton btnTabort;
    private javax.swing.JComboBox<String> cmboAnvandare;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAnvandareBorttagen;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPhone;
    private javax.swing.JLabel lblUserName;
    private javax.swing.JList<String> listRequests;
    // End of variables declaration//GEN-END:variables
}
